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
includeBuild("${rootProject.projectDir}/mobile-android-pipelines/buildLogic")
include(":componentsv2")
include(":componentsv2:componentsv2-camera")
include(":patterns")
include(":patterns:patterns-camera")
include(":theme")
include(":testwrapper")
