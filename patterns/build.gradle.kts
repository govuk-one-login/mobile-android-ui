import uk.gov.android.ui.extensions.namespace

plugins {
    id("uk.gov.android.ui.android-lib-config")
    id("kotlin-parcelize")
    id("uk.gov.android.ui.disable-javadoc")
}

android {
    namespace("patterns", project)
}

apply(from = rootProject.file("gradle/snapshot-test-filter.gradle.kts"))

dependencies {
    implementation(libs.accompanist.permissions)
    implementation(libs.androidx.activity.compose)
    implementation(libs.appcompat)
    implementation(libs.bundles.compose)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.core.ktx)
    implementation(libs.kotlinx.collections.immutable)
    implementation(projects.componentsv2)
    implementation(projects.componentsv2.componentsv2Camera)
    implementation(project(":theme"))
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
