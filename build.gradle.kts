plugins {
    java
    id("org.jetbrains.intellij")
    id("org.jetbrains.kotlin.jvm")
}

group = "me.khol.intellij.plugin"
version = "1.2.0"

sourceSets.main.configure {
    java.srcDirs("src/main/gen")
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib")
    testImplementation("junit:junit:4.13.2")
}

// See https://github.com/JetBrains/gradle-intellij-plugin/
intellij {
    version.set("2019.3")
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
    sinceBuild.set("193")
    untilBuild.set("203.*")
}
