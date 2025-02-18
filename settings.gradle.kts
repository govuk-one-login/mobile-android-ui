pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}

rootProject.name = "mobile-android-ui"
include(":components")
include(":pages")
includeBuild("${rootProject.projectDir}/mobile-android-pipelines/buildLogic")
include(":componentsv2")
include(":patterns")
include(":theme")
include(":sample")
