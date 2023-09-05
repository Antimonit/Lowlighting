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

sourceSets.register("testData") {
    java.srcDirs("src/test/testData")
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib")
    testImplementation("junit:junit:4.13.2")
}

// https://plugins.jetbrains.com/docs/intellij/build-number-ranges.html#platformVersions
// 2022.3 requires Java 17
// 2020.3 requires Java 11
java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

tasks.buildSearchableOptions {
    enabled = false
}

/*
 * We need the following to resolve `Cannot resolve symbol` errors when testing highlighting.
 * https://plugins.jetbrains.com/docs/intellij/testing-faq.html#how-to-test-a-jvm-language
 */
tasks.test {
    systemProperty("idea.home.path", "../intellij-community")
}