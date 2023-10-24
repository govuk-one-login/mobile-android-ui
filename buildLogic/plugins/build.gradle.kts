plugins {
    `kotlin-dsl`
}

val kotlinVersion by rootProject.extra("1.9.0")
val sonarqubeVersion by rootProject.extra("4.3.0.3225")

dependencies {
    listOf(
        "com.android.tools.build:gradle:8.0.0",
        "com.google.devtools.ksp:com.google.devtools.ksp.gradle.plugin:1.9.10-1.0.13",
        "org.jetbrains.dokka:org.jetbrains.dokka.gradle.plugin:$kotlinVersion",
        "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion",
        "org.jetbrains.dokka:android-documentation-plugin:$kotlinVersion",
        "org.sonarqube:org.sonarqube.gradle.plugin:$sonarqubeVersion",
    ).forEach { dependency ->
        implementation(dependency)
    }
}
