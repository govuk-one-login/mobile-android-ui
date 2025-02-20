package uk.gov.android.ui.patterns.centrealignedscreen

import androidx.annotation.DrawableRes
import kotlinx.collections.immutable.ImmutableList
import uk.gov.android.ui.componentsv2.bulletedlist.BulletedListTitle

internal data class CentreAlignedScreenContent(
    val title: String,
    val image: CentreAlignedScreenImage? = null,
    val body: ImmutableList<CentreAlignedScreenBodyContent>? = null,
    val supportingText: String? = null,
    val primaryButton: CentreAlignedScreenButton? = null,
    val secondaryButton: CentreAlignedScreenButton? = null,
)

sealed class CentreAlignedScreenBodyContent {
    data class Text(val bodyText: String) : CentreAlignedScreenBodyContent()
    data class BulletList(
        val title: BulletedListTitle? = null,
        val items: ImmutableList<String>,
    ) : CentreAlignedScreenBodyContent()
}

data class CentreAlignedScreenImage(
    @DrawableRes val image: Int,
    val description: String,
)

data class CentreAlignedScreenButton(
    val text: String,
    val onClick: () -> Unit,
)
