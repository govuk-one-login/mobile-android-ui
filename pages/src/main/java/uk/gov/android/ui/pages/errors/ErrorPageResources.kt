package uk.gov.android.ui.pages.errors

import android.os.Parcelable
import androidx.annotation.DrawableRes
import androidx.annotation.Keep
import androidx.annotation.StringRes
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize
import uk.gov.android.ui.components.information.InformationResources

@Keep
@Parcelize
data class ErrorPageResources(
    val information: InformationResources,
    @StringRes
    val primaryButtonText: Int,
    @StringRes
    val secondaryButtonText: Int?
) : Parcelable {
    @get:DrawableRes
    @IgnoredOnParcel
    val image: Int get() = information.image

    fun getSubtitle(index: Int = 0) = information.getSubtitle(index)
    fun getContent(index: Int = 0) = information.content[index]
}
