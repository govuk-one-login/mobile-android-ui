import uk.gov.pipelines.config.ApkConfig

plugins {
    id("uk.gov.pipelines.android-lib-config")
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.paparazzi)
    id("kotlin-parcelize")
}

android {
    defaultConfig {
        val apkConfig: ApkConfig by project.rootProject.extra
        namespace = "${apkConfig.applicationId}.patterns"
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
    kotlinOptions {
        jvmTarget = "17"
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
                events =
                    setOf(
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
    implementation(composeBom)
    androidTestImplementation(composeBom)

    implementation(libs.androidx.activity.compose)
    implementation(libs.appcompat)
    implementation(libs.bundles.compose)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.core.ktx)
    implementation(libs.kotlinx.collections.immutable)
    implementation(libs.material)
    implementation(project(":componentsv2"))
    implementation(project(":theme"))

    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.testmanifest)

    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.androidx.compose.ui.junit4)
    androidTestImplementation(libs.androidx.test.espresso.core)
    androidTestUtil(libs.androidx.test.orchestrator)

    testImplementation(libs.androidx.ui.test.android)
    testImplementation(libs.androidx.ui.test.junit4.android)
    testImplementation(libs.arch.core)
    testImplementation(libs.hilt.android.testing)
    testImplementation(libs.junit)
    testImplementation(libs.mockito.kotlin)
    testImplementation(libs.robolectric)
    lintChecks(libs.com.slack.compose.lint.checks)
}

// https://github.com/Kotlin/dokka/issues/2956
tasks
    .matching { task ->
        task.name.contains("javaDocReleaseGeneration", ignoreCase = true) or
            task.name.contains("javaDocDebugGeneration")
    }.configureEach {
        enabled = false
    }

mavenPublishingConfig {
    mavenConfigBlock {
        name.set(
            "Mobile Android Patterns Library",
        )
        description.set(
            """
            Patterns are best practice design solutions for specific user-focused tasks and pages.
            """.trimIndent(),
        )
    }
}

android {
    lint {
        baseline = file("lint-baseline.xml")
    }
}

//afterEvaluate {
//    tasks.named("testDebugUnitTest") {
//        finalizedBy("verifyPaparazziDebug")
//    }
//}
