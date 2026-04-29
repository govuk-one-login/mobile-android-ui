package uk.gov.android.ui.extensions

import com.android.build.gradle.BaseExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.extra
import org.gradle.kotlin.dsl.provideDelegate
import uk.gov.pipelines.config.ApkConfig

fun BaseExtension.namespace(suffix: String, project: Project) {
    val apkConfig: ApkConfig by project.rootProject.extra
    namespace = "${apkConfig.applicationId}.$suffix"
}
