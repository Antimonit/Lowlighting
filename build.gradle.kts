plugins {
    id("me.khol.lowlighting")
    id("org.jetbrains.kotlin.jvm") version "1.8.0"
}

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

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

tasks.buildSearchableOptions {
    enabled = false
}
