package uk.gov.android.ui.patterns.leftalignedscreen.v3

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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

/**
 * The bottom content (supporting text and buttons) of [LeftAlignedScreenV3].
 *
 * Spacing follows the Figma design (a standard 16.dp between components) and can be overridden per
 * element through the modifiers on each slot.
 *
 * This is the slot-based implementation used directly by production code (`LeftAlignedLayout`).
 * The [BottomContentParameters] overload below is a thin wrapper kept for previews, tests and
 * demos.
 *
 * @param supportingText the supporting text slot, shown above the buttons. Receives the
 * configured horizontal padding and a vertical padding hook; the hook is passed as [NoPadding]
 * here because the gap below the supporting text is already provided by this `Column`'s
 * [Arrangement.spacedBy], so a slot only needs it to add its own custom offset (optional).
 * @param primaryButton the primary action slot (optional).
 * @param secondaryButton the secondary action slot (optional).
 * @param config the layout configuration (background colour, padding and modifier) for the bottom
 * content (optional).
 * @param isSticky whether this is rendered as a sticky footer; when true a top padding is added to
 * separate it from the content above (optional).
 */
@Composable
internal fun BottomContentV2(
    supportingText: (@Composable (horizontalPadding: Dp, verticalPadding: Dp) -> Unit)?,
    primaryButton: (@Composable () -> Unit)?,
    secondaryButton: (@Composable () -> Unit)?,
    config: BottomContentConfig = BottomContentConfig(),
    isSticky: Boolean = true,
) {
    with(config) {
        Surface(
            modifier = modifier.fillMaxWidth(),
            color = backgroundColor
                .takeUnless { it == Color.Unspecified }
                ?: Color.Transparent,
        ) {
            Column(
                modifier = Modifier.padding(
                    start = horizontalPadding,
                    end = horizontalPadding,
                    top = if (isSticky) verticalPadding else 0.dp,
                    bottom = verticalPadding,
                ),
                verticalArrangement = Arrangement.spacedBy(verticalPadding),
            ) {
                supportingText?.invoke(horizontalPadding, NoPadding)
                primaryButton?.invoke()
                secondaryButton?.invoke()
            }
        }
    }
}

private val NoPadding: Dp = 0.dp

/**
 * [BottomContentParameters] convenience overload of [BottomContentV2], kept for previews, tests
 * and demos. Maps the parameter slots onto the slot-based overload above, which is what
 * production code (`LeftAlignedLayout`) calls directly.
 *
 * @param parameters the supporting text and button content.
 * @param config the layout configuration (background colour, padding and modifier) for the bottom
 * content (optional).
 * @param isSticky whether this is rendered as a sticky footer; when true a top padding is added to
 * separate it from the content above (optional).
 */
@Composable
internal fun BottomContentV2(
    parameters: BottomContentParameters,
    config: BottomContentConfig = BottomContentConfig(),
    isSticky: Boolean = true,
) = BottomContentV2(
    supportingText = parameters.supportingText,
    primaryButton = parameters.primaryButton,
    secondaryButton = parameters.secondaryButton,
    config = config,
    isSticky = isSticky,
)

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
    private fun Text(horizontalPadding: Dp, verticalPadding: Dp) = GdsSupportingText(
        text = SUPPORTING_TEXT,
        modifier = Modifier.padding(horizontal = horizontalPadding, vertical = verticalPadding),
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
        Surface {
            BottomContentV2(BottomContentParametersProvider().allStandardParams)
        }
    }
}

@ExcludeFromJacocoGeneratedReport
@PreviewFontScale
@Composable
private fun PreviewFontScaleBottomContent() {
    GdsTheme {
        Surface {
            BottomContentV2(BottomContentParametersProvider().allStandardParams)
        }
    }
}

@ExcludeFromJacocoGeneratedReport
@Preview
@Composable
private fun PreviewBottomContent(
    @PreviewParameter(BottomContentParametersProvider::class)
    content: BottomContentParameters,
) {
    GdsTheme { Surface { BottomContentV2(content) } }
}
