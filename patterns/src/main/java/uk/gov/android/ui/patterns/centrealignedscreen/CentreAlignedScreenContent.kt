package uk.gov.android.ui.patterns.centrealignedscreen

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import kotlinx.collections.immutable.ImmutableList
import uk.gov.android.ui.componentsv2.R
import uk.gov.android.ui.componentsv2.button.ButtonType
import uk.gov.android.ui.componentsv2.button.GdsButton
import uk.gov.android.ui.componentsv2.button.customButtonColors
import uk.gov.android.ui.componentsv2.list.GdsBulletedList
import uk.gov.android.ui.componentsv2.list.GdsNumberedList
import uk.gov.android.ui.componentsv2.list.ListItem
import uk.gov.android.ui.componentsv2.list.ListTitle
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
    data class Text(
        val bodyText: String,
        val useBoldStyle: Boolean = false,
    ) : CentreAlignedScreenBodyContent()
    data class BulletList(
        val title: ListTitle? = null,
        val items: ImmutableList<String>,
    ) : CentreAlignedScreenBodyContent()
    data class NumberedList(
        val title: ListTitle? = null,
        val items: ImmutableList<ListItem>,
    ) : CentreAlignedScreenBodyContent()
    data class Button(
        val text: String,
        val onClick: () -> Unit,
        val leftAligned: Boolean = false,
        val showIcon: Boolean = false,
    ) : CentreAlignedScreenBodyContent()
}

data class CentreAlignedScreenImage(
    @DrawableRes val image: Int,
    val description: String,
)

data class CentreAlignedScreenButton(
    val text: String,
    val onClick: () -> Unit,
    val showIcon: Boolean = false,
    val enabled: Boolean = true,
)

@Suppress("LongMethod")
internal fun LazyListScope.toBodyContent(
    body: ImmutableList<CentreAlignedScreenBodyContent>? = null,
    horizontalItemPadding: Dp,
) {
    val itemPadding = PaddingValues(horizontal = horizontalItemPadding)
    body?.forEach { item ->
        when (item) {
            is CentreAlignedScreenBodyContent.Text -> {
                item {
                    val textStyle = if (item.useBoldStyle) {
                        Typography.bodyLarge.copy(fontWeight = FontWeight.Bold)
                    } else {
                        Typography.bodyLarge
                    }
                    Text(
                        text = item.bodyText,
                        style = textStyle,
                        color = colorScheme.onBackground,
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

            is CentreAlignedScreenBodyContent.NumberedList -> {
                item {
                    GdsNumberedList(
                        item.items,
                        Modifier
                            .fillMaxWidth()
                            .padding(itemPadding),
                        item.title,
                    )
                }
            }

            is CentreAlignedScreenBodyContent.Button -> {
                item {
                    SecondaryButton(item)
                }
            }
        }
    }
}

@Composable
private fun SecondaryButton(button: CentreAlignedScreenBodyContent.Button) {
    val buttonModifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = spacingDouble)
    val contentPosition = if (button.leftAligned) Arrangement.Start else Arrangement.Center
    val buttonType = if (button.showIcon) {
        ButtonType.Icon(
            buttonColors = customButtonColors(
                contentColor = colorScheme.primary,
                containerColor = colorScheme.background,
            ),
            iconImage = ImageVector.vectorResource(R.drawable.ic_external_site),
            contentDescription = stringResource(R.string.opens_in_external_browser),
        )
    } else {
        ButtonType.Secondary
    }

    GdsButton(
        text = button.text,
        buttonType = buttonType,
        onClick = button.onClick,
        modifier = buttonModifier,
        contentModifier = Modifier.fillMaxWidth(),
        contentPosition = contentPosition,
    )
}
