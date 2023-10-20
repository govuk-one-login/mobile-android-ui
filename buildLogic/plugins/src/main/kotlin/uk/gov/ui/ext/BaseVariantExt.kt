package uk.gov.ui.ext

import com.android.build.api.variant.Variant
import com.android.build.gradle.api.BaseVariant
import org.gradle.configurationcache.extensions.capitalized

@Suppress("DEPRECATION") // having to use `LibraryExtension` which exposes [BaseVariant]
object BaseVariantExt {
    val Variant.capitalisedName: String get() = name.capitalized()
    val Variant.capitalisedFlavorName: String get() = flavorName?.capitalized() ?: capitalisedName

    val BaseVariant.capitalisedName: String get() = name.capitalized()
    val BaseVariant.capitalisedFlavorName: String get() = flavorName.capitalized()
}
