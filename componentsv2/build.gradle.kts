import uk.gov.android.ui.extensions.namespace

plugins {
    id("uk.gov.android.ui.android-lib-config")
    id("kotlin-parcelize")
}

android {
    namespace("componentsv2", project)
}

dependencies {
    api(libs.accompanist.permissions)

    implementation(libs.androidx.activity.compose)
    implementation(libs.appcompat)
    implementation(libs.androidx.compose.material.icons)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.preview)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.core.ktx)
    implementation(libs.kotlinx.collections.immutable)
    implementation(project(":theme"))

    androidTestImplementation(libs.androidx.test.rules)
    androidTestImplementation(libs.mockito.android)

    testFixturesApi(libs.androidx.ui.test.android)
    testFixturesApi(libs.android.tools.layoutlib.api)
    testFixturesImplementation(libs.kotlin.stdlib)
    testFixturesImplementation(libs.androidx.ui.test.junit4.android)

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
