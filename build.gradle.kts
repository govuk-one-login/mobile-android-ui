buildscript {
    val jacocoVersion by rootProject.extra("0.8.11")
    val minAndroidVersion by rootProject.extra { 29 }
    val compileAndroidVersion by rootProject.extra { 33 }
    val androidBuildToolsVersion by rootProject.extra { "33.0.0" }
    val composeKotlinCompilerVersion by rootProject.extra { "1.5.0" }
    val configDir by rootProject.extra { "$rootDir/config" }

    val localProperties = java.util.Properties()
    if (rootProject.file("local.properties").exists()) {
        println(localProperties)
        localProperties.load(java.io.FileInputStream(rootProject.file("local.properties")))
    }

    fun findPackageVersion(): String {
        var version = "1.0.0"

        println(localProperties)
        if (rootProject.hasProperty("packageVersion")) {
            version = rootProject.property("packageVersion") as String
        } else if (localProperties.getProperty("packageVersion") != null) {
            version = localProperties.getProperty("packageVersion") as String
        }

        return version
    }

    val packageVersion by rootProject.extra { findPackageVersion() }

    dependencies {
        classpath(
            "org.jacoco",
            "org.jacoco.core",
            "_",
        )
        classpath(
            "org.jacoco",
            "org.jacoco.ant",
            "_",
        )
        classpath(
            "org.jacoco",
            "org.jacoco.report",
            "_",
        )
        classpath(
            "org.jacoco",
            "org.jacoco.agent",
            "_",
        )
    }
}

plugins {
    id("maven-publish")
    id("org.jetbrains.kotlin.android") version "1.9.0" apply false
    id("com.android.library") apply false
    id("org.jlleitschuh.gradle.ktlint") version "11.6.1" apply false
    id("io.gitlab.arturbosch.detekt") version "1.23.1" apply false
    id("app.cash.paparazzi") apply false
    id("org.sonarqube") version "4.3.0.3225"
    id("uk.gov.ui.sonarqube-root-config")
}

apply {
    from("$rootDir/config/styles/tasks.gradle")
}

tasks.register("check") {
    dependsOn("vale")
}
