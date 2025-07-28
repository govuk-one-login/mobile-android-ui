package uk.gov.android.ui.componentsv2

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.focusGroup
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import uk.gov.android.ui.componentsv2.button.ButtonType
import uk.gov.android.ui.componentsv2.button.GdsButton
import uk.gov.android.ui.componentsv2.button.buttonColors
import uk.gov.android.ui.componentsv2.utils.ModifierExtensions.customTilePadding
import uk.gov.android.ui.componentsv2.utils.ModifierExtensions.customTitlePadding
import uk.gov.android.ui.componentsv2.utils.ModifierExtensions.elevatedCardModifier
import uk.gov.android.ui.theme.cardShadow
import uk.gov.android.ui.theme.dividerThickness
import uk.gov.android.ui.theme.m3.GdsLocalColorScheme
import uk.gov.android.ui.theme.m3.GdsTheme
import uk.gov.android.ui.theme.m3.Typography
import uk.gov.android.ui.theme.smallPadding
import uk.gov.android.ui.theme.tileCornerRadius
import uk.gov.android.ui.theme.xsmallPadding

/**
 * This is providing a customisable card/ tile.
 *
 * @param title (required)
 * @param titleStyle (default provided) - defaults to GDS Design Typography **Title 2** (see [Typography] but can be overridden with any other TextStyle, as required (check with UCD)
 * @param body (optional) - style/ details **NOT** configurable
 * @param caption (optional) - style/ details **NOT** configurable
 * @param image (optional) - image that would be displayed above text content
 * @param contentDescription (optional) - the image (param above) content description
 * @param showDismissIcon (optional) - dismiss icon present in the top left corner
 * @param dismiss (optional) - action to be provided for the dismiss icon (param above)
 * @param displayPrimary (default provided) - defaults to true - controls if a [GdsButton] of type [ButtonType.Primary] is displayed
 * @param displaySecondary (default provided) - defaults to false - controls if a [GdsButton] of type [ButtonType.Secondary] is displayed
 *
 * **You can either display a Primary Button, a Secondary one or no button at al, NOT both at the same time with the current implementation**
 *
 * @param buttonText (optional) - this is used for the content of the buttons (when using any of the buttons, this will need to be provided, otherwise it would be an empty button) - when not using buttons it can be skipped as it defaults to **null**
 * @param secondaryIcon (optional and default provided) - it defaults to **external site icon** - this controls if the secondary button will display an icon or if it will be only text (to **NOT** display the icon set to null)
 * @param secondaryIconContentDescription (optional and default provided) - it defaults to **external site icon content description**
 * @param shadow (default provided) - default to 1.dp but can be overridden
 * @param onClick (required) - action attached to the buttons (used for any type of buttons since only one button can be displayed at one time)
 */
@Composable
fun GdsCard(
    title: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    titleStyle: TextStyle = Typography.headlineMedium,
    image: Painter? = null,
    contentDescription: String? = null,
    showDismissIcon: Boolean = false,
    caption: String? = null,
    body: String? = null,
    displayPrimary: Boolean = true,
    buttonText: String? = null,
    displaySecondary: Boolean = false,
    secondaryIcon: ImageVector? = ImageVector.vectorResource(R.drawable.ic_external_site),
    secondaryIconContentDescription: String? = stringResource(R.string.opens_in_external_browser),
    shadow: Dp = cardShadow,
    dismiss: (() -> Unit) = {},
) {
    val cardContentDescription = stringResource(R.string.card_content_description, title)
    Card(
        colors = CardDefaults.cardColors(
            containerColor = GdsLocalColorScheme.current.cardBackground,
            contentColor = MaterialTheme.colorScheme.onBackground,
        ),
        shape = RoundedCornerShape(tileCornerRadius),
        modifier = modifier.elevatedCardModifier(shadow)
            .focusGroup()
            .semantics(true) { this.contentDescription = cardContentDescription },
    ) {
        // Allows for the children to be rendered appropriately when using cards in a scrollable layout
        Box(Modifier.wrapContentHeight()) {
            Column {
                TileImage(
                    image = image,
                    contentDescription = contentDescription,
                )
                Box(Modifier.wrapContentHeight()) {
                    Column {
                        Column(
                            modifier = Modifier
                                .padding(horizontal = smallPadding),
                        ) {
                            Content(
                                caption = caption,
                                title = title,
                                titleFont = titleStyle,
                                body = body,
                                displaySecondary = displaySecondary,
                                displayDismiss = image == null && showDismissIcon,
                            )
                            Buttons(
                                text = buttonText,
                                displayPrimary = displayPrimary,
                                displaySecondary = displaySecondary,
                                secondaryIcon = secondaryIcon,
                                secondaryIconContentDescription = secondaryIconContentDescription,
                                onClick = onClick,
                            )
                        }
                    }

                    if (image == null && showDismissIcon) {
                        DismissButton(
                            dismiss,
                            Modifier.align(alignment = Alignment.TopEnd).zIndex(1f),
                        )
                    }
                }
            }
            if (showDismissIcon && image != null) {
                DismissButton(
                    dismiss,
                    Modifier.align(alignment = Alignment.TopEnd).zIndex(1f),
                )
            }
        }
    }
}

@Composable
private fun Content(
    caption: String?,
    title: String,
    titleFont: TextStyle,
    body: String?,
    displaySecondary: Boolean,
    displayDismiss: Boolean,
) {
    caption?.let {
        Text(
            text = caption,
            style = Typography.bodySmall,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = xsmallPadding),
        )
    }
    Text(
        text = title,
        style = titleFont,
        modifier = Modifier
            .customTilePadding(body != null)
            .customTitlePadding(displayDismiss),
    )
    body?.let {
        Text(
            text = body,
            style = Typography.bodyLarge,
            modifier = Modifier.customTilePadding(displaySecondary),
        )
    }
}

@Composable
private fun TileImage(
    image: Painter? = null,
    contentDescription: String? = null,
) {
    val defaultContentDescription = stringResource(R.string.vector_image_content_description)
    image?.let {
        Image(
            painter = image,
            contentDescription = contentDescription ?: defaultContentDescription,
            contentScale = ContentScale.FillWidth,
            modifier = Modifier.fillMaxWidth(),
        )
    }
}

@Composable
private fun DismissButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val description = stringResource(R.string.close_button)
    IconButton(
        onClick = onClick,
        modifier = Modifier
            .semantics(true) {}
            .then(modifier),
    ) {
        Icon(
            imageVector = Icons.Default.Close,
            contentDescription = description,
            tint = GdsLocalColorScheme.current.topBarIcon,
        )
    }
}

@Composable
private fun Buttons(
    text: String?,
    displayPrimary: Boolean,
    secondaryIcon: ImageVector?,
    displaySecondary: Boolean,
    secondaryIconContentDescription: String?,
    onClick: () -> Unit,
) {
    text?.let {
        if (displayPrimary) {
            GdsButton(
                text = text,
                buttonType = ButtonType.Primary,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        top = xsmallPadding,
                        bottom = smallPadding,
                    ),
                contentModifier = Modifier.fillMaxWidth(),
                onClick = onClick,
            )
        } else {
            if (displaySecondary) {
                HorizontalDivider(
                    thickness = dividerThickness,
                    color = GdsLocalColorScheme.current.dividerDefault,
                    modifier = Modifier
                        .padding(top = smallPadding),
                )
                GdsButton(
                    text = text,
                    onClick = onClick,
                    buttonType = secondaryIcon?.let {
                        ButtonType.Icon(
                            buttonColors = ButtonType.Secondary.buttonColors(),
                            iconImage = secondaryIcon,
                            contentDescription = secondaryIconContentDescription ?: "",
                        )
                    } ?: ButtonType.Secondary,
                    modifier = Modifier.fillMaxWidth(),
                    contentPosition = Arrangement.Start,
                    contentModifier = Modifier.fillMaxWidth(),
                )
            }
        }
    }
}

internal data class GdsCardPreviewParameters(
    @DrawableRes
    val image: Int? = null,
    @StringRes
    val contentDescription: Int? = null,
    val showDismissIcon: Boolean = false,
    @StringRes
    val caption: Int? = null,
    @StringRes
    val title: Int,
    val titleStyle: TextStyle = Typography.headlineMedium,
    @StringRes
    val body: Int? = null,
    val displayPrimary: Boolean = true,
    val buttonText: Int? = null,
    val displaySecondary: Boolean = false,
    @DrawableRes
    val secondaryIcon: Int? = R.drawable.ic_external_site,
    @StringRes
    val secondaryIconContentDescription: Int? = R.string.opens_in_external_browser,
    val shadow: Dp = cardShadow,
)

internal class GdsCardPreviewParametersProvider :
    PreviewParameterProvider<GdsCardPreviewParameters> {
    override val values: Sequence<GdsCardPreviewParameters> = sequenceOf(
        GdsCardPreviewParameters(
            image = R.drawable.ic_tile_image,
            contentDescription = R.string.vector_image_content_description,
            showDismissIcon = true,
            caption = R.string.caption,
            title = R.string.title,
            body = R.string.body,
            buttonText = R.string.primary_button,
        ),
        GdsCardPreviewParameters(
            image = R.drawable.ic_tile_image,
            title = R.string.title,
            displayPrimary = false,
            buttonText = R.string.secondary_button,
            displaySecondary = true,
        ),
        GdsCardPreviewParameters(
            caption = R.string.caption,
            title = R.string.title,
            body = R.string.body,
            displayPrimary = true,
            buttonText = R.string.primary_button,
        ),
        GdsCardPreviewParameters(
            caption = R.string.caption,
            title = R.string.title,
            displayPrimary = true,
            buttonText = R.string.primary_button,
        ),
        GdsCardPreviewParameters(
            caption = R.string.caption,
            title = R.string.title,
        ),
        GdsCardPreviewParameters(
            showDismissIcon = true,
            caption = R.string.caption,
            title = R.string.title,
            body = R.string.body,
            displayPrimary = false,
            displaySecondary = true,
            secondaryIcon = null,
            buttonText = R.string.secondary_button,
        ),
        GdsCardPreviewParameters(
            showDismissIcon = true,
            caption = R.string.caption,
            title = R.string.title,
            titleStyle = Typography.titleSmall,
            body = R.string.body,
            displayPrimary = true,
            buttonText = R.string.primary_button,
            shadow = 12.dp,
        ),
        GdsCardPreviewParameters(
            showDismissIcon = true,
            caption = R.string.caption,
            title = R.string.title,
            body = R.string.body,
            displayPrimary = true,
            buttonText = R.string.primary_button,
            shadow = 0.dp,
        ),
        GdsCardPreviewParameters(
            showDismissIcon = true,
            caption = R.string.caption,
            title = R.string.title,
            titleStyle = Typography.headlineLarge,
            body = R.string.body,
            displayPrimary = false,
            buttonText = R.string.secondary_button,
            displaySecondary = true,
            secondaryIcon = R.drawable.arrow_forward,
            secondaryIconContentDescription = R.string.icon_content_desc,
            shadow = 0.dp,
        ),
    )
}

@Composable
@Preview
internal fun GdsCardPreview(
    @PreviewParameter(GdsCardPreviewParametersProvider::class)
    parameters: GdsCardPreviewParameters,
) {
    GdsTheme {
        GdsCard(
            title = stringResource(parameters.title),
            titleStyle = parameters.titleStyle,
            onClick = {},
            image = parameters.image?.let { painterResource(it) },
            contentDescription = parameters.contentDescription?.let { stringResource(it) },
            showDismissIcon = parameters.showDismissIcon,
            caption = parameters.caption?.let { stringResource(it) },
            body = parameters.body?.let { stringResource(it) },
            displayPrimary = parameters.displayPrimary,
            buttonText = parameters.buttonText?.let { stringResource(it) },
            displaySecondary = parameters.displaySecondary,
            secondaryIcon = parameters.secondaryIcon?.let { ImageVector.vectorResource(it) },
            secondaryIconContentDescription = parameters.secondaryIconContentDescription?.let {
                stringResource(it)
            },
            shadow = parameters.shadow,
        )
    }
}
