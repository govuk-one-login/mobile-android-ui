package uk.gov.android.ui.theme.ext

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.graphics.toArgb
import androidx.core.graphics.ColorUtils
import java.util.Locale

internal const val LUMINANCE_THRESHOLD = 0.5
internal const val MASK = 0xFFFFFF
internal const val MASK_ALPHA = 0xFFFFFFFF

fun Color.isDark(): Boolean = this.luminance() < LUMINANCE_THRESHOLD

fun Color.isLight(): Boolean = !this.isDark()

fun Color.toHex(): Int = (MASK and this.toArgb())

fun Color.toHexWithAlpha(): Long = (MASK_ALPHA and this.toArgb().toLong())

fun Color.toHexString(): String =
    String.format(Locale.ENGLISH, "#%06X", toHex())

fun Color.toHexStringWithAlpha(): String =
    String.format(Locale.ENGLISH, "#%08X", toHexWithAlpha())
