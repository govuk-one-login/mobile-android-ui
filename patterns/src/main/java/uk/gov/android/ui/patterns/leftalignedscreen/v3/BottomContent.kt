package uk.gov.android.ui.patterns.leftalignedscreen.v3

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewFontScale
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import uk.gov.android.ui.componentsv2.button.ButtonTypeV2
import uk.gov.android.ui.componentsv2.button.GdsButton
import uk.gov.android.ui.componentsv2.supportingtext.GdsSupportingText
import uk.gov.android.ui.theme.m3.GdsTheme
import uk.gov.android.ui.theme.meta.ExcludeFromJacocoGeneratedReport
import uk.gov.android.ui.theme.spacingDouble

/**
 * The bottom content (supporting text and buttons) of [LeftAlignedScreenV3].
 *
 * Spacing follows the Figma design (a standard 16.dp between components) and can be overridden per
 * element through the modifiers on each [BottomContentParameters] slot.
 *
 * @param parameters the supporting text and button content and their layout configuration.
 * @param isSticky whether this is rendered as a sticky footer; when true a top padding is added to
 * separate it from the content above (optional).
 */
@Composable
internal fun BottomContentV2(parameters: BottomContentParameters, isSticky: Boolean = true) {
    with(parameters) {
        Surface(
            modifier = modifier.fillMaxWidth(),
            color = backgroundColor
                .takeUnless { it == Color.Unspecified }
                ?: MaterialTheme.colorScheme.surface,
        ) {
            Column(
                modifier = Modifier.padding(
                    start = horizontalPadding,
                    end = horizontalPadding,
                    top = if (isSticky) verticalPadding else 0.dp,
                    bottom = verticalPadding,
                ),
                verticalArrangement = Arrangement.spacedBy(spacingDouble),
            ) {
                supportingText?.invoke(horizontalPadding)
                primaryButton?.invoke()
                secondaryButton?.invoke()
            }
        }
    }
}

@Immutable
internal class BottomContentParametersProvider :
    PreviewParameterProvider<BottomContentParameters> {
    val onlySupportingText = BottomContentParameters(
        supportingText = ::Text,
    )
    val onlyPrimaryButton = BottomContentParameters(
        primaryButton = ::PrimaryButton,
    )
    val onlySecondaryButton = BottomContentParameters(
        secondaryButton = ::SecondaryButton,
    )
    val primaryButtonWithText = BottomContentParameters(
        supportingText = ::Text,
        primaryButton = ::PrimaryButton,
    )
    val secondaryButtonWithText = BottomContentParameters(
        supportingText = ::Text,
        secondaryButton = ::SecondaryButton,
    )
    val onlyButtons = BottomContentParameters(
        primaryButton = ::PrimaryButton,
        secondaryButton = ::SecondaryButton,
    )
    val allStandardParams = BottomContentParameters(
        supportingText = ::Text,
        primaryButton = ::PrimaryButton,
        secondaryButton = ::SecondaryButton,
    )

    override val values: Sequence<BottomContentParameters> =
        sequenceOf(
            onlySupportingText,
            onlyPrimaryButton,
            onlySecondaryButton,
            primaryButtonWithText,
            secondaryButtonWithText,
            onlyButtons,
            allStandardParams,
        )

    @Composable
    private fun Text(horizontalPadding: Dp) = GdsSupportingText(
        text = SUPPORTING_TEXT,
        modifier = Modifier.padding(horizontal = horizontalPadding),
    )

    @Composable
    private fun PrimaryButton() = GdsButton(
        text = PRIMARY_BUTTON_TEXT,
        onClick = {},
        buttonType = ButtonTypeV2.Primary(),
        modifier = Modifier.fillMaxWidth(),
    )

    @Composable
    private fun SecondaryButton() = GdsButton(
        text = SECONDARY_BUTTON_TEXT,
        onClick = {},
        buttonType = ButtonTypeV2.Secondary(),
        modifier = Modifier.fillMaxWidth(),
    )

    companion object {
        const val SUPPORTING_TEXT = "Check if your passport has a biometric chip, look for the " +
            "rectangular biometric chip symbol on the front cover"
        const val PRIMARY_BUTTON_TEXT = "Primary Button"
        const val SECONDARY_BUTTON_TEXT = "Secondary Button"
    }
}

@ExcludeFromJacocoGeneratedReport
@PreviewLightDark
@Composable
private fun PreviewLightDarkBottomContent() {
    GdsTheme {
        BottomContentV2(BottomContentParametersProvider().allStandardParams)
    }
}

@ExcludeFromJacocoGeneratedReport
@PreviewFontScale
@Composable
private fun PreviewFontScaleBottomContent() {
    GdsTheme {
        BottomContentV2(BottomContentParametersProvider().allStandardParams)
    }
}

@ExcludeFromJacocoGeneratedReport
@Preview
@Composable
private fun PreviewBottomContent(
    @PreviewParameter(BottomContentParametersProvider::class)
    content: BottomContentParameters,
) {
    GdsTheme {
        BottomContentV2(content)
    }
}
