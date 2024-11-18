package uk.gov.android.ui.components.images.icon

import android.os.Parcelable
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import kotlinx.parcelize.Parcelize

@Parcelize
data class IconResources(
    @DrawableRes
    val image: Int,
    @StringRes
    val description: Int? = null
) : Parcelable
