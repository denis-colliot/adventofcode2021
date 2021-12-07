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

tasks.create<Task>("newDay") {
    doLast {
        val day = project.findProperty("day")
            ?.toString()?.toIntOrNull()
            ?: error("Missing valid day number")

        val mainSourceFile = File(project.projectDir.path, "src/main/kotlin/com/adventofcode/day$day/Main.kt")
        val mainResourceInput = File(project.projectDir.path, "src/main/resources/day$day/input")
        val testSourceFile = File(project.projectDir.path, "src/test/kotlin/com/adventofcode/day$day/Day${day}Test.kt")

        if (mainSourceFile.exists()) {
            error("Main source file `$mainSourceFile` already exists")
        }
        if (testSourceFile.exists()) {
            error("Test source file `$testSourceFile` already exists")
        }

        // Main source file
        mainSourceFile.apply {
            parentFile.mkdirs()
            createNewFile()
            writeText(
                """
                package com.adventofcode.day$day
                
                import com.adventofcode.util.printResultWithTime
                import com.adventofcode.util.readResourceLines
                
                fun main() {
                    val input = readResourceLines("/day$day/input")
                
                    printResultWithTime {
                        "Day $day result = TODO"
                    }
                }
                """.trimIndent()
            )
        }

        // Main resource input
        mainResourceInput.apply {
            parentFile.mkdirs()
            createNewFile()
        }

        // Test source file
        testSourceFile.apply {
            parentFile.mkdirs()
            createNewFile()
            writeText(
                """
                package com.adventofcode.day$day
                
                import kotlin.test.Test
                
                class Day${day}Test {
    
                    private val testInput = "TODO"
            
                    @Test
                    fun `should xxx`() {
                        // Given
                        
                        // When
                
                        // Then
                    }
                }
                """.trimIndent()
            )
        }
    }
}