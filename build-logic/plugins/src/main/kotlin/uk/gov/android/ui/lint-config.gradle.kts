package uk.gov.android.ui

import com.android.build.api.dsl.LibraryExtension
import org.gradle.accessors.dm.LibrariesForLibs
import org.jlleitschuh.gradle.ktlint.KtlintExtension

val libs = the<LibrariesForLibs>()

project.extensions.findByType<LibraryExtension>()?.run {
    lint {
        baseline = project.file("lint-baseline.xml")
    }
}
