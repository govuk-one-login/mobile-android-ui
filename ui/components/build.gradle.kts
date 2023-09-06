buildscript {
    dependencies {
        classpath(Android.tools.build.gradlePlugin)
    }
}

plugins {
    id("com.android.library")
    id("kotlin-parcelize")
    id("org.jetbrains.kotlin.android")
    id("org.jlleitschuh.gradle.ktlint")
    id("io.gitlab.arturbosch.detekt")
    id("jacoco")
    id("app.cash.paparazzi")
}

apply(from = "${rootProject.extra["configDir"]}/detekt/config.gradle")
apply(from = "${rootProject.extra["configDir"]}/ktlint/config.gradle")

android {
    namespace = "uk.gov.ui.components"
     compileSdk = (rootProject.extra["compileAndroidVersion"] as Int)

    defaultConfig {
        minSdk = (rootProject.extra["minAndroidVersion"] as Int)

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    lint {
        abortOnError = true
        absolutePaths = true
        baseline = File("${rootProject.extra["configDir"]}/android/baseline.xml")
        checkAllWarnings = true
        checkDependencies = false
        checkGeneratedSources = false
        checkReleaseBuilds = true
        disable.addAll(
            setOf(
                "ConvertToWebp",
                "UnusedIds",
                "VectorPath"
            )
        )
        explainIssues = true
        htmlReport = true
        ignoreTestSources = true
        ignoreWarnings = false
        lintConfig = File("${rootProject.extra["configDir"]}/android/lint.xml")
        noLines = false
        quiet = false
        showAll = true
        textReport = true
        warningsAsErrors = true
        xmlReport = true
    }
    testCoverage {
        jacocoVersion = (rootProject.extra["dep_jacoco"] as String)
    }

    testOptions {
        execution = "ANDROIDX_TEST_ORCHESTRATOR"
        animationsDisabled = true
        unitTests.all {
            it.testLogging {
                events = setOf(
                    org.gradle.api.tasks.testing.logging.TestLogEvent.FAILED,
                    org.gradle.api.tasks.testing.logging.TestLogEvent.PASSED,
                    org.gradle.api.tasks.testing.logging.TestLogEvent.SKIPPED
                )
            }
        }
        unitTests {
            isReturnDefaultValues = true
            isIncludeAndroidResources = true
        }
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = (
            rootProject.extra["composeKotlinCompilerVersion"] as String
            )
    }
}

dependencies {
    androidTestImplementation(AndroidX.compose.ui.testJunit4)
    androidTestImplementation(AndroidX.compose.ui.testManifest)
    androidTestImplementation(AndroidX.test.espresso.core)
    androidTestImplementation(AndroidX.test.ext.junit)

    androidTestUtil(AndroidX.test.orchestrator)

    implementation(AndroidX.activity.compose)
    implementation(AndroidX.appCompat)
    implementation(AndroidX.compose.material3)
    implementation(AndroidX.compose.material)
    implementation(AndroidX.compose.ui.tooling)
    implementation(AndroidX.constraintLayout.compose)
    implementation(AndroidX.core.ktx)
    implementation(project(":ui:theme"))

    testImplementation(AndroidX.archCore.testing)
    testImplementation(Google.dagger.hilt.android.testing)
    testImplementation(Testing.junit4)
    testImplementation(Testing.mockito.core)
}

jacoco {
    toolVersion = (rootProject.extra["dep_jacoco"] as String)
}

// --
// Set JVM to 11 for paparazzi tests
tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
    kotlinOptions.jvmTarget = "11"
}
tasks.withType<Test>().configureEach {
    javaLauncher.set(
        javaToolchains.launcherFor {
            languageVersion.set(JavaLanguageVersion.of(11))
        }
    )
}
// --
