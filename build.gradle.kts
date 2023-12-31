import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("fabric-loom") version "1.3.8"
    kotlin("jvm") version "1.9.10"
    kotlin("plugin.serialization") version "1.9.10"
    id("org.teamvoided.iridium") version "3.0.2"
}

group = project.properties["maven_group"]!!
version = project.properties["mod_version"]!!
base.archivesName.set(project.properties["archives_base_name"] as String)
description = "TeamVoided Template Mod"

repositories {
    mavenCentral()
    maven { url = uri("https://maven.teamvoided.org/releases") }
    maven { url = uri("https://maven.awakenedredstone.com")}
}

modSettings {
    modId(base.archivesName.get())
    modName("Team Voided Template Mod")

    entrypoint("main", "org.teamvoided.hydra.Hydra::commonInit")
    entrypoint("client", "org.teamvoided.hydra.Hydra::clientInit")

    dependency("twitch4j", "1.17.0+1.3.0")
}

dependencies{
    //TwitchAPI
    modImplementation ("com.github.twitch4j:twitch4j-fabric:1.17.0+1.3.0")

    modImplementation("org.teamvoided:voidlib-core:1.5.4+1.20.1")
    modImplementation("org.teamvoided:voidlib-vui:1.5.4+1.20.1")
    modImplementation("org.teamvoided:voidlib-config:1.5.4+1.20.1")
    modImplementation("org.teamvoided:voidlib-networking:1.5.4+1.20.1")
}

tasks {
    val targetJavaVersion = 17
    withType<JavaCompile> {
        options.encoding = "UTF-8"
        options.release.set(targetJavaVersion)
    }

    withType<KotlinCompile> {
        kotlinOptions.jvmTarget = targetJavaVersion.toString()
    }

    java {
        toolchain.languageVersion.set(JavaLanguageVersion.of(JavaVersion.toVersion(targetJavaVersion).toString()))
        withSourcesJar()
    }
}