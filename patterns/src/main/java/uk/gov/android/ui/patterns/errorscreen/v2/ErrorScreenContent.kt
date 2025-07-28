package uk.gov.android.ui.patterns.errorscreen.v2

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
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
import uk.gov.android.ui.componentsv2.button.ButtonType
import uk.gov.android.ui.componentsv2.button.GdsButton
import uk.gov.android.ui.componentsv2.button.buttonColors
import uk.gov.android.ui.componentsv2.button.customButtonColors
import uk.gov.android.ui.componentsv2.list.GdsBulletedList
import uk.gov.android.ui.componentsv2.list.GdsNumberedList
import uk.gov.android.ui.componentsv2.list.ListItem
import uk.gov.android.ui.componentsv2.list.ListTitle
import uk.gov.android.ui.patterns.R
import uk.gov.android.ui.theme.m3.Typography
import uk.gov.android.ui.theme.spacingSingle

internal data class ErrorScreenContent(
    val configurationDescription: String,
    val title: String,
    val icon: ErrorScreenIcon = ErrorScreenIcon.ErrorIcon,
    val body: ImmutableList<ErrorScreenBodyContent>? = null,
    val primaryButton: ErrorScreenButton? = null,
    val secondaryButton: ErrorScreenButton? = null,
    val tertiaryButton: ErrorScreenButton? = null,
)

sealed class ErrorScreenBodyContent {
    data class Text(
        val bodyText: String,
        val useBoldStyle: Boolean = false,
    ) : ErrorScreenBodyContent()

    data class BulletList(
        val title: ListTitle? = null,
        val items: ImmutableList<ListItem>,
    ) : ErrorScreenBodyContent()

    data class NumberedList(
        val title: ListTitle? = null,
        val items: ImmutableList<ListItem>,
    ) : ErrorScreenBodyContent()

    data class Button(
        val text: String,
        val onClick: () -> Unit,
        val leftAligned: Boolean = false,
        val showIcon: Boolean = false,
        val enabled: Boolean = true,
    ) : ErrorScreenBodyContent()
}

data class ErrorScreenButton(
    val text: String,
    val onClick: () -> Unit,
    val showIcon: Boolean = false,
    val enabled: Boolean = true,
)

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
    body: ImmutableList<ErrorScreenBodyContent>? = null,
    horizontalItemPadding: Dp,
) {
    val itemPadding = PaddingValues(horizontal = horizontalItemPadding)
    body?.forEach { item ->
        when (item) {
            is ErrorScreenBodyContent.Text -> {
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

            is ErrorScreenBodyContent.BulletList -> {
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

            is ErrorScreenBodyContent.NumberedList -> {
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

            is ErrorScreenBodyContent.Button -> {
                item { SecondaryButtonBody(item) }
            }
        }
    }
}

// Helper for primary button with icon
@Composable
internal fun PrimaryButton(button: ErrorScreenButton) {
    val buttonType = if (button.showIcon) {
        ButtonType.Icon(
            buttonColors = ButtonType.Primary.buttonColors(),
            fontWeight = FontWeight.Bold,
            iconImage = ImageVector.vectorResource(uk.gov.android.ui.componentsv2.R.drawable.ic_external_site),
            contentDescription = stringResource(uk.gov.android.ui.componentsv2.R.string.opens_in_external_browser),
        )
    } else {
        ButtonType.Primary
    }

    GdsButton(
        text = button.text,
        buttonType = buttonType,
        onClick = button.onClick,
        modifier = Modifier.fillMaxWidth(),
        enabled = button.enabled,
    )
}

// Helper for secondary button with icon
@Composable
internal fun SecondaryButton(button: ErrorScreenButton) {
    val buttonType = if (button.showIcon) {
        ButtonType.Icon(
            buttonColors = customButtonColors(
                contentColor = colorScheme.primary,
                containerColor = colorScheme.background,
            ),
            iconImage = ImageVector.vectorResource(uk.gov.android.ui.componentsv2.R.drawable.ic_external_site),
            contentDescription = stringResource(uk.gov.android.ui.componentsv2.R.string.opens_in_external_browser),
        )
    } else {
        ButtonType.Secondary
    }

    GdsButton(
        text = button.text,
        buttonType = buttonType,
        onClick = button.onClick,
        modifier = Modifier.fillMaxWidth(),
        enabled = button.enabled,
    )
}

// Helper for secondary body button with icon
@Composable
internal fun SecondaryButtonBody(button: ErrorScreenBodyContent.Button) {
    val buttonModifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = spacingSingle)
    val contentPosition = if (button.leftAligned) Arrangement.Start else Arrangement.Center
    val buttonType = if (button.showIcon) {
        ButtonType.Icon(
            buttonColors = customButtonColors(
                contentColor = colorScheme.primary,
                containerColor = colorScheme.background,
            ),
            iconImage = ImageVector.vectorResource(uk.gov.android.ui.componentsv2.R.drawable.ic_external_site),
            contentDescription = stringResource(uk.gov.android.ui.componentsv2.R.string.opens_in_external_browser),
        )
    } else {
        ButtonType.Secondary
    }

    GdsButton(
        text = button.text,
        buttonType = buttonType,
        onClick = button.onClick,
        modifier = buttonModifier,
        contentPosition = contentPosition,
    )
}
