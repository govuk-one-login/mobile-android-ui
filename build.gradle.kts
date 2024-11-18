buildscript {
    val jacocoVersion by rootProject.extra("0.8.11")
    val minAndroidVersion by rootProject.extra { 29 }
    val compileAndroidVersion by rootProject.extra { 34 }
    val androidBuildToolsVersion by rootProject.extra { "34.0.0" }
    val composeKotlinCompilerVersion by rootProject.extra { "1.5.0" }
    val configDir by rootProject.extra { "$rootDir/config" }
    val baseNamespace by rootProject.extra { "uk.gov.android.ui" }

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
}

plugins {
    id("maven-publish")
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.ktlint) apply false
    alias(libs.plugins.detekt) apply false
    alias(libs.plugins.paparazzi) apply false
    id("org.sonarqube") version "5.1.0.4882"
    id("uk.gov.ui.sonarqube-root-config")
}

apply {
    from("$rootDir/config/styles/tasks.gradle")
}

tasks.register("check") {
    dependsOn("vale")
}
