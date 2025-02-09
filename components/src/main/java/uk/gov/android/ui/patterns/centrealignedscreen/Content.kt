package uk.gov.android.ui.patterns.centrealignedscreen

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import uk.gov.android.ui.components.m3.BulletListParameters

data class Content(
    @StringRes val title: Int,
    val image: ImageResource? = null,
    val body: Body? = null,
    @StringRes val supportingText: Int? = null,
    @StringRes val primaryButtonText: Int? = null,
    @StringRes val secondaryButtonText: Int? = null,
)

data class Body(
    val bodyContentList: List<BodyContent>,
)

sealed class BodyContent {
    data class Text(@StringRes val bodyText: Int) : BodyContent()
    data class BulletList(val bulletList: BulletListParameters) : BodyContent()
}

data class ImageResource(
    @DrawableRes val image: Int,
    @StringRes val description: Int,
)
