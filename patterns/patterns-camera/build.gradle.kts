import uk.gov.android.ui.extensions.namespace

plugins {
    id("uk.gov.android.ui.android-lib-config")
    id("kotlin-parcelize")
    id("uk.gov.android.ui.disable-javadoc")
}

android {
    namespace("patterns.camera", project)
}

apply(from = rootProject.file("gradle/snapshot-test-filter.gradle.kts"))

dependencies {
    api(projects.theme)
    api(projects.componentsv2.componentsv2Camera)
    api(libs.bundles.qr.code.scanning)

    implementation(libs.accompanist.permissions)
    implementation(libs.androidx.activity.compose)
    implementation(libs.appcompat)
    implementation(libs.bundles.compose)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.core.ktx)
    implementation(libs.kotlinx.collections.immutable)
    implementation(projects.componentsv2)

    androidTestImplementation(libs.androidx.test.rules)

    testFixturesImplementation(libs.bundles.compose)

    testImplementation(testFixtures(projects.componentsv2))
}

mavenPublishingConfig {
    mavenConfigBlock {
        name.set(
            "Mobile Android Patterns Library",
        )
        description.set(
            """
            Patterns are best practice design solutions for specific user-focused tasks and pages.
            """.trimIndent(),
        )
    }
}
