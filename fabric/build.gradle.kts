import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

architectury {
    platformSetupLoomIde()
    fabric()
}


// The generated resources directory for the AW.
val generatedResources = file("src/generated/resources")
val accessWidenerFile = project(":common").file("src/main/resources/goldenberries.accesswidener")

loom {
    // Make the Fabric project use the common access widener.
    // Technically useless, BUT this file is also needed at dev runtime of course
    accessWidenerPath.set(accessWidenerFile)
}

repositories {
    // TerraformersMC maven for Mod Menu.
    maven {
        name = "TerraformersMC"
        url = uri("https://maven.terraformersmc.com/releases")

        content {
            includeGroup("com.terraformersmc")
        }
    }
    mavenCentral()
}

dependencies {
    // Depend on the common project. The "namedElements" configuration contains the non-remapped
    // classes and resources of the project.
    // It follows Gradle's own convention of xyzElements for "outgoing" configurations like apiElements.
    implementation(project(":common", configuration = "namedElements")) {
        isTransitive = false
    }

    // Used at dev runtime by the Architectury Transformer to automatically read changes in the common jar
    // and apply them.
    "developmentFabric"(project(":common", configuration = "namedElements")) {
        isTransitive = false
    }

    // Bundle the transformed version of the common project in the mod.
    // The transformed version replaces all @ExpectPlatform calls to call the Fabric versions.
    bundle(project(path = ":common", configuration = "transformProductionFabric")) {
        isTransitive = false
    }

    // Standard Fabric mod setup.
    modImplementation("net.fabricmc:fabric-loader:${rootProject.property("fabric_loader")}")
    modImplementation("net.fabricmc.fabric-api:fabric-api:${rootProject.property("fabric_api")}")
    modApi("net.fabricmc:fabric-language-kotlin:${rootProject.property("fabric_kotlin")}")

    modLocalRuntime(modCompileOnly("com.terraformersmc:modmenu:${rootProject.property("modmenu_version")}")!!)
    modLocalRuntime("me.shedaniel:RoughlyEnoughItems-fabric:${rootProject.property("rei_version")}")
}

tasks {
    val copyAccessWidener by registering(Copy::class) {
        from(accessWidenerFile)
        into(generatedResources)
    }

    processResources {
        // Hook the AW copying and data generation to processResources.
        // Note: this is done differently in the other subprojects where the data generator
        // has to be run manually!
        dependsOn(copyAccessWidener)
        // Mark that this task depends on the project version,
        // and should reset when the project version changes.
        inputs.property("version", project.version)

        // Replace the $version template in fabric.mod.json with the project version.
        filesMatching("fabric.mod.json") {
            expand("version" to project.version)
        }
    }
}
