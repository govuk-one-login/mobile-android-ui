package uk.gov.android.ui.componentsv2.dialogue

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.window.Dialog
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toImmutableList
import uk.gov.android.ui.componentsv2.R
import uk.gov.android.ui.componentsv2.button.ButtonTypeV2
import uk.gov.android.ui.componentsv2.button.GdsButton
import uk.gov.android.ui.componentsv2.heading.GdsHeading
import uk.gov.android.ui.componentsv2.heading.GdsHeadingAlignment
import uk.gov.android.ui.componentsv2.heading.GdsHeadingStyle
import uk.gov.android.ui.theme.m3.Backgrounds
import uk.gov.android.ui.theme.m3.GdsTheme
import uk.gov.android.ui.theme.m3.toMappedColors
import uk.gov.android.ui.theme.mediumPadding
import uk.gov.android.ui.theme.meta.ExcludeFromJacocoGeneratedReport
import uk.gov.android.ui.theme.smallPadding
import uk.gov.android.ui.theme.util.UnstableDesignSystemAPI
import uk.gov.android.ui.theme.xsmallPadding

/**
 * Display a Dialogue
 *
 * @param headingText Dialogue title
 * @param contentText Dialogue content
 * @param buttonParameters Used to define the dialogue buttons
 * @param modifier Modifier
 * @param onDismissRequest Dismiss listener
 */
@OptIn(UnstableDesignSystemAPI::class)
@Composable
fun GdsDialogue(
    headingText: String?,
    contentText: String?,
    buttonParameters: ImmutableList<DialogueButtonParameters>,
    modifier: Modifier = Modifier,
    onDismissRequest: () -> Unit,
) {
    Column(modifier = modifier) {
        Dialog(onDismissRequest = { onDismissRequest() }) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RectangleShape,
                colors =
                CardDefaults.cardColors(
                    containerColor = Backgrounds.dialogue.toMappedColors(),
                ),
            ) {
                Column(modifier = Modifier.fillMaxWidth()) {
                    headingText?.let {
                        GdsHeading(
                            text = headingText,
                            style = GdsHeadingStyle.Title2,
                            textAlign = GdsHeadingAlignment.LeftAligned,
                            modifier =
                            Modifier
                                .padding(
                                    start = mediumPadding,
                                    end = mediumPadding,
                                    top = mediumPadding,
                                )
                                .background(
                                    color = Backgrounds.dialogue.toMappedColors(),
                                ),
                        )
                    }
                    contentText?.let {
                        Text(
                            text = contentText,
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.onBackground,
                            modifier =
                            Modifier.padding(
                                start = mediumPadding,
                                end = mediumPadding,
                                top = smallPadding,
                            ),
                        )
                    }
                    ButtonRow(buttonParameters)
                }
            }
        }
    }
}

/**
 * Display the dialogue buttons in a Left To Right direction which ensures that the buttons
 * overflow in the correct order
 *
 * There must be at least one button in the dialogue to comply with GDS design requirements
 *
 * @param buttonParameters Used to define the dialogue buttons
 */
@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun ButtonRow(buttonParameters: ImmutableList<DialogueButtonParameters>) {
    FlowRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(mediumPadding),
        horizontalArrangement = Arrangement.spacedBy(
            smallPadding,
            Alignment.End,
        ),
        verticalArrangement = Arrangement.spacedBy(xsmallPadding),
    ) {
        buttonParameters.forEach { param ->
            GdsButton(
                text = param.text,
                buttonType = param.buttonType,
                onClick = param.onClick,
            )
        }
    }
}

data class DialogueButtonParameters(
    val text: String,
    val buttonType: ButtonTypeV2,
    val onClick: () -> Unit = {},
)

data class DialogueButtonPreviewParameters(
    val text: Int,
    val buttonType: ButtonTypeV2,
    val onClick: () -> Unit = {},
)

internal data class DialoguePreviewParameters(
    val headingText: Int,
    val contentText: Int,
    val buttonParameters: ImmutableList<DialogueButtonPreviewParameters>,
    val modifier: Modifier = Modifier,
    val onDismissRequest: () -> Unit = {},
)

internal class DialogProvider : PreviewParameterProvider<DialoguePreviewParameters> {
    override val values: Sequence<DialoguePreviewParameters> = sequenceOf(
        DialoguePreviewParameters(
            headingText = R.string.dialogue_provider_title1,
            contentText = R.string.dialogue_example_content,
            buttonParameters = persistentListOf(
                DialogueButtonPreviewParameters(
                    buttonType = ButtonTypeV2.Secondary(),
                    text = R.string.dialogue_provider_button_no,
                ),
                DialogueButtonPreviewParameters(
                    buttonType = ButtonTypeV2.Primary(),
                    text = R.string.dialogue_provider_button_yes,
                ),

            ),
        ),
        DialoguePreviewParameters(
            headingText = R.string.dialogue_provider_title2,
            contentText = R.string.dialogue_example_content,
            buttonParameters = persistentListOf(
                DialogueButtonPreviewParameters(
                    buttonType = ButtonTypeV2.Secondary(),
                    text = R.string.dialogue_provider_button_dismiss,
                ),
                DialogueButtonPreviewParameters(
                    buttonType = ButtonTypeV2.Secondary(),
                    text = R.string.dialogue_provider_button_confirm,
                ),

            ),
        ),
    )
}

@ExcludeFromJacocoGeneratedReport
@PreviewLightDark
@Composable
@Preview
internal fun GdsDialoguePreview(
    @PreviewParameter(DialogProvider::class)
    dialoguePreviewParameters: DialoguePreviewParameters,
) {
    dialoguePreviewParameters.apply {
        GdsTheme {
            GdsDialogue(
                headingText = stringResource(headingText),
                contentText = stringResource(contentText),
                buttonParameters = buttonParameters.map {
                    DialogueButtonParameters(
                        text = stringResource(it.text),
                        buttonType = it.buttonType,
                        onClick = it.onClick,
                    )
                }.toImmutableList(),
            ) {}
        }
    }
}
