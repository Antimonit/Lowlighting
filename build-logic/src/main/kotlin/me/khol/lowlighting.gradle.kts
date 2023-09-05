package me.khol

import java.io.FileInputStream
import java.util.*

plugins {
    java
    id("org.jetbrains.intellij")
}

group = "me.khol.intellij.lowlighting"
version = "1.4.0"

// See https://github.com/JetBrains/gradle-intellij-plugin/
// See https://plugins.jetbrains.com/docs/intellij/tools-gradle-intellij-plugin.html#configuration-intellij-extension
intellij {
    version.set("2022.3")
    plugins.set(listOf("java", "Kotlin"))
    type.set("IC")
}

// https://plugins.jetbrains.com/docs/intellij/tools-gradle-intellij-plugin-faq.html#how-to-disable-building-searchable-options
tasks.buildSearchableOptions {
    enabled = false
}

tasks.patchPluginXml {
    version.set(project.version.toString())
    sinceBuild.set("223")
    untilBuild.set("241.*")
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
