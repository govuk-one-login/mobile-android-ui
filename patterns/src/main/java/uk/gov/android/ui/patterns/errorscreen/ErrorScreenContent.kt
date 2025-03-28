package uk.gov.android.ui.patterns.errorscreen

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import kotlinx.collections.immutable.ImmutableList
import uk.gov.android.ui.componentsv2.bulletedlist.BulletedListTitle
import uk.gov.android.ui.componentsv2.bulletedlist.GdsBulletedList
import uk.gov.android.ui.componentsv2.button.ButtonType
import uk.gov.android.ui.componentsv2.button.GdsButton
import uk.gov.android.ui.componentsv2.button.customButtonColors
import uk.gov.android.ui.patterns.R
import uk.gov.android.ui.patterns.errorscreen.ErrorScreenBodyContent.BulletList
import uk.gov.android.ui.patterns.errorscreen.ErrorScreenBodyContent.Button
import uk.gov.android.ui.patterns.errorscreen.ErrorScreenBodyContent.Text
import uk.gov.android.ui.patterns.errorscreen.ErrorScreenButtonAlignment.Center
import uk.gov.android.ui.patterns.errorscreen.ErrorScreenButtonAlignment.Start
import uk.gov.android.ui.theme.m3.Typography
import uk.gov.android.ui.theme.m3_disabled
import uk.gov.android.ui.theme.m3_onDisabled
import uk.gov.android.ui.theme.spacingDouble
import uk.gov.android.ui.theme.spacingSingle
import uk.gov.android.ui.componentsv2.R as componentsR

internal data class ErrorScreenContent(
    val configurationDescription: String,
    val title: String,
    val icon: ErrorScreenIcon = ErrorScreenIcon.ErrorIcon,
    val body: ImmutableList<ErrorScreenBodyContent>? = null,
    val buttons: ImmutableList<ErrorScreenButton>? = null,
)

sealed class ErrorScreenButton {
    data class PrimaryButton(
        val text: String,
        val onClick: () -> Unit,
        val showIcon: Boolean = false,
    ) : ErrorScreenButton()
    data class SecondaryButton(
        val text: String,
        val onClick: () -> Unit,
        val showIcon: Boolean = false,
    ) : ErrorScreenButton()
}

sealed class ErrorScreenBodyContent {
    data class Text(
        val text: String,
        val type: TextType = TextType.Regular,
    ) : ErrorScreenBodyContent()
    data class BulletList(
        val title: BulletedListTitle? = null,
        val items: ImmutableList<String>,
    ) : ErrorScreenBodyContent()
    data class Button(
        val text: String,
        val onClick: () -> Unit,
        val buttonAlignment: ErrorScreenButtonAlignment = Center,
        val showIcon: Boolean = false,
    ) : ErrorScreenBodyContent()
}

enum class TextType {
    Bold, Regular
}

enum class ErrorScreenButtonAlignment {
    Start,
    Center,
}

enum class ErrorScreenIcon(
    @DrawableRes val icon: Int,
    @StringRes val description: Int,
) {
    ErrorIcon(
        icon = R.drawable.ic_warning_error,
        description = R.string.error_icon_description,
    ),
    WarningIcon(
        icon = R.drawable.ic_warning_error,
        description = R.string.warning_icon_description,
    ),
}

@Suppress("LongMethod")
internal fun LazyListScope.toBodyContent(
    body: ImmutableList<ErrorScreenBodyContent>?,
    horizontalItemPadding: Dp,
) {
    val itemPadding = PaddingValues(horizontal = horizontalItemPadding)
    body?.forEachIndexed { i, item ->
        when (item) {
            is Text -> {
                item {
                    val textStyle = when (item.type) {
                        TextType.Bold -> Typography.bodyLarge.copy(fontWeight = FontWeight.Bold)
                        TextType.Regular -> Typography.bodyLarge
                    }

                    Text(
                        text = item.text,
                        style = textStyle,
                        color = colorScheme.onBackground,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(itemPadding),
                    )
                }
            }
            is BulletList -> {
                item {
                    GdsBulletedList(
                        bulletListItems = item.items,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(itemPadding),
                        title = item.title,
                    )
                }
            }
            is Button -> {
                item {
                    val buttonModifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = spacingSingle)
                    val centerPosition = when (item.buttonAlignment) {
                        Center -> Arrangement.Center
                        Start -> Arrangement.Start
                    }
                    if (item.showIcon) {
                        GdsButton(
                            text = item.text,
                            buttonType = ButtonType.Icon(
                                buttonColors = customButtonColors(
                                    contentColor = colorScheme.primary,
                                    containerColor = colorScheme.background,
                                ),
                                iconImage = ImageVector.vectorResource(componentsR.drawable.ic_external_site),
                                contentDescription = stringResource(componentsR.string.opens_in_external_browser),
                            ),
                            onClick = item.onClick,
                            modifier = buttonModifier,
                            contentPosition = centerPosition,
                        )
                    } else {
                        GdsButton(
                            text = item.text,
                            buttonType = ButtonType.Secondary,
                            onClick = item.onClick,
                            modifier = buttonModifier,
                            contentPosition = centerPosition,
                        )
                    }
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

@Suppress("LongMethod")
@Composable
internal fun ToBottomContent(
    buttons: ImmutableList<ErrorScreenButton>,
    horizontalItemPadding: Dp,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(spacingDouble),
        modifier = Modifier.padding(horizontalItemPadding),
    ) {
        buttons.forEach { item ->
            when (item) {
                is ErrorScreenButton.PrimaryButton -> {
                    if (item.showIcon) {
                        GdsButton(
                            text = item.text,
                            buttonType = ButtonType.Icon(
                                buttonColors = ButtonDefaults.buttonColors(
                                    containerColor = colorScheme.primary,
                                    contentColor = colorScheme.onPrimary,
                                    disabledContainerColor = m3_disabled,
                                    disabledContentColor = m3_onDisabled,
                                ),
                                fontWeight = FontWeight.Bold,
                                iconImage = ImageVector.vectorResource(componentsR.drawable.ic_external_site),
                                contentDescription = stringResource(componentsR.string.opens_in_external_browser),
                            ),
                            onClick = item.onClick,
                            modifier = Modifier.fillMaxWidth(),
                            contentPosition = Arrangement.Center,
                        )
                    } else {
                        GdsButton(
                            text = item.text,
                            buttonType = ButtonType.Primary,
                            onClick = item.onClick,
                            modifier = Modifier.fillMaxWidth(),
                            contentPosition = Arrangement.Center,
                        )
                    }
                }

                is ErrorScreenButton.SecondaryButton -> {
                    val buttonModifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = spacingSingle)
                    if (item.showIcon) {
                        GdsButton(
                            text = item.text,
                            buttonType = ButtonType.Icon(
                                buttonColors = customButtonColors(
                                    contentColor = colorScheme.primary,
                                    containerColor = colorScheme.background,
                                ),
                                iconImage = ImageVector.vectorResource(componentsR.drawable.ic_external_site),
                                contentDescription = stringResource(componentsR.string.opens_in_external_browser),
                            ),
                            onClick = item.onClick,
                            modifier = Modifier.fillMaxWidth(),
                            contentPosition = Arrangement.Center,
                        )
                    } else {
                        GdsButton(
                            text = item.text,
                            buttonType = ButtonType.Secondary,
                            onClick = item.onClick,
                            modifier = buttonModifier,
                            contentPosition = Arrangement.Center,
                        )
                    }
                }
            }
        }
    }
}
