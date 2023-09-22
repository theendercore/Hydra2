import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("fabric-loom") version "1.3-SNAPSHOT"
    kotlin("jvm") version "1.9.0"
    kotlin("plugin.serialization") version "1.9.0"
    id("org.teamvoided.iridium") version "3.0.2"
}

group = project.properties["maven_group"]!!
version = project.properties["mod_version"]!!
base.archivesName.set(project.properties["archives_base_name"] as String)
description = "TeamVoided Template Mod"

repositories {
    mavenCentral()
    maven { url = uri("https://maven.teamvoided.org/releases") }
}

modSettings {
    modId(base.archivesName.get())
    modName("Team Voided Template Mod")

    entrypoint("main", "org.teamvoided.templatemod.TemplateMod::commonInit")
    entrypoint("client", "org.teamvoided.templatemod.TemplateMod::clientInit")

    dependency("twitch4j", "1.17.0-1.3.0")
}

dependencies{
    //TwitchAPI
    implementation("com.github.twitch4j:twitch4j:${project.properties["twitch4j_version"]}")

    modImplementation("org.teamvoided:voidlib-core:1.5.2+1.20.1")
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