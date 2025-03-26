import uk.gov.pipelines.config.ApkConfig
import uk.gov.pipelines.emulator.EmulatorConfig
import uk.gov.pipelines.emulator.SystemImageSource

buildscript {
    val configDir by rootProject.extra { "$rootDir/config" }
    val composeKotlinCompilerVersion by rootProject.extra { "1.5.0" }
    val buildLogicDir: String by rootProject.extra("mobile-android-pipelines/buildLogic")

    // Github packages publishing configuration
    val githubRepositoryName: String by rootProject.extra("mobile-android-ui")
    val mavenGroupId: String by rootProject.extra("uk.gov.android")
    // Sonar configuration
    val sonarProperties: Map<String, String> by rootProject.extra(
        mapOf(
            "sonar.projectKey" to "mobile-android-ui",
            "sonar.projectName" to "mobile-android-ui"
        )
    )

    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }

    // https://issuetracker.google.com/issues/380600747
    dependencies {
        classpath("org.bouncycastle:bcutil-jdk18on:1.80")
    }
}

val apkConfig by rootProject.extra(
    object : ApkConfig {
        override val applicationId: String = "uk.gov.android.ui"
        override val debugVersion: String = "DEBUG_VERSION"
        override val sdkVersions = object : ApkConfig.SdkVersions {
            override val minimum = 29
            override val target = 35
            override val compile = 35
        }
    }
)

val emulatorConfig by rootProject.extra(
    EmulatorConfig(
        systemImageSources = setOf(SystemImageSource.GOOGLE_ATD),
        androidApiLevels = setOf(33),
        deviceFilters = setOf("Pixel XL"),
    )
)

plugins {
    id("uk.gov.pipelines.vale-config")
    id("uk.gov.pipelines.sonarqube-root-config")
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.compose.compiler) apply false
    alias(libs.plugins.ktlint) apply false
    alias(libs.plugins.detekt) apply false
}