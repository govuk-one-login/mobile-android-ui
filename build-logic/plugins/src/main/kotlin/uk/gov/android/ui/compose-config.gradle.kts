package uk.gov.android.ui

import com.android.build.api.dsl.CommonExtension
import org.gradle.accessors.dm.LibrariesForLibs

val libs = the<LibrariesForLibs>()

project.plugins.apply(libs.plugins.compose.compiler.get().pluginId)

configure<CommonExtension> {
    buildFeatures.compose = true
}

dependencies {
    val composeBom = platform(libs.androidx.compose.bom)
    "implementation"(composeBom)
    "androidTestImplementation"(composeBom)
    "testFixturesImplementation"(composeBom)

    "debugImplementation"(libs.androidx.compose.ui.tooling)
    "debugImplementation"(libs.androidx.compose.ui.testmanifest)

    "lintChecks"(libs.com.slack.compose.lint.checks)
}
