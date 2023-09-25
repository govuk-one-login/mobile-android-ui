package uk.gov.ui.components.inputs.radio

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.RadioButton
import androidx.compose.material.RadioButtonDefaults
import androidx.compose.material.contentColorFor
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import uk.gov.ui.theme.GdsTheme
import uk.gov.ui.theme.smallPadding

@Composable
fun GdsRadioSelection(
    radioSelectionParams: RadioSelectionParameters
) {
    radioSelectionParams.apply {
        val (selectedOption, onOptionSelected) = radioState
        Column(
            modifier = Modifier
                .semantics(mergeDescendants = true) {}
                .background(
                    colors.background
                )
                .then(
                    colModifier
                ),
            horizontalAlignment = colAlignment
        ) {
            radioSelectionParams.radioOptions.forEach {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = (it.text == selectedOption),
                        onClick = { onOptionSelected(it.text) },
                        colors = RadioButtonDefaults.colors(
                            selectedColor = colors.primary
                        )
                    )
                    Text(
                        color = color ?: colors.contentColorFor(colors.background),
                        modifier = textModifier,
                        style = textStyle ?: MaterialTheme.typography.body1,
                        text = it.text,
                        textAlign = textAlign
                    )
                }
            }
        }
    }
}

data class RadioSelectionParameters(
    val radioOptions: List<RadioOption>,
    val radioState: MutableState<String>,
    val color: Color? = null,
    val textStyle: TextStyle? = null,
    val indent: Dp = 50.dp,
    val colModifier: Modifier = Modifier
        .padding(bottom = smallPadding),
    val colAlignment: Alignment.Horizontal = Alignment.Start,
    val textModifier: Modifier = Modifier.fillMaxWidth(),
    val textAlign: TextAlign = TextAlign.Start
) {
    override fun toString(): String = this::class.java.simpleName
}

class RadioSelectionProvider : PreviewParameterProvider<RadioSelectionParameters> {
    override val values: Sequence<RadioSelectionParameters> = sequenceOf(
        RadioSelectionParameters(
            radioOptions = listOf(
                RadioOption("option one"),
                RadioOption("option two")
            ),
            radioState = mutableStateOf("option one")
        )
    )
}

@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
private fun Preview(
    @PreviewParameter(RadioSelectionProvider::class)
    bulletListParameters: RadioSelectionParameters
) {
    GdsTheme {
        GdsRadioSelection(
            bulletListParameters
        )
    }
}
