// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    extra.apply {
        set("configDir", "$rootDir/config")
        set("dep_jacoco", "0.8.8")
        set("minAndroidVersion", 29)
        set("compileAndroidVersion", 33)
        set("androidBuildToolsVersion", "33.0.0")
        set("composeKotlinCompilerVersion", "1.5.0")
    }
}

plugins {
    id("maven-publish")
    id("java-library")
    id("org.jetbrains.kotlin.android") version "1.9.0" apply false
    id("com.android.library") version "8.1.1" apply false
    id("org.jlleitschuh.gradle.ktlint") version "11.5.0" apply false
    id("io.gitlab.arturbosch.detekt") version "1.23.1" apply false
    id("app.cash.paparazzi") apply false
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
    withJavadocJar()
    withSourcesJar()
}

//subprojects {
//    apply(plugin = "maven-publish")
//
//    if (project.name != "app" && project.name != "ui") {
//        publishing {
//            publications {
//                create<MavenPublication>("maven") {
//                    groupId = "uk.gov.android"
//                    version = "1.0"
//
//                    artifact("$buildDir/outputs/aar/${project.name}-release.aar")
//                }
//            }
//            repositories {
//                maven {
//                    val propsFile = rootProject.file("github.properties")
//                    val props = Properties()
//                    props.load(FileInputStream(propsFile))
//                    name = "GitHubPackages"
//                    url = uri("https://maven.pkg.github.com/alphagov/di-mobile-android-ui")
//                    credentials {
//                        username = props["username"].toString()
//                        password = props["token"].toString()
//                    }
//                }
//            }
//        }
//    }
//}
