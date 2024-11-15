buildscript {
    dependencies {
        classpath(Android.tools.build.gradlePlugin)
    }
}

plugins {
    listOf(
        "com.android.library",
        "org.jlleitschuh.gradle.ktlint",
        "io.gitlab.arturbosch.detekt",
        "jacoco",
        "app.cash.paparazzi",
        "kotlin-parcelize",
        "maven-publish",
        "uk.gov.ui.jvm-toolchains",
        "uk.gov.ui.sonarqube-module-config",
        "uk.gov.ui.jacoco-module-config",
        "uk.gov.ui.emulator-config"
    ).forEach(::id)
}

apply(from = "${rootProject.extra["configDir"]}/detekt/config.gradle")
apply(from = "${rootProject.extra["configDir"]}/ktlint/config.gradle")

android {
    namespace = "${rootProject.extra["baseNamespace"]}.pages"
    compileSdk = (rootProject.extra["compileAndroidVersion"] as Int)

    defaultConfig {
        minSdk = (rootProject.extra["minAndroidVersion"] as Int)
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = (
            rootProject.extra["composeKotlinCompilerVersion"] as String
            )
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        debug {
            enableAndroidTestCoverage = true
            enableUnitTestCoverage = true
        }
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
}

dependencies {
    implementation(AndroidX.activity.compose)
    implementation(AndroidX.appCompat)
    implementation(AndroidX.compose.material)
    implementation(AndroidX.compose.ui.tooling)
    implementation(AndroidX.constraintLayout.compose)
    implementation(AndroidX.core.ktx)
    implementation(Google.android.material)
    implementation(AndroidX.compose.material3)
    implementation(project(":components"))
    implementation(project(":theme"))

    androidTestImplementation(AndroidX.test.ext.junit)
    androidTestImplementation(AndroidX.compose.ui.testJunit4)
    androidTestImplementation(AndroidX.compose.ui.testManifest)
    androidTestImplementation(AndroidX.test.espresso.core)

    listOf(
        AndroidX.archCore.testing,
        Google.dagger.hilt.android.testing,
        Testing.junit4,
        Testing.mockito.core
    ).forEach { testDependency ->
        testImplementation(testDependency)
    }

    androidTestUtil(AndroidX.test.orchestrator)
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = "uk.gov.android"
            version = rootProject.extra["packageVersion"] as String

            artifact("${layout.buildDirectory}/outputs/aar/${project.name}-release.aar")
        }
    }
    repositories {
        maven("https://maven.pkg.github.com/govuk-one-login/mobile-android-ui") {
            credentials {
                username = System.getenv("USERNAME")
                password = System.getenv("TOKEN")
            }
        }
    }
}
