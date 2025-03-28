package uk.gov.android.ui.patterns.errorscreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewParameter
import kotlinx.collections.immutable.ImmutableList
import uk.gov.android.ui.componentsv2.R
import uk.gov.android.ui.componentsv2.bulletedlist.GdsBulletedList
import uk.gov.android.ui.componentsv2.button.ButtonType
import uk.gov.android.ui.componentsv2.button.GdsButton
import uk.gov.android.ui.componentsv2.button.customButtonColors
import uk.gov.android.ui.componentsv2.heading.GdsHeading
import uk.gov.android.ui.patterns.centrealignedscreen.CentreAlignedScreen
import uk.gov.android.ui.patterns.errorscreen.ErrorScreenBodyContent.BulletList
import uk.gov.android.ui.patterns.errorscreen.ErrorScreenBodyContent.Button
import uk.gov.android.ui.patterns.errorscreen.ErrorScreenBodyContent.Text
import uk.gov.android.ui.theme.m3.GdsTheme
import uk.gov.android.ui.theme.m3.Typography
import uk.gov.android.ui.theme.m3_disabled
import uk.gov.android.ui.theme.m3_onDisabled
import uk.gov.android.ui.theme.spacingDouble
import uk.gov.android.ui.theme.spacingSingle
import uk.gov.android.ui.theme.util.UnstableDesignSystemAPI

@OptIn(UnstableDesignSystemAPI::class)
@Composable
fun ErrorScreen(
    title: String,
    icon: ErrorScreenIcon,
    modifier: Modifier = Modifier,
    body: ImmutableList<ErrorScreenBodyContent>? = null,
    buttons: ImmutableList<ErrorScreenButton>? = null,
) {
    CentreAlignedScreen(
        title = { horizontalPadding ->
            GdsHeading(
                text = title,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = horizontalPadding),
                textAlign = TextAlign.Center,
            )
        },
        image = { horizontalPadding ->
            Icon(
                imageVector = ImageVector.vectorResource(icon.icon),
                contentDescription = stringResource(icon.description),
                modifier = Modifier.padding(horizontal = horizontalPadding),
                tint = colorScheme.onBackground,
            )
        },
        modifier = modifier,
        body = body?.let {
            { horizontalPadding ->
                body.forEachIndexed { i, item ->
                    when (item) {
                        is Text -> BodyContentText(item)
                        is BulletList -> BodyContentBulletList(item)
                        is Button -> BodyContentButton(item)
                    }

                    if (i < body.lastIndex) {
                        Spacer(modifier = Modifier.height(spacingDouble))
                    }
                }
            }
        },
        bottomContent = {
            buttons?.let {
                BottomContent(
                    it,
                    modifier = Modifier.fillMaxWidth(),
                )
            }
        },
    )
}

@Composable
private fun BottomContent(
    buttons: ImmutableList<ErrorScreenButton>,
    modifier: Modifier = Modifier,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(spacingDouble),
        modifier = modifier.padding(spacingDouble),
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
                                iconImage = ImageVector.vectorResource(R.drawable.ic_external_site),
                                contentDescription = stringResource(R.string.opens_in_external_browser),
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
                                iconImage = ImageVector.vectorResource(R.drawable.ic_external_site),
                                contentDescription = stringResource(R.string.opens_in_external_browser),
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

@Composable
private fun BodyContentButton(item: Button) {
    val buttonModifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = spacingSingle)
    val centerPosition = when (item.buttonAlignment) {
        ErrorScreenButtonAlignment.Center -> Arrangement.Center
        ErrorScreenButtonAlignment.Start -> Arrangement.Start
    }
    if (item.showIcon) {
        GdsButton(
            text = item.text,
            buttonType = ButtonType.Icon(
                buttonColors = customButtonColors(
                    contentColor = colorScheme.primary,
                    containerColor = colorScheme.background,
                ),
                iconImage = ImageVector.vectorResource(R.drawable.ic_external_site),
                contentDescription = stringResource(R.string.opens_in_external_browser),
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

@Composable
private fun BodyContentBulletList(item: BulletList) {
    GdsBulletedList(
        bulletListItems = item.items,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = spacingDouble),
        title = item.title,
    )
}

@Composable
private fun BodyContentText(item: Text) {
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
            .padding(horizontal = spacingDouble),
    )
}

@PreviewLightDark
@Preview(showBackground = true)
@Composable
internal fun PreviewErrorScreen(
    @PreviewParameter(ErrorScreenContentProvider::class)
    content: ErrorScreenContent,
) {
    GdsTheme {
        ErrorScreen(
            title = content.title,
            icon = content.icon,
            body = content.body,
            buttons = content.buttons,
        )
    }
}

@PreviewLightDark
@Preview(showBackground = true, fontScale = 2f)
@Composable
internal fun PreviewErrorScreenAccessibility(
    @PreviewParameter(ErrorScreenContentProvider::class)
    content: ErrorScreenContent,
) {
    GdsTheme {
        ErrorScreen(
            title = content.title,
            icon = content.icon,
            body = content.body,
            buttons = content.buttons,
        )
    }
}
