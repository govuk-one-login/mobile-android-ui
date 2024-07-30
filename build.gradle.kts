import uk.gov.pipelines.config.ApkConfig

buildscript {
    val configDir by rootProject.extra { "$rootDir/config" }
    val projectKey: String by rootProject.extra("mobile-android-ui")
    val projectId: String by rootProject.extra("uk.gov.android")
    val composeKotlinCompilerVersion by rootProject.extra { "1.6.8" }

    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}

val apkConfig by rootProject.extra(
    object: ApkConfig {
        override val applicationId: String = "uk.gov.android.ui"
        override val debugVersion: String = "DEBUG_VERSION"
        override val sdkVersions = object: ApkConfig.SdkVersions {
            override val minimum = 29
            override val target = 33
            override val compile = 34
        }
    }
)

plugins {
    id("uk.gov.pipelines.vale-config")
    id("uk.gov.pipelines.sonarqube-root-config")
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.android.application) apply false
}