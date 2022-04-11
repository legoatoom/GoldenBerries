import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

// I used Adorns buildscript as example.

plugins {
    base
    kotlin("jvm") version "1.6.0" apply false
    id("architectury-plugin") version "3.4-SNAPSHOT"
    id("dev.architectury.loom") version "0.11.0.248" apply false
    id("io.github.juuxel.loom-quiltflower") version "1.6.0" apply false

    id("org.jmailen.kotlinter") version "3.2.0" apply false
    id("com.github.johnrengelman.shadow") version "7.0.0" apply false
}

architectury {
    minecraft = project.property("minecraft_version").toString()
}

group = project.property("maven_group")!!
version = "${project.property("mod_version")}+${project.property("minecraft_version")}"
base.archivesName.set("GoldenBerries")

tasks {
    val collectJars by registering(Copy::class) {
        val tasks = subprojects.filter { it.path != ":common" }.map { it.tasks.named("remapJar") }
        dependsOn(tasks)

        from(tasks)
        into(buildDir.resolve("libs"))
    }

    assemble {
        dependsOn(collectJars)
    }
}

// Do the shared set up for the Minecraft subprojects.
subprojects {
    apply(plugin = "java")
    apply(plugin = "org.jetbrains.kotlin.jvm")
    apply(plugin = "dev.architectury.loom")
    apply(plugin = "architectury-plugin")
    apply(plugin = "io.github.juuxel.loom-quiltflower")
    apply(plugin = "org.jmailen.kotlinter")

    // Set Java version.
    extensions.configure<JavaPluginExtension> {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    // Copy the artifact metadata from the root project.
    group = rootProject.group
    version = rootProject.version
    base.archivesName.set(rootProject.base.archivesName)


    dependencies {
        "minecraft"("net.minecraft:minecraft:${rootProject.property("minecraft_version")}")

        // Set up the layered mappings with Yarn and my Menu mappings.
        // The average modder would have "mappings"("net.fabricmc:yarn:...") or "mappings"(loom.officialMojangMappings()).
        "mappings"("net.fabricmc:yarn:${rootProject.property("minecraft_version")}+${rootProject.property("yarn_mappings")}:v2")
    }

    tasks {
        withType<JavaCompile> {
            options.encoding = "UTF-8"
            options.release.set(17)
        }

        // Set the Kotlin JVM target to match the Java version
        // for all Kotlin compilation tasks.
        withType<KotlinCompile> {
            kotlinOptions.jvmTarget = "17"
        }

        // Include the license in the jar files.
        // See the dependencies section above for why this is in quotes.
        "jar"(Jar::class) {
            from(rootProject.file("LICENSE"))
        }
    }
}

subprojects {
    if (path != ":common") {
        apply(plugin = "com.github.johnrengelman.shadow")

        val bundle by configurations.creating {
            // This configuration is only meant to be resolved to its files but not published in
            // any way, so we set canBeConsumed = false and canBeResolved = true.
            // See https://docs.gradle.org/current/userguide/declaring_dependencies.html#sec:resolvable-consumable-configs.
            isCanBeConsumed = false
            isCanBeResolved = true
        }

        tasks {
            "jar"(Jar::class) {
                archiveClassifier.set("dev-slim")
            }

            "shadowJar"(ShadowJar::class) {
                archiveClassifier.set("dev-shadow")
                // Include our bundle configuration in the shadow jar.
                configurations = listOf(bundle)
            }

            "remapJar"(net.fabricmc.loom.task.RemapJarTask::class) {
                dependsOn("shadowJar")
                // Replace the remap jar task's input with the shadow jar containing the common classes.
                inputFile.set(named<ShadowJar>("shadowJar").flatMap { it.archiveFile })
                // The project name will be "fabric" or "forge", so this will become the classifier/suffix
                // for the jar. For example: Adorn-3.4.0-fabric.jar
                archiveClassifier.set(project.name)
            }
        }
    }
}
