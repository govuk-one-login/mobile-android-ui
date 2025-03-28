package uk.gov.android.ui.patterns.centrealignedscreen

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import kotlinx.collections.immutable.ImmutableList
import uk.gov.android.ui.componentsv2.bulletedlist.BulletedListTitle
import uk.gov.android.ui.componentsv2.bulletedlist.GdsBulletedList
import uk.gov.android.ui.theme.m3.Typography
import uk.gov.android.ui.theme.spacingDouble

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

internal fun LazyListScope.toBodyContent(
    body: ImmutableList<CentreAlignedScreenBodyContent>? = null,
    horizontalItemPadding: Dp,
) {
    val itemPadding = PaddingValues(horizontal = horizontalItemPadding)
    body?.forEachIndexed { i, item ->
        when (item) {
            is CentreAlignedScreenBodyContent.Text -> {
                item {
                    Text(
                        text = item.bodyText,
                        style = Typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onBackground,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(itemPadding),
                    )
                }
            }

            is CentreAlignedScreenBodyContent.BulletList -> {
                item {
                    GdsBulletedList(
                        item.items,
                        Modifier
                            .fillMaxWidth()
                            .padding(itemPadding),
                        item.title,
                    )
                }
            }
        }

        if (i < body.lastIndex) {
            item {
                Spacer(modifier = Modifier.height(spacingDouble))
            }
        }
    }
}
