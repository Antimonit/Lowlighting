package me.khol

import java.io.FileInputStream
import java.util.*

plugins {
    java
    id("org.jetbrains.intellij")
}

group = "me.khol.intellij.plugin"
version = "1.3.3"

// See https://github.com/JetBrains/gradle-intellij-plugin/
intellij {
    version.set("2022.1")
    plugins.set(listOf("java", "Kotlin"))
    type.set("IC")
}

tasks.buildSearchableOptions {
    enabled = false
}

tasks.patchPluginXml {
    version.set(project.version.toString())
    sinceBuild.set("211")
    untilBuild.set("231.*")
    pluginDescription.set(me.khol.lowlighting.description)
    changeNotes.set(me.khol.lowlighting.changeNotes.toHtml())
}

val propertiesFile = rootProject.file("local.properties")
val lowlightingToken = if (propertiesFile.exists()) {
    val localProperties = Properties().apply {
        load(FileInputStream(propertiesFile))
    }
    localProperties.getProperty("org.gradle.project.intellijPublishToken")
} else {
    null
}

tasks.publishPlugin {
    token.set(lowlightingToken)
}
