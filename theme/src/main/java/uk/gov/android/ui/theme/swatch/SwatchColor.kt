package uk.gov.android.ui.theme.swatch

import androidx.compose.ui.graphics.Color

data class SwatchColor(
    val backgroundColor: Color,
    val label: String,
    val color: Color = Color.Unspecified
)
