import uk.gov.android.ui.extensions.namespace

plugins {
    id("uk.gov.android.ui.android-lib-config")
}

android {
    namespace("theme", project)

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
    implementation(libs.core.ktx)
    implementation(libs.appcompat)
    implementation(libs.bundles.compose)
    implementation(libs.androidx.activity.compose)

    testImplementation(libs.junit.jupiter)
    testRuntimeOnly(libs.junit.vintage)
    testImplementation(platform(libs.junit.bom))
}

mavenPublishingConfig {
    mavenConfigBlock {
        name.set(
            "Mobile Android Component Library",
        )
        description.set(
            """
            Make services look and feel like GOV.UK using styles.
            """.trimIndent(),
        )
    }
}
