package uk.gov.android.ui.componentsV2

import android.util.Log
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import uk.gov.android.ui.components.R
import uk.gov.android.ui.componentsV2.button.ButtonParameters
import uk.gov.android.ui.componentsV2.button.ButtonType
import uk.gov.android.ui.componentsV2.button.GdsButton
import uk.gov.android.ui.componentsV2.images.GdsVectorImage
import uk.gov.android.ui.componentsV2.images.IconParameters
import uk.gov.android.ui.componentsV2.images.VectorImageParameters
import uk.gov.android.ui.componentsV2.utils.ModifierExtensions.customTilePadding
import uk.gov.android.ui.componentsV2.utils.ModifierExtensions.elevatedCardModifier
import uk.gov.android.ui.theme.dividerThickness
import uk.gov.android.ui.theme.m3.GdsTheme
import uk.gov.android.ui.theme.m3.Typography
import uk.gov.android.ui.theme.smallPadding
import uk.gov.android.ui.theme.tileCornerRadius
import uk.gov.android.ui.theme.xsmallPadding
import java.util.MissingResourceException

@Composable
fun GdsContentTile(
    parameters: ContentTileParameters,
    onClick: () -> Unit,
) {
    val defaultContentDescription = R.string.vector_image_content_description
    with(parameters) {
        Card(
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.inverseOnSurface,
            ),
            shape = RoundedCornerShape(tileCornerRadius),
            modifier = Modifier.elevatedCardModifier(),
        ) {
            image?.let {
                GdsVectorImage(
                    VectorImageParameters(
                        image = image,
                        contentDescription = contentDescription ?: defaultContentDescription,
                        scale = ContentScale.FillWidth,
                    ),
                )
            }
            Column(
                modifier = Modifier
                    .weight(1f, false)
                    .padding(horizontal = smallPadding),
            ) {
                caption?.let {
                    Text(
                        text = stringResource(caption),
                        style = Typography.bodySmall,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = xsmallPadding),
                    )
                }

                Text(
                    text = stringResource(title),
                    style = Typography.headlineMedium,
                    modifier = Modifier.customTilePadding(body),
                )

                body?.let {
                    Text(
                        text = stringResource(body),
                        style = Typography.bodyLarge,
                        modifier = Modifier.padding(vertical = xsmallPadding),
                    )
                }

                Buttons(onClick)
            }
        }
    }
}

data class ContentTileParameters(
    @DrawableRes
    val image: Int? = null,
    @StringRes
    val contentDescription: Int? = null,
    @StringRes
    val caption: Int? = null,
    @StringRes
    val title: Int,
    @StringRes
    val body: Int? = null,
    val displayPrimary: Boolean = true,
    val text: Int? = null,
    val secondaryIcon: Int? = null,
) {
    init {
        val error = MissingResourceException(
            "Field secondaryIcon cannot be null",
            this.javaClass.simpleName.toString(),
            secondaryIcon.toString(),
        )
        if (!displayPrimary && text != null) {
            if (secondaryIcon == null) {
                Log.e(error.key, error.message, error)
                throw error
            }
        }
    }
}

@Composable
private fun ContentTileParameters.Buttons(
    onClick: () -> Unit,
) {
    text?.let {
        if (displayPrimary) {
            GdsButton(
                parameters = ButtonParameters(
                    text = text,
                    buttonType = ButtonType.PRIMARY(),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            top = xsmallPadding,
                            bottom = smallPadding,
                        ),
                    contentModifier = Modifier.fillMaxWidth(),
                ),
                onClick = onClick,
            )
        } else {
            secondaryIcon?.let {
                HorizontalDivider(
                    thickness = dividerThickness,
                    color = MaterialTheme.colorScheme.surface,
                )
                GdsButton(
                    parameters = ButtonParameters(
                        text = text,
                        buttonType = ButtonType.ICON(
                            ButtonType.CUSTOM(
                                contentColor = MaterialTheme.colorScheme.primary,
                                containerColor = MaterialTheme.colorScheme.inverseOnSurface,
                            ),
                            IconParameters(
                                image = R.drawable.ic_external_site,
                                color = MaterialTheme.colorScheme.primary,
                                backgroundColor = MaterialTheme.colorScheme.inverseOnSurface,
                                contentDescription = R.string.icon_content_desc,
                            ),
                        ),
                        modifier = Modifier
                            .fillMaxWidth(),
                        contentPosition = Arrangement.Start,
                    ),
                    onClick = onClick,
                )
            }
        }
    }
}

class ContentTilePreviewParameters : PreviewParameterProvider<ContentTileParameters> {
    override val values: Sequence<ContentTileParameters> = sequenceOf(
        ContentTileParameters(
            image = R.drawable.ic_tile_image,
            contentDescription = R.string.vector_image_content_description,
            caption = R.string.caption,
            title = R.string.title,
            body = R.string.body,
            text = R.string.primary_button,
        ),
        ContentTileParameters(
            image = R.drawable.ic_tile_image,
            title = R.string.title,
            displayPrimary = false,
            text = R.string.secondary_button,
            secondaryIcon = R.drawable.ic_external_site,
        ),
        ContentTileParameters(
            caption = R.string.caption,
            title = R.string.title,
            body = R.string.body,
            displayPrimary = true,
            text = R.string.primary_button,
        ),
        ContentTileParameters(
            caption = R.string.caption,
            title = R.string.title,
            displayPrimary = true,
            text = R.string.primary_button,
        ),
        ContentTileParameters(
            caption = R.string.caption,
            title = R.string.title,
        ),
    )
}

@Composable
@PreviewLightDark
fun ContentTilePreview(
    @PreviewParameter(ContentTilePreviewParameters::class)
    parameters: ContentTileParameters,
) {
    GdsTheme {
        GdsContentTile(parameters) {}
    }
}
