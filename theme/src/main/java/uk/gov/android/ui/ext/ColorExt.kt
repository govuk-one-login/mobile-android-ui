package uk.gov.android.ui.ext

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.core.graphics.ColorUtils
import java.util.Locale

internal const val LUMINANCE_THRESHOLD = 0.5
internal const val MASK = 0xFFFFFF

fun Color.isDark(): Boolean {
    return ColorUtils.calculateLuminance(this.toArgb()) < LUMINANCE_THRESHOLD
}

fun Color.isLight(): Boolean = !this.isDark()

fun Color.toHexString(): String {
    return String.format(Locale.ENGLISH, "#%06X", (MASK and this.toArgb()))
}
