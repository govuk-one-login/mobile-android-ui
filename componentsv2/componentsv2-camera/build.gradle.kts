import uk.gov.android.ui.extensions.namespace

plugins {
    id("uk.gov.android.ui.android-lib-config")
    id("kotlin-parcelize")
}

android {
    namespace("componentsv2.camera", project)
}

dependencies {
    api(libs.bundles.qr.code.scanning)
    api(projects.componentsv2)
    api(libs.accompanist.permissions)

    implementation(libs.androidx.compose.material.icons)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.preview)
    implementation(libs.core.ktx)
    implementation(projects.theme)

    androidTestImplementation(libs.androidx.test.rules)
    androidTestImplementation(libs.mockito.android)

    testFixturesApi(libs.bundles.qr.code.scanning)
    testFixturesApi(testFixtures(projects.componentsv2))
    testFixturesApi(libs.androidx.ui.test.android)
    testFixturesImplementation(libs.kotlin.stdlib)
    testFixturesImplementation(libs.androidx.ui.test.junit4.android)
    testFixturesImplementation(libs.mockito.kotlin)

    testImplementation(libs.androidx.test.rules)
}

mavenPublishingConfig {
    mavenConfigBlock {
        name.set(
            "Mobile Android Component Library Version 2",
        )
        description.set(
            """
            Use pre-built reusable components to build consistent apps - this will replace the
            components module with a more standardised approach.
            """.trimIndent(),
        )
    }
}
