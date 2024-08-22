plugins {
    id("me.khol.lowlighting")
    id("org.jetbrains.kotlin.jvm") version "2.0.20"
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

tasks.compileKotlin {
    kotlinOptions.jvmTarget = "11"
}

tasks.compileTestKotlin {
    kotlinOptions.jvmTarget = "11"
}

tasks.buildSearchableOptions {
    enabled = false
}
