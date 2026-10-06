package uk.gov.android.ui.patterns.leftalignedscreen

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
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import uk.gov.android.ui.componentsv2.button.ButtonTypeV2
import uk.gov.android.ui.componentsv2.button.GdsButton
import uk.gov.android.ui.componentsv2.supportingtext.GdsSupportingText
import uk.gov.android.ui.theme.m3.GdsTheme
import uk.gov.android.ui.theme.spacingDouble

/**
 * BottomContent to be used with the LeftAlignedScreen.
 *
 * The padding has been updated to match the Figma designs; a standardised 16.dp between components.
 * Paddings are configurable by using the modifiers for each individual composable block.
 *
 * @param parameters [BottomContentParameters] the composables to be arranged and configured.
 * @param modifier A [Modifier] to be applied to the root layout of the screen (optional).
 * @param isSticky A [Boolean] flag for if this is used as a sticky footer.
 * Determines the top padding (optional).
 */
@Composable
internal fun BottomContent(
    parameters: BottomContentParameters,
    modifier: Modifier = Modifier,
    isSticky: Boolean = true,
) {
    with(parameters) {
        Surface(
            color = backgroundColor
                .takeUnless { it == Color.Unspecified }
                ?: MaterialTheme.colorScheme.surface,
        ) {
            Column(
                modifier = modifier.padding(
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

/**
 *  A data class containing the composables to be arranged and configured,
 *  as well as other configurable parameters.
 *
 * @param supportingText [Composable] first composable in column (optional).
 * @param primaryButton [Composable] second composable in column (optional).
 * @param secondaryButton [Composable] third composable in column (optional).
 * @param backgroundColor [Color] background colour when sticky (optional).
 * @param horizontalPadding [Dp] defaults to spacingDouble (optional).
 * @param verticalPadding [Dp] defaults to spacingDouble (optional).
 */
internal data class BottomContentParameters(
    val supportingText: (@Composable (horizontalPadding: Dp) -> Unit)? = null,
    val primaryButton: (@Composable () -> Unit)? = null,
    val secondaryButton: (@Composable () -> Unit)? = null,
    val backgroundColor: Color = Color.Unspecified,
    val horizontalPadding: Dp = spacingDouble,
    val verticalPadding: Dp = spacingDouble,
)

@Immutable
internal class BottomContentParametersProvider :
    PreviewParameterProvider<BottomContentParameters> {
    override val values: Sequence<BottomContentParameters> =
        sequenceOf(
            BottomContentParameters(
                supportingText = ::Text,
            ),
            BottomContentParameters(
                primaryButton = ::PrimaryButton,
            ),
            BottomContentParameters(
                secondaryButton = ::SecondaryButton,
            ),
            BottomContentParameters(
                supportingText = ::Text,
                primaryButton = ::PrimaryButton,
            ),
            BottomContentParameters(
                primaryButton = ::PrimaryButton,
                secondaryButton = ::SecondaryButton,
            ),
            BottomContentParameters(
                supportingText = ::Text,
                primaryButton = ::PrimaryButton,
                secondaryButton = ::SecondaryButton,
            ),
        )

    @Composable
    private fun Text(horizontalPadding: Dp) = GdsSupportingText(
        text = "Check if your passport has a biometric chip, look for the " +
            "rectangular biometric chip symbol on the front cover",
        modifier = Modifier.padding(horizontal = horizontalPadding),
    )

    @Composable
    private fun PrimaryButton() = GdsButton(
        text = "Primary Button",
        onClick = {},
        buttonType = ButtonTypeV2.Primary(),
        modifier = Modifier.fillMaxWidth(),
    )

    @Composable
    private fun SecondaryButton() = GdsButton(
        text = "Secondary Button",
        onClick = {},
        buttonType = ButtonTypeV2.Secondary(),
        modifier = Modifier.fillMaxWidth(),
    )
}

@PreviewLightDark
@Composable
private fun PreviewBottomContent(
    @PreviewParameter(BottomContentParametersProvider::class)
    content: BottomContentParameters,
) {
    GdsTheme {
        BottomContent(content)
    }
}
