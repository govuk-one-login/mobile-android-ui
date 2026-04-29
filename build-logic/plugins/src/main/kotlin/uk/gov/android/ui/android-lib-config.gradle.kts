package uk.gov.android.ui

import com.android.build.api.dsl.LibraryExtension

project.plugins.apply("uk.gov.pipelines.android-lib-config")
project.plugins.apply("uk.gov.android.ui.lint-config")
project.plugins.apply("uk.gov.android.ui.compose-config")
project.plugins.apply("uk.gov.android.ui.test-config")
project.plugins.apply("uk.gov.android.ui.packaging-config")

configure<LibraryExtension> {
    buildTypes {
        release {
            isMinifyEnabled = false
        }
        debug {
            enableAndroidTestCoverage = true
            enableUnitTestCoverage = true
        }
    }
}
