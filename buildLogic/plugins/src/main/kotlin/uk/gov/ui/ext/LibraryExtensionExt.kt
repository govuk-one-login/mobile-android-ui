package uk.gov.ui.ext

import com.android.build.api.dsl.LibraryExtension as DslLibraryExtension
import com.android.build.gradle.LibraryExtension

fun LibraryExtension.decorateLibraryExtensionWithJacoco(jacocoVersion: String) {
    testCoverage.jacocoVersion = jacocoVersion
    buildTypes {
        debug {
            this.enableAndroidTestCoverage = true
            this.enableUnitTestCoverage = true
            this.isTestCoverageEnabled = true
        }
    }
}

fun DslLibraryExtension.decorateDslLibraryExtensionWithJacoco(
    jacocoVersion: String
) {
    testCoverage.jacocoVersion = jacocoVersion
    buildTypes {
        debug {
            this.enableAndroidTestCoverage = true
            this.enableUnitTestCoverage = true
            this.isTestCoverageEnabled = true
        }
    }
}