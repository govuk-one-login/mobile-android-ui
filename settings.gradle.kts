pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }

    plugins {
        id("com.android.library") version "7.4.2"
        id("org.jetbrains.kotlin.android") version "1.7.20"
        id("de.fayard.refreshVersions") version "0.60.2"
        id("app.cash.paparazzi") version "1.2.0"
    }
}

plugins {
    id("de.fayard.refreshVersions")
}

refreshVersions {
    enableBuildSrcLibs()
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "di-mobile-android-ui"
include(":theme")
include(":components")
include(":pages")
