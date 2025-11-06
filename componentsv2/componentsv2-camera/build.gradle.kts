import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import uk.gov.pipelines.config.ApkConfig

plugins {
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.paparazzi)
    id("uk.gov.pipelines.android-lib-config")
    id("kotlin-parcelize")
}

android {
    defaultConfig {
        val apkConfig: ApkConfig by project.rootProject.extra
        namespace = "${apkConfig.applicationId}.componentsv2.camera"
        compileSdk = apkConfig.sdkVersions.compile
        minSdk = apkConfig.sdkVersions.minimum
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures {
        compose = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlin {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_17)
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
        }
        debug {
            enableAndroidTestCoverage = true
            enableUnitTestCoverage = true
        }
    }

    @Suppress("UnstableApiUsage")
    testOptions {
        execution = "ANDROIDX_TEST_ORCHESTRATOR"
        animationsDisabled = true
        unitTests.all {
            it.testLogging {
                events = setOf(
                    org.gradle.api.tasks.testing.logging.TestLogEvent.FAILED,
                    org.gradle.api.tasks.testing.logging.TestLogEvent.PASSED,
                    org.gradle.api.tasks.testing.logging.TestLogEvent.SKIPPED,
                )
            }
        }
        unitTests {
            isReturnDefaultValues = true
            isIncludeAndroidResources = true
        }
    }

    ktlint {
        version = libs.versions.ktlint.cli.get()
    }
}

dependencies {
    val composeBom = platform(libs.androidx.compose.bom)
    androidTestImplementation(composeBom)
    implementation(composeBom)

    api(libs.bundles.qr.code.scanning)
    api(projects.componentsv2)
    api(libs.accompanist.permissions)

    implementation(libs.androidx.compose.material.icons)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.preview)
    implementation(libs.core.ktx)
    implementation(projects.theme)

    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.testmanifest)

    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.androidx.test.rules)
    androidTestImplementation(libs.androidx.compose.ui.junit4)
    androidTestImplementation(libs.androidx.test.espresso.core)
    androidTestImplementation(libs.mockito.android)
    androidTestUtil(libs.androidx.test.orchestrator)

    testFixturesApi(libs.bundles.qr.code.scanning)
    testFixturesApi(testFixtures(projects.componentsv2))
    testFixturesApi(libs.androidx.ui.test.android)
    testFixturesImplementation(libs.kotlin.stdlib)
    testFixturesImplementation(libs.androidx.ui.test.junit4.android)

    testImplementation(libs.androidx.test.rules)
    testImplementation(libs.androidx.ui.test.android)
    testImplementation(libs.androidx.ui.test.junit4.android)
    testImplementation(libs.arch.core)
    testImplementation(libs.hilt.android.testing)
    testImplementation(libs.junit)
    testImplementation(libs.mockito.kotlin)
    testImplementation(libs.robolectric)
    lintChecks(libs.com.slack.compose.lint.checks)
}

mavenPublishingConfig {
    mavenConfigBlock {
        name.set(
            "Mobile Android Component Library Version 2",
        )
        description.set(
            """
            Use pre-built reusable components to build consistent apps - this will replace the
            components module with a more standardised approach.
            """.trimIndent(),
        )
    }
}

android {
    lint {
        baseline = file("lint-baseline.xml")
    }
}
