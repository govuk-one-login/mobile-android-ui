import uk.gov.pipelines.config.ApkConfig
import uk.gov.pipelines.emulator.SystemImageSource
import uk.gov.pipelines.emulator.EmulatorConfig

buildscript {
    val configDir by rootProject.extra { "$rootDir/config" }
    val githubRepositoryName by rootProject.extra { "mobile-android-ui" }
    val mavenGroupId by rootProject.extra { "uk.gov.android.ui" }
    val projectKey: String by rootProject.extra("mobile-android-ui")
    val projectId: String by rootProject.extra("uk.gov.android")
    val composeKotlinCompilerVersion by rootProject.extra { "1.5.0" }
    val buildLogicDir: String by extra("mobile-android-pipelines/buildLogic")
    val sonarProperties: Map<String, String> by rootProject.extra { mapOf() }

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
            override val target = 34
            override val compile = 34
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