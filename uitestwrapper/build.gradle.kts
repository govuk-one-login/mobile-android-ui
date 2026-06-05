plugins {
    id("uk.gov.android.ui.android-app-config")
    alias(libs.plugins.kotlin.parcelize)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "uk.gov.android.ui.uitestwrapper"

    defaultConfig {
        applicationId = "uk.gov.android.ui.uitestwrapper"
        versionCode = 1
        versionName = "1.0"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {
    implementation(libs.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.compose.ui.preview)
    implementation(libs.androidx.compose.material3)
    implementation(libs.kotlinx.collections.immutable)
    implementation(projects.componentsv2)
    implementation(projects.componentsv2.componentsv2Camera)
    implementation(project(":theme"))
    implementation(projects.patterns)
    implementation(projects.patterns.patternsCamera)
    implementation(libs.androidx.fragment.ktx)
    implementation(libs.androidx.navigation.runtime.ktx)
    implementation(libs.navigation.compose)
    implementation(libs.androidx.compose.adaptive)
    implementation(libs.androidx.compose.adaptive.navigation)
    implementation(libs.androidx.compose.adaptive.layout)
    implementation(libs.kotlinx.serialization.json)

    testFixturesApi(testFixtures(projects.componentsv2))
    testFixturesApi(testFixtures(projects.componentsv2.componentsv2Camera))
    testFixturesApi(libs.androidx.ui.test.junit4.android)
    testFixturesImplementation(libs.kotlin.stdlib)
    testFixturesImplementation(libs.androidx.navigation.testing)
    testFixturesImplementation(libs.navigation.compose)

    testImplementation(libs.androidx.navigation.testing)

    androidTestImplementation(libs.androidx.test.rules)
    androidTestImplementation(libs.mockito.android)
    androidTestImplementation(testFixtures(projects.componentsv2))
    androidTestImplementation(testFixtures(projects.componentsv2.componentsv2Camera))
}
