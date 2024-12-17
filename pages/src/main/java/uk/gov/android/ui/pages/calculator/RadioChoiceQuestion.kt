package uk.gov.android.ui.pages.calculator

import android.content.res.Configuration
import androidx.annotation.ArrayRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.Dp
import uk.gov.android.ui.components.GdsHeading
import uk.gov.android.ui.components.HeadingParameters
import uk.gov.android.ui.components.HeadingSize
import uk.gov.android.ui.components.buttons.ButtonParameters
import uk.gov.android.ui.components.buttons.ButtonType
import uk.gov.android.ui.components.buttons.GdsButton
import uk.gov.android.ui.components.content.ContentParameters
import uk.gov.android.ui.components.content.GdsContent
import uk.gov.android.ui.components.content.GdsContentText.GdsContentTextArray
import uk.gov.android.ui.components.inputs.radio.GdsRadioSelection
import uk.gov.android.ui.components.inputs.radio.RadioOption
import uk.gov.android.ui.components.inputs.radio.RadioSelectionParameters
import uk.gov.android.ui.pages.R.string
import uk.gov.android.ui.theme.GdsTheme
import uk.gov.android.ui.theme.mediumPadding
import uk.gov.android.ui.theme.smallPadding
import uk.gov.android.ui.theme.xsmallPadding
import uk.gov.android.ui.pages.R as pagesR

@Suppress("LongMethod")
@Composable
fun RadioChoiceQuestion(
    radioChoiceQuestionParameters: RadioChoiceQuestionParameters,
) {
    radioChoiceQuestionParameters.apply {
        GdsTheme {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(
                        bottom = mediumPadding,
                        top = mediumPadding,
                    ),
                verticalArrangement = Arrangement.SpaceBetween,
            ) {
                Content(radioChoiceQuestionParameters)
                Buttons(radioChoiceQuestionParameters)
            }
        }
    }
}

@Composable
@Suppress("LongMethod")
internal fun Content(
    radioChoiceQuestionParameters: RadioChoiceQuestionParameters,
) {
    radioChoiceQuestionParameters.apply {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            GdsHeading(
                headingParameters = HeadingParameters(
                    modifier = Modifier
                        .fillMaxWidth(),
                    size = HeadingSize.H1(),
                    text = title,
                    textAlign = titleAlign,
                    padding = PaddingValues(
                        end = smallPadding,
                        start = smallPadding,
                        bottom = titleBottomPadding,
                    ),
                ),
            )
            content?.let {
                GdsContent(
                    contentParameters = ContentParameters(
                        modifier = Modifier
                            .padding(
                                end = smallPadding,
                                start = smallPadding,
                                bottom = xsmallPadding,
                            ),
                        internalColumnModifier = Modifier
                            .padding(
                                bottom = xsmallPadding,
                            ),
                        resource = listOf(
                            GdsContentTextArray(
                                text = content,
                            ),
                        ),
                        textAlign = contentAlign,
                    ),
                )
            }
            GdsRadioSelection(
                radioSelectionParams = RadioSelectionParameters(
                    radioOptions = radioOptions,
                    radioState = radioState,
                ),
            )
        }
    }
}

@Composable
internal fun Buttons(
    radioChoiceQuestionParameters: RadioChoiceQuestionParameters,
) {
    radioChoiceQuestionParameters.apply {
        Column(
            modifier = Modifier
                .padding(
                    end = smallPadding,
                    start = smallPadding,
                    top = mediumPadding,
                ),
        ) {
            GdsButton(
                buttonParameters = ButtonParameters(
                    buttonType = ButtonType.PRIMARY(),
                    text = primaryButtonText,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = xsmallPadding),
                    onClick = onPrimary,
                    enabled = radioChoiceQuestionParameters.radioState.value != null,
                ),
            )
        }
    }
}

data class RadioChoiceQuestionParameters(
    @ArrayRes
    val content: Int? = null,
    val radioOptions: List<RadioOption>,
    val radioState: MutableState<RadioOption?>,
    val contentAlign: TextAlign = TextAlign.Start,
    var onPrimary: () -> Unit = {},
    var onHelp: () -> Unit = {},
    @StringRes
    val primaryButtonText: Int,
    @StringRes
    val title: Int,
    val titleAlign: TextAlign = TextAlign.Start,
    val titleBottomPadding: Dp = mediumPadding,
)

class RadioChoiceQuestionProvider : PreviewParameterProvider<RadioChoiceQuestionParameters> {
    private val radioOptionOne = "option one"
    private val radioOptionTwo = "option two"
    override val values: Sequence<RadioChoiceQuestionParameters> = sequenceOf(
        RadioChoiceQuestionParameters(
            title = string.preview__BrpInstructions__title,
            content = pagesR.array.preview__BrpInstructions__array_1,
            radioOptions = listOf(
                RadioOption(radioOptionOne),
                RadioOption(radioOptionTwo),
            ),
            radioState = mutableStateOf(RadioOption(radioOptionOne)),
            primaryButtonText = string.preview__BrpInstructions__primary_button,
        ),
        RadioChoiceQuestionParameters(
            title = string.preview__BrpInstructions__title,
            content = pagesR.array.preview__BrpInstructions__array_1,
            radioOptions = listOf(
                RadioOption(radioOptionOne),
                RadioOption(radioOptionTwo),
            ),
            radioState = mutableStateOf(null),
            primaryButtonText = string.preview__BrpInstructions__primary_button,
        ),
    )
}

@Preview(
    backgroundColor = 0xFFFBFDF8,
    showBackground = true,
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
)
@Preview(
    backgroundColor = 0xFF000000,
    showBackground = true,
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Composable
private fun Preview(
    @PreviewParameter(RadioChoiceQuestionProvider::class)
    parameters: RadioChoiceQuestionParameters,
) {
    RadioChoiceQuestion(
        parameters,
    )
}
