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
import androidx.compose.material.MaterialTheme
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
import uk.gov.android.ui.components.ButtonParameters
import uk.gov.android.ui.components.ButtonType
import uk.gov.android.ui.components.GdsButton
import uk.gov.android.ui.components.GdsHeading
import uk.gov.android.ui.components.HeadingParameters
import uk.gov.android.ui.components.HeadingSize
import uk.gov.android.ui.components.content.ContentParameters
import uk.gov.android.ui.components.content.GdsContent
import uk.gov.android.ui.components.content.GdsContentText.GdsContentTextArray
import uk.gov.android.ui.components.content.GdsContentText.GdsContentTextString
import uk.gov.android.ui.components.inputs.number.GdsNumberInput
import uk.gov.android.ui.components.inputs.number.NumberInputParameters
import uk.gov.android.ui.pages.R.string
import uk.gov.android.ui.theme.GdsTheme
import uk.gov.android.ui.theme.hintTextGrey
import uk.gov.android.ui.theme.mediumPadding
import uk.gov.android.ui.theme.smallPadding
import uk.gov.android.ui.theme.xsmallPadding

@Suppress("LongMethod")
@Composable
fun NumberInputQuestion(
    params: NumberInputQuestionParameters
) {
    params.apply {
        GdsTheme {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(
                        bottom = mediumPadding,
                        top = mediumPadding
                    ),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Content(params)
                Buttons(params)
            }
        }
    }
}

@Composable
@Suppress("LongMethod")
internal fun Content(
    params: NumberInputQuestionParameters
) {
    params.apply {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(
                    start = smallPadding,
                    end = smallPadding
                )
        ) {
            GdsHeading(
                headingParameters = HeadingParameters(
                    modifier = Modifier
                        .fillMaxWidth(),
                    size = HeadingSize.H1(),
                    text = title,
                    textAlign = titleAlign,
                    padding = PaddingValues(
                        bottom = titleBottomPadding
                    )
                )
            )
            content?.let {
                GdsContent(
                    contentParameters = ContentParameters(
                        modifier = Modifier
                            .padding(
                                bottom = xsmallPadding
                            ),
                        internalColumnModifier = Modifier
                            .padding(
                                bottom = xsmallPadding
                            ),
                        resource = listOf(
                            GdsContentTextArray(
                                text = content
                            )
                        ),
                        textAlign = contentAlign
                    )
                )
            }
            GdsNumberInput(
                params = NumberInputParameters(
                    numberState = numberState,
                    isValid = isValid,
                    validateInput = validateInput,
                    inputLabel = inputLabel,
                    errorMessage = errorMessage
                )
            )
            hintText?.let {
                GdsContent(
                    contentParameters = ContentParameters(
                        modifier = Modifier
                            .padding(
                                start = smallPadding,
                                end = smallPadding
                            ),
                        resource = listOf(
                            GdsContentTextString(
                                text = intArrayOf(it)
                            )
                        ),
                        textAlign = TextAlign.Start,
                        textStyle = MaterialTheme.typography.body2,
                        color = hintTextGrey
                    )
                )
            }
        }
    }
}

@Composable
internal fun Buttons(
    params: NumberInputQuestionParameters
) {
    params.apply {
        Column(
            modifier = Modifier
                .padding(
                    end = smallPadding,
                    start = smallPadding,
                    top = mediumPadding
                )
        ) {
            GdsButton(
                buttonParameters = ButtonParameters(
                    buttonType = ButtonType.PRIMARY(),
                    text = primaryButtonText,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = xsmallPadding),
                    onClick = onPrimary,
                    enabled = isValid.value
                )
            )
        }
    }
}

data class NumberInputQuestionParameters(
    val numberState: MutableState<String>,
    val isValid: MutableState<Boolean>,
    val validateInput: (String) -> Boolean = { true },
    @StringRes
    val inputLabel: Int? = null,
    @StringRes
    val errorMessage: Int? = null,
    @StringRes
    val hintText: Int? = null,
    @ArrayRes
    val content: Int? = null,
    val contentAlign: TextAlign = TextAlign.Start,
    var onPrimary: () -> Unit = {},
    @StringRes
    val primaryButtonText: Int,
    @StringRes
    val title: Int,
    val titleAlign: TextAlign = TextAlign.Start,
    val titleBottomPadding: Dp = mediumPadding
)

class NumberInputQuestionProvider : PreviewParameterProvider<NumberInputQuestionParameters> {
    override val values: Sequence<NumberInputQuestionParameters> = sequenceOf(
        NumberInputQuestionParameters(
            title = string.preview__BrpInstructions__title,
            numberState = mutableStateOf(""),
            isValid = mutableStateOf(false),
            validateInput = { true },
            inputLabel = string.preview__BrpInstructions__subtitle_1,
            errorMessage = string.preview__BrpInstructions__help_text,
            primaryButtonText = string.preview__BrpInstructions__primary_button
        )
    )
}

@Preview(
    backgroundColor = 0xFFFBFDF8,
    showBackground = true,
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Preview(
    backgroundColor = 0xFF000000,
    showBackground = true,
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
private fun Preview(
    @PreviewParameter(NumberInputQuestionProvider::class)
    parameters: NumberInputQuestionParameters
) {
    NumberInputQuestion(
        parameters
    )
}
