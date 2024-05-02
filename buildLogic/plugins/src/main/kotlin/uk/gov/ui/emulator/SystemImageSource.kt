package uk.gov.ui.emulator

import com.android.build.api.dsl.ManagedVirtualDevice
import uk.gov.ui.ext.StringExtensions.kebabToLowerCamelCase
import uk.gov.ui.ext.StringExtensions.snakeToLowerCamelCase

/**
 * Collection to hold applicable values for the [ManagedVirtualDevice.systemImageSource]. Look at
 * the output of the `sdkmanager --list` command for other system source images.
 *
 * @see ManagedVirtualDevice.systemImageSource
 */
enum class SystemImageSource(val image: String) {

    /**
     * Android Open Source Project (AOSP) system image. Doesn't contain google services.
     */
    AOSP("aosp"),

    /**
     * AOSP Android Test Device (ATD) system image. Doesn't contain google services. Look into the
     * [ATD optimisations](https://developer.android.com/studio/test/gradle-managed-devices#atd-optimizations)
     * to see what's removed from the image.
     */
    AOSP_ATD("aosp-atd"),

    /**
     * System image containing google services.
     */
    GOOGLE("google"),

    /**
     * Android Test Device system image containing google services. Look into the
     * [ATD optimisations](https://developer.android.com/studio/test/gradle-managed-devices#atd-optimizations)
     * to see what's removed from the image.
     */
    GOOGLE_ATD("google-atd"),

    /**
     * System image that contains google play store pre-installed.
     */
    GOOGLE_PLAYSTORE("google_apis_playstore");

    /**
     * Reformat the [image] property. Called to act as a prefix for device
     * configurations, as well as the generated gradle tasks.
     */
    fun sanitise() = this.image.kebabToLowerCamelCase().snakeToLowerCamelCase()
}
