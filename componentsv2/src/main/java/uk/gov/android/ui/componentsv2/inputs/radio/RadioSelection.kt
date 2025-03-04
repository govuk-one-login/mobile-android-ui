package uk.gov.android.ui.componentsv2.inputs.radio

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import uk.gov.android.ui.componentsv2.R
import uk.gov.android.ui.theme.GdsTheme
import uk.gov.android.ui.theme.m3.Typography
import uk.gov.android.ui.theme.smallPadding

@Composable
fun GdsRadioSelection(
    radioSelectionParams: RadioSelectionParameters,
    modifier: Modifier = Modifier,
) {
    radioSelectionParams.apply {
        val (selectedOption, onOptionSelected) = radioState
        Column(
            modifier
            .semantics(mergeDescendants = true) {}
            .then(
                colModifier,
            ),
            horizontalAlignment = colAlignment,
        ) {
            radioSelectionParams.title?.let {
                RadioSelectionTitle(it)
            }

            radioSelectionParams.radioOptions.forEach {
                Row(
                    modifier = Modifier
                        .clickable {
                            onOptionSelected(it)
                        },
                    verticalAlignment = Alignment.CenterVertically,
                ) {

                    RadioButton(
                        selected = (it.text == selectedOption?.text),
                        onClick = { onOptionSelected(it) },
                        colors = RadioButtonDefaults.colors(
                            selectedColor = MaterialTheme.colorScheme.primary,
                        ),
                    )
                    Text(
                        text = it.text,
                        color = MaterialTheme.colorScheme.onBackground,
                        style = textStyle ?: MaterialTheme.typography.bodyLarge,
                        textAlign = textAlign,
                        modifier = textModifier,
                    )
                }
            }
        }
    }
}

data class RadioSelectionParameters(
    val radioOptions: List<RadioOption>,
    val radioState: MutableState<RadioOption?>,
    val title: RadioSelectionTitle? = null,
    val color: Color? = null,
    val textStyle: TextStyle? = null,
    val colModifier: Modifier = Modifier
        .padding(bottom = smallPadding),
    val colAlignment: Alignment.Horizontal = Alignment.Start,
    val textModifier: Modifier = Modifier.fillMaxWidth(),
    val textAlign: TextAlign = TextAlign.Start,
) {
    override fun toString(): String = this::class.java.simpleName
}

@Composable
private fun RadioSelectionTitle(
    title: RadioSelectionTitle,
    modifier: Modifier = Modifier,
) {
    val titleContentDescription: String

    val textStyle = when (title.titleType) {
        TitleType.BoldText -> {
            titleContentDescription = title.text
            Typography.bodyLarge.copy(fontWeight = FontWeight.Bold)
        }

        TitleType.Heading -> {
            titleContentDescription = "${title.text} ${stringResource(R.string.heading)}"
            Typography.headlineSmall
        }

        TitleType.Text -> {
            titleContentDescription = title.text
            Typography.bodyLarge
        }
    }

    Text(
        text = title.text,
        style = textStyle,
        color = MaterialTheme.colorScheme.onBackground,
        modifier = modifier
            .padding(bottom = 4.dp)
            .semantics { contentDescription = titleContentDescription },
    )
}

enum class TitleType {
    BoldText, Heading, Text
}

data class RadioSelectionTitle(
    val text: String,
    val titleType: TitleType,
)
class RadioSelectionProvider : PreviewParameterProvider<RadioSelectionParameters> {
    override val values: Sequence<RadioSelectionParameters> = sequenceOf(
        RadioSelectionParameters(
            radioOptions = listOf(
                RadioOption("option one"),
                RadioOption("option two"),
            ),
            radioState = mutableStateOf(RadioOption("option one")),
            title = RadioSelectionTitle("Example Heading", TitleType.Heading)
        ),
        RadioSelectionParameters(
            radioOptions = listOf(
                RadioOption("option one"),
                RadioOption("option two"),
                RadioOption(
                    "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed " +
                    "do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim " +
                    "ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut " +
                    "aliquip ex ea commodo consequat"
                )
            ),
            radioState = mutableStateOf(RadioOption("option one")),
            title = RadioSelectionTitle("Example Heading", TitleType.Text)
        ),
        RadioSelectionParameters(
            radioOptions = listOf(
                RadioOption("option one"),
                RadioOption("option two"),
            ),
            radioState = mutableStateOf(RadioOption("option one")),
            title = RadioSelectionTitle("Example Heading", TitleType.BoldText)
        ),
        RadioSelectionParameters(
            radioOptions = listOf(
                RadioOption("option one"),
                RadioOption("option two"),
                RadioOption("option three"),
            ),
            radioState = mutableStateOf(RadioOption("option one")),
        ),
    )
}

@PreviewLightDark
@Composable
private fun Preview(
    @PreviewParameter(RadioSelectionProvider::class)
    radioSelectionParameters: RadioSelectionParameters,
) {
    GdsTheme {
        GdsRadioSelection(
            radioSelectionParameters,
        )
    }
}
