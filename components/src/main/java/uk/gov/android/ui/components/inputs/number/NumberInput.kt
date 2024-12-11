package uk.gov.android.ui.components.inputs.number

import android.content.res.Configuration
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import uk.gov.android.ui.components.R
import uk.gov.android.ui.theme.GdsTheme
import uk.gov.android.ui.theme.smallPadding

@Composable
fun GdsNumberInput(
    params: NumberInputParameters,
) {
    params.apply {
        val (inputNumber, onInputChanged) = numberState
        val (isValid, onValidChange) = isValid
        Column(
            modifier = Modifier
                .semantics(mergeDescendants = true) {}
                .background(
                    colors.background,
                )
                .then(
                    colModifier,
                ),
        ) {
            OutlinedTextField(
                modifier = Modifier
                    .testTag("numberInput")
                    .fillMaxWidth(),
                value = inputNumber,
                onValueChange = {
                    onInputChanged(it)
                    onValidChange(validateInput(it))
                },
                label = {
                    inputLabel?.let {
                        Text(stringResource(id = inputLabel))
                    }
                },
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = colors.background,
                ),
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                isError = inputNumber.isNotEmpty() && !isValid,
            )
            if (inputNumber.isNotEmpty() && !isValid && errorMessage != null) {
                Text(
                    modifier = Modifier
                        .padding(start = smallPadding),
                    text = stringResource(id = errorMessage),
                    style = MaterialTheme.typography.body2,
                    color = Color.Red,
                )
            }
        }
    }
}

data class NumberInputParameters(
    val numberState: MutableState<String>,
    val isValid: MutableState<Boolean>,
    val validateInput: (String) -> Boolean = { true },
    @StringRes
    val inputLabel: Int? = null,
    @StringRes
    val errorMessage: Int? = null,
    val textStyle: TextStyle? = null,
    val colModifier: Modifier = Modifier
        .padding(bottom = smallPadding),
) {
    override fun toString(): String = this::class.java.simpleName
}

class NumberInputProvider : PreviewParameterProvider<NumberInputParameters> {
    override val values: Sequence<NumberInputParameters> = sequenceOf(
        NumberInputParameters(
            numberState = mutableStateOf(""),
            isValid = mutableStateOf(false),
            validateInput = { false },
            inputLabel = R.string.preview__GdsHeading__h4,
            errorMessage = R.string.preview__GdsContent__oneLine_0,
        ),
    )
}

@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
)
@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Composable
private fun Preview(
    @PreviewParameter(NumberInputProvider::class)
    numberInputParams: NumberInputParameters,
) {
    GdsTheme {
        GdsNumberInput(
            numberInputParams,
        )
    }
}
