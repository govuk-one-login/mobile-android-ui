package uk.gov.ui.ext

import com.android.build.gradle.LibraryExtension
import com.android.build.api.dsl.LibraryExtension as DslLibraryExtension

/**
 * Wrapper object for containing extension functions relating to various implementations of the
 * [LibraryExtension], such as the [DslLibraryExtension] variant.
 */
object LibraryExtensionExt {
    /**
     * Apply Jacoco configurations to the `android` block of a gradle module.
     *
     * Sets up the `android.testCoverage.jacocoVersion` property with the [version] parameter.
     * Also enables instrumentation and unit test coverage.
     *
     * @param version The Jacoco version number as a string.
     */
    fun LibraryExtension.decorateLibraryExtensionWithJacoco(version: String) {
        testCoverage.jacocoVersion = version
        buildTypes {
            debug {
                this.enableAndroidTestCoverage = true
                this.enableUnitTestCoverage = true
            }
        }
    }

    /**
     * Apply Jacoco configurations to the `android` block of a gradle module.
     *
     * Sets up the `android.testCoverage.jacocoVersion` property with the [version] parameter.
     * Also enables instrumentation and unit test coverage.
     *
     * @param version The Jacoco version number as a string.
     */
    fun DslLibraryExtension.decorateDslLibraryExtensionWithJacoco(
        version: String,
    ) {
        testCoverage.jacocoVersion = version
        buildTypes {
            debug {
                this.enableAndroidTestCoverage = true
                this.enableUnitTestCoverage = true
            }
        }
    }
}
