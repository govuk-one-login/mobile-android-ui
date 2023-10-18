import org.sonarqube.gradle.SonarExtension

plugins {
    id("org.sonarqube")
}

private val _packageVersion: String = if (rootProject.hasProperty("packageVersion")) {
    rootProject.property("packageVersion") as String
} else {
    "1.0.0"
}

val packageVersion: String by rootProject.extra(
    _packageVersion,
)

val rootSonarProperties by rootProject.extra(
    mapOf(
        "sonar.host.url" to System.getProperty("uk.gov.ui.sonar.host.url"),
        "sonar.login" to System.getProperty("uk.gov.ui.sonar.login"),
        "sonar.projectKey" to "di-mobile-android-ui",
        "sonar.projectName" to "di-mobile-android-ui",
        "sonar.projectVersion" to packageVersion,
        "sonar.organization" to "govuk-one-login",
        "sonar.sourceEncoding" to "UTF-8",
    ),
)

configure<SonarExtension> {
    this.setAndroidVariant("debug")

    properties {
        rootSonarProperties.forEach { (key, value) ->
            property(key, value)
        }
    }
}
