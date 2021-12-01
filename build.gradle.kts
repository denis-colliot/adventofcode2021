import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.6.0"
    id("application")
}

group = "com.adventofcode"
version = "1.0.0"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "12"
}

application {
    val day = project.findProperty("day")
        ?.toString()?.toIntOrNull()
        ?: 1
    mainClass.set("com.adventofcode.day$day.MainKt")
}