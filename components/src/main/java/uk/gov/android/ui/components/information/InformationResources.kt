package uk.gov.android.ui.components.information

import android.os.Parcelable
import androidx.annotation.DrawableRes
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize
import uk.gov.android.ui.components.content.GdsContentText
import uk.gov.android.ui.components.images.icon.IconResources

@Parcelize
data class InformationResources(
    val icon: IconResources,
    val content: List<GdsContentText>,
) : Parcelable {

    @get:DrawableRes
    @IgnoredOnParcel
    val image: Int get() = icon.image

    fun getSubtitle(index: Int = 0) = content[index].subTitle
}
