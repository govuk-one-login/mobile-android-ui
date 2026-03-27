package uk.gov.android.ui

import com.android.build.gradle.BaseExtension

configure<BaseExtension> {
    packagingOptions {
        listOf(
            "META-INF/AL2.0",
            "META-INF/LGPL2.1",
        ).forEach(resources.excludes::plusAssign)
    }
}
