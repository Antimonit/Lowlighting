import java.io.FileInputStream
import java.util.Properties

plugins {
    java
    id("org.jetbrains.intellij") version "1.5.3"
    id("org.jetbrains.kotlin.jvm") version "1.6.21"
}

group = "me.khol.intellij.plugin"
version = "1.3.0"

repositories {
    mavenCentral()
}

sourceSets.main.configure {
    java.srcDirs("src/main/gen")
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib")
    testImplementation("junit:junit:4.13.2")
}

// See https://github.com/JetBrains/gradle-intellij-plugin/
intellij {
    version.set("2021.1")
    plugins.set(listOf("java", "Kotlin"))
    type.set("IC")
}

tasks.compileKotlin {
    kotlinOptions.jvmTarget = "11"
}

tasks.compileTestKotlin {
    kotlinOptions.jvmTarget = "11"
}

tasks.buildSearchableOptions {
    enabled = false
}

tasks.patchPluginXml {
    version.set(project.version.toString())
    sinceBuild.set("211")
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