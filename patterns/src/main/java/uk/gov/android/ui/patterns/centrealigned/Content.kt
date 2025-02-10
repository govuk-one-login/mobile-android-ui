package uk.gov.android.ui.patterns.centrealigned

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import uk.gov.android.ui.components.m3.BulletListParameters

data class Content(
    val title: String,
    val image: ImageResource? = null,
    val body: Body? = null,
    val supportingText: String? = null,
    val primaryButtonText: String? = null,
    val secondaryButtonText: String? = null,
)

data class Body(
    val bodyContentList: List<BodyContent>,
)

sealed class BodyContent {
    data class Text(val bodyText: String) : BodyContent()
    data class BulletList(val bulletList: BulletListParameters) : BodyContent()
}

data class ImageResource(
    @DrawableRes val image: Int,
    @StringRes val description: Int,
)
