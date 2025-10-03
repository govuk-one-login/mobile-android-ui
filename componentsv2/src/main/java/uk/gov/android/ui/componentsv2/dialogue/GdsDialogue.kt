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
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.window.Dialog
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
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
    contentText: Int?,
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
                            text = stringResource(contentText),
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
 * Display the dialogue buttons in a Right To Left direction which ensures that the buttons
 * overflow in the correct order
 *
 * @param buttonParameters Used to define the dialogue buttons
 */
@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun ButtonRow(buttonParameters: ImmutableList<DialogueButtonParameters>) {
    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
        FlowRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(mediumPadding),
            horizontalArrangement = Arrangement.spacedBy(
                mediumPadding,
                Alignment.Start,
            ),
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
}

data class DialogueButtonParameters(
    val text: String,
    val buttonType: ButtonTypeV2,
    val onClick: () -> Unit = {},
)

internal data class DialogueParameters(
    val headingText: String?,
    val contentText: Int?,
    val buttonParameters: ImmutableList<DialogueButtonParameters>,
    val modifier: Modifier = Modifier,
    val onDismissRequest: () -> Unit = {},
)

internal class DialogProvider : PreviewParameterProvider<DialogueParameters> {
    override val values: Sequence<DialogueParameters> = sequenceOf(
        DialogueParameters(
            headingText = TITLE1,
            contentText = R.string.dialog_example_content,
            buttonParameters = persistentListOf(
                DialogueButtonParameters(
                    buttonType = ButtonTypeV2.Primary(),
                    text = BUTTON_TEXT_YES,
                ),
                DialogueButtonParameters(
                    buttonType = ButtonTypeV2.Secondary(),
                    text = BUTTON_TEXT_NO,
                ),
            ),
        ),
        DialogueParameters(
            headingText = TITLE2,
            contentText = R.string.dialog_example_content,
            buttonParameters = persistentListOf(
                DialogueButtonParameters(
                    buttonType = ButtonTypeV2.Secondary(),
                    text = BUTTON_TEXT_CONFIRM,
                ),
                DialogueButtonParameters(
                    buttonType = ButtonTypeV2.Secondary(),
                    text = BUTTON_TEXT_DISMISS,
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
    dialogueParameters: DialogueParameters,
) {
    dialogueParameters.apply {
        GdsTheme {
            GdsDialogue(
                headingText = headingText,
                contentText = contentText,
                buttonParameters = buttonParameters,
            ) {}
        }
    }
}

internal const val TITLE1 = "Horizontal buttons"
internal const val TITLE2 = "Overflow buttons"
internal const val BUTTON_TEXT_YES = "Yes"
private const val BUTTON_TEXT_NO = "No"
internal const val BUTTON_TEXT_CONFIRM = "Confirming action"
internal const val BUTTON_TEXT_DISMISS = "Dismiss action"
