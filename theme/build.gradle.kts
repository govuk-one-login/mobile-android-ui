import java.io.FileInputStream
import java.util.Properties

buildscript {
    dependencies {
        classpath(Android.tools.build.gradlePlugin)
    }
}

plugins {
    listOf(
        "com.android.library",
        "org.jetbrains.kotlin.android",
        "org.jlleitschuh.gradle.ktlint",
        "io.gitlab.arturbosch.detekt",
        "jacoco",
        "maven-publish"
    ).forEach(::id)
}

apply(from = "${rootProject.extra["configDir"]}/detekt/config.gradle")
apply(from = "${rootProject.extra["configDir"]}/ktlint/config.gradle")

android {
    namespace = "uk.gov.ui.theme"
    compileSdk = (rootProject.extra["compileAndroidVersion"] as Int)

    defaultConfig {
        minSdk = (rootProject.extra["minAndroidVersion"] as Int)
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
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
            it.useJUnitPlatform()
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
    implementation(AndroidX.core.ktx)
    implementation(AndroidX.appCompat)
    implementation(AndroidX.compose.material3)
    implementation(AndroidX.compose.material)
    implementation(AndroidX.compose.ui.tooling)
    implementation(AndroidX.activity.compose)
    implementation(Google.android.material)
    androidTestImplementation(AndroidX.test.ext.junit)
    androidTestImplementation(AndroidX.compose.ui.testJunit4)
    androidTestImplementation(AndroidX.compose.ui.testManifest)
    androidTestImplementation(AndroidX.test.espresso.core)

    testImplementation(AndroidX.archCore.testing)
    testImplementation(Google.dagger.hilt.android.testing)
    testImplementation(Testing.junit.jupiter)
    testImplementation(Testing.mockito.core)
    testImplementation(platform(Testing.junit.bom))
}

jacoco {
    toolVersion = (rootProject.extra["dep_jacoco"] as String)
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = "uk.gov.android"
            version = "1.0"

            artifact("$buildDir/outputs/aar/${project.name}-release.aar")
        }
    }
    repositories {
        maven {
            val propsFile = rootProject.file("github.properties")
            val props = Properties()
            props.load(FileInputStream(propsFile))
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/alphagov/di-mobile-android-ui")
            credentials {
                username = props["username"].toString()
                password = props["token"].toString()
            }
        }
    }
}