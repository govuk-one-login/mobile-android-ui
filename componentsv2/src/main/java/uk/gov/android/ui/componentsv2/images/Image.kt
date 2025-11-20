package uk.gov.android.ui.componentsv2.images

import androidx.annotation.DrawableRes

data class Image (
    @DrawableRes
    val drawable: Int,
    val contentDescription: String,
)