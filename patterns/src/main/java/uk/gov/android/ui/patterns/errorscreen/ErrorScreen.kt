package uk.gov.android.ui.patterns.errorscreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewParameter
import kotlinx.collections.immutable.ImmutableList
import uk.gov.android.ui.componentsv2.R
import uk.gov.android.ui.componentsv2.button.ButtonType
import uk.gov.android.ui.componentsv2.button.GdsButton
import uk.gov.android.ui.componentsv2.button.buttonColors
import uk.gov.android.ui.componentsv2.button.customButtonColors
import uk.gov.android.ui.componentsv2.heading.GdsHeading
import uk.gov.android.ui.componentsv2.heading.GdsHeadingAlignment
import uk.gov.android.ui.patterns.centrealignedscreen.CentreAlignedScreen
import uk.gov.android.ui.patterns.centrealignedscreen.CentreAlignedScreenBodyContent
import uk.gov.android.ui.patterns.centrealignedscreen.CentreAlignedScreenButton
import uk.gov.android.ui.patterns.centrealignedscreen.toBodyContent
import uk.gov.android.ui.patterns.errorscreen.ErrorScreenTitleTestTag.ERROR_SCREEN_TITLE_TEST_TAG
import uk.gov.android.ui.theme.m3.GdsTheme
import uk.gov.android.ui.theme.meta.ExcludeFromJacocoGeneratedReport
import uk.gov.android.ui.theme.spacingDouble

/**
 * Renders a centre-aligned error screen with a structured layout.
 *
 * This screen is designed for displaying an warning/error icon, title, body content,
 * and bottom content with primary/secondary buttons in a visually consistent manner.
 *
 * @param icon image displayed at the top of the screen.
 * @param title represents the main title. Use of [GdsHeading] is recommended
 * @param modifier A [Modifier] to be applied to the root layout of the screen (optional).
 * @param body list of [CentreAlignedScreenBodyContent] representing the main content (optional).
 * @param primaryButton primary action button. Use of [GdsButton] composable is recommended (optional).
 * @param secondaryButton secondary action button. Use of [GdsButton] composable is recommended (optional).
 * @param tertiaryButton tertiary action button. Use of [GdsButton] composable is recommended (optional).
 */
@Deprecated("Use [patterns.errorscreen.v2/ErrorScreen] instead")
@Composable
fun ErrorScreen(
    icon: ErrorScreenIcon,
    title: String,
    modifier: Modifier = Modifier,
    body: ImmutableList<CentreAlignedScreenBodyContent>? = null,
    primaryButton: CentreAlignedScreenButton? = null,
    secondaryButton: CentreAlignedScreenButton? = null,
    tertiaryButton: CentreAlignedScreenButton? = null,
) {
    CentreAlignedScreen(
        title = { horizontalPadding ->
            Column(
                modifier = Modifier
                    .testTag(ERROR_SCREEN_TITLE_TEST_TAG)
                    .semantics(mergeDescendants = true) {
                        heading()
                    },
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(icon.icon),
                    contentDescription = stringResource(icon.description),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = horizontalPadding),
                    tint = colorScheme.onBackground,
                )

                Spacer(modifier = Modifier.height(spacingDouble))

                GdsHeading(
                    text = title,
                    modifier = Modifier
                        .padding(horizontal = horizontalPadding),
                    textAlign = GdsHeadingAlignment.CenterAligned,
                )
            }
        },
        modifier = modifier,
        body = body?.let {
            { horizontalPadding ->
                toBodyContent(body = body, horizontalItemPadding = horizontalPadding)
            }
        },
        primaryButton = primaryButton?.let {
            { PrimaryButton(it) }
        },
        secondaryButton = secondaryButton?.let {
            { SecondaryButton(it) }
        },
        tertiaryButton = tertiaryButton?.let {
            { SecondaryButton(it) }
        },
    )
}

@Composable
private fun PrimaryButton(button: CentreAlignedScreenButton) {
    val buttonType = if (button.showIcon) {
        ButtonType.Icon(
            buttonColors = ButtonType.Primary.buttonColors(),
            fontWeight = FontWeight.Bold,
            iconImage = ImageVector.vectorResource(R.drawable.ic_external_site),
            contentDescription = stringResource(R.string.opens_in_external_browser),
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

@Composable
private fun SecondaryButton(button: CentreAlignedScreenButton) {
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
        modifier = Modifier.fillMaxWidth(),
        enabled = button.enabled,
    )
}

internal object ErrorScreenTitleTestTag {
    const val ERROR_SCREEN_TITLE_TEST_TAG = "ERROR_SCREEN_TITLE_TEST_TAG"
}

@ExcludeFromJacocoGeneratedReport
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
            primaryButton = content.primaryButton,
            secondaryButton = content.secondaryButton,
            tertiaryButton = content.tertiaryButton,
        )
    }
}

@ExcludeFromJacocoGeneratedReport
@PreviewLightDark
@Preview(showBackground = true, fontScale = 1.5f)
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
            primaryButton = content.primaryButton,
            secondaryButton = content.secondaryButton,
            tertiaryButton = content.tertiaryButton,
        )
    }
}
