// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    extra.apply {
        set("configDir", "$rootDir/config")
        set("dep_jacoco", "0.8.8")
        set("minAndroidVersion", 29)
        set("compileAndroidVersion", 33)
        set("androidBuildToolsVersion", "33.0.0")
        set("composeKotlinCompilerVersion", "1.5.0")
        set("packageVersion", "1.4.0")
    }
}

plugins {
    id("maven-publish")
    id("org.jetbrains.kotlin.android") version "1.9.0" apply false
    id("com.android.library") version "8.1.1" apply false
    id("org.jlleitschuh.gradle.ktlint") version "11.5.0" apply false
    id("io.gitlab.arturbosch.detekt") version "1.23.1" apply false
    id("app.cash.paparazzi") apply false
}
