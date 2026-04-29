package uk.gov.android.ui

import com.android.build.gradle.BaseExtension
import org.gradle.accessors.dm.LibrariesForLibs
import org.gradle.api.tasks.testing.logging.TestLogEvent

val libs = the<LibrariesForLibs>()

project.plugins.apply("app.cash.paparazzi")

configure<BaseExtension> {
    testOptions {
        execution = "ANDROIDX_TEST_ORCHESTRATOR"
        animationsDisabled = true
        unitTests.all {
            it.testLogging {
                events = setOf(
                    TestLogEvent.FAILED,
                    TestLogEvent.PASSED,
                    TestLogEvent.SKIPPED,
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
    "testImplementation"(libs.arch.core)
    "testImplementation"(libs.hilt.android.testing)
    "testImplementation"(libs.junit)
    "testImplementation"(libs.mockito.kotlin)
    "testImplementation"(libs.androidx.ui.test.android)
    "testImplementation"(libs.androidx.ui.test.junit4.android)
    "testImplementation"(libs.robolectric)

    "androidTestImplementation"(libs.androidx.test.ext.junit)
    "androidTestImplementation"(libs.androidx.compose.ui.junit4)
    "androidTestImplementation"(libs.androidx.test.espresso.core)
    "androidTestUtil"(libs.androidx.test.orchestrator)
}
