import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

architectury {
    // Set up Architectury for the common project.
    // This sets up the transformations (@ExpectPlatform etc.) we need for production environments.
    platformSetupLoomIde()
    forge()
}

loom {
    accessWidenerPath.set(project(":common").file("src/main/resources/goldenberries.accesswidener"))

    forge {
        // Enable Loom's AW -> AT conversion for Forge.
        // This will make remapJar convert the common AW to a Forge access transformer.
        convertAccessWideners.set(true)
        // Add an "extra" converted access widener which comes from outside the project.
        // The path is relative to the mod jar's root.
        extraAccessWideners.add("goldenberries.accesswidener")

        // Add mixin configs. Forge needs these explicitly declared
        // via Gradle; in Fabric, they're in fabric.mod.json.
        mixinConfigs("mixins.goldenberries.common.json", "mixins.goldenberries.forge.json")
    }
}

repositories {
    // Set up Kotlin for Forge's Maven repository.
    maven {
        name = "kotlinforforge"
        url = uri("https://thedarkcolour.github.io/KotlinForForge/")
    }
    mavenCentral()
}

dependencies {
    // Add dependency on Forge. This is mainly used for generating the patched Minecraft jar with Forge classes.
    forge("net.minecraftforge:forge:${rootProject.property("minecraft_version")}-${rootProject.property("forge_version")}")

    // Add Kotlin for Forge.
    // Based on their own instructions: https://github.com/thedarkcolour/KotlinForForge/blob/70385f5/thedarkcolour/kotlinforforge/gradle/kff-3.0.0.gradle
    implementation("thedarkcolour:kotlinforforge:${rootProject.property("forge_kotlin")}")
    // Without the manually specified versions, Loom's generateDLIConfig fails??
    forgeRuntimeLibrary(kotlin("stdlib-jdk8", version = "1.6.0"))
    forgeRuntimeLibrary(kotlin("reflect", version = "1.6.0"))

    // Depend on the common project. The "namedElements" configuration contains the non-remapped
    // classes and resources of the project.
    // It follows Gradle's own convention of xyzElements for "outgoing" configurations like apiElements.
    implementation(project(":common", configuration = "namedElements")) {
        isTransitive = false
    }

    // Used at dev runtime by the Architectury Transformer to automatically read changes in the common jar
    // and apply them.
    "developmentForge"(project(":common", configuration = "namedElements")) {
        isTransitive = false
    }

    // Bundle the transformed version of the common project in the mod.
    // The transformed version replaces all @ExpectPlatform calls to call the Forge versions.
    bundle(project(path = ":common", configuration = "transformProductionForge")) {
        isTransitive = false
    }

    // Add regular mod dependency on REI - API for compile time and the mod itself for runtime.
    // modLocalRuntime won't be exposed if other mods depend on your mod unlike modRuntimeOnly.
    modCompileOnly("me.shedaniel:RoughlyEnoughItems-api-forge:${rootProject.property("rei_version")}")
    modLocalRuntime("me.shedaniel:RoughlyEnoughItems-forge:${rootProject.property("rei_version")}")
    modLocalRuntime("dev.architectury:architectury-forge:${rootProject.property("architectury_api_version")}")
}

tasks {

    processResources {
        // Mark that this task depends on the project version,
        // and should reset when the project version changes.
        inputs.property("version", project.version)

        // Replace the $version template in mods.toml with the project version.
        filesMatching("META-INF/mods.toml") {
            expand("version" to project.version)
        }
    }
}
