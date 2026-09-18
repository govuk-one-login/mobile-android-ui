package uk.gov.android.ui

import com.android.build.api.dsl.CommonExtension

configure<CommonExtension> {
    packaging.apply {
        listOf(
            "META-INF/AL2.0",
            "META-INF/LGPL2.1",
        ).forEach(resources.excludes::plusAssign)
    }
}
