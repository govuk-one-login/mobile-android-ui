package uk.gov.android.ui.ext

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.core.graphics.ColorUtils

fun Color.isDark(): Boolean {
    return ColorUtils.calculateLuminance(this.toArgb()) < 0.5
}

fun Color.isLight(): Boolean = !this.isDark()

fun Color.toHexString(): String {
    return String.format("#%06X", (0xFFFFFF and this.toArgb()))
}
