import uk.gov.android.ui.extensions.namespace

plugins {
    id("uk.gov.android.ui.android-lib-config")
}

android {
    namespace("accessibility", project)

    buildTypes {
        release {
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
            )
        }
    }
}

dependencies {
    listOf(
        libs.androidx.activity.compose,
        libs.androidx.compose.material3,
        libs.androidx.compose.ui,
        libs.androidx.test.rules,
        libs.appcompat,
        libs.core.ktx,
    ).forEach(::androidTestImplementation)

    listOf(
        libs.androidx.activity.compose,
        libs.androidx.test.ext.junit,
        libs.androidx.compose.ui.junit4,
        libs.androidx.compose.ui.junit4.accessibility,
    ).forEach(::testFixturesApi)
}

mavenPublishingConfig {
    mavenConfigBlock {
        name.set(
            "GDS Accessibility testing",
        )
        description.set(
            """
            Utilities to enable accessibility testing for Compose UI.
            """.trimIndent(),
        )
    }
}
