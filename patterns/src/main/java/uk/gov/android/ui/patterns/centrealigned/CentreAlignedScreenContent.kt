package uk.gov.android.ui.patterns.centrealigned

import androidx.annotation.DrawableRes
import kotlinx.collections.immutable.ImmutableList
import uk.gov.android.ui.componentsv2.bulletedlist.BulletedListTitle

internal data class CentreAlignedScreenContent(
    val title: String,
    val image: CentreAlignedScreenImage? = null,
    val body: ImmutableList<CentreAlignedScreenBodyContent>? = null,
    val supportingText: String? = null,
    val primaryButton: CentreAlignedScreenButton.Primary? = null,
    val secondaryButton: CentreAlignedScreenButton.Secondary? = null,
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

sealed interface CentreAlignedScreenButton {
    val text: String
    val onClick: () -> Unit

    data class Primary(
        override val text: String,
        override val onClick: () -> Unit,
    ) : CentreAlignedScreenButton

    data class Secondary(
        override val text: String,
        override val onClick: () -> Unit,
    ) : CentreAlignedScreenButton
}
