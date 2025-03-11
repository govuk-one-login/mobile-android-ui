package uk.gov.android.ui.componentsv2.inputs.radio

import androidx.compose.foundation.background
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
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.clearAndSetSemantics
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
import uk.gov.android.ui.theme.m3.GdsTheme
import uk.gov.android.ui.theme.m3.Typography
import uk.gov.android.ui.theme.smallPadding

@Composable
@Suppress("LongMethod")
fun GdsRadioSelection(
    radioSelectionParams: RadioSelectionParameters,
    modifier: Modifier = Modifier,
) {
    radioSelectionParams.apply {
        val (selectedOption, onOptionSelected) = radioState
        Column(
            modifier
                .background(MaterialTheme.colorScheme.background)
                .then(
                    colModifier,
                ),
            horizontalAlignment = colAlignment,
        ) {
            radioSelectionParams.title?.let {
                RadioSelectionTitle(it)
            }

            radioSelectionParams.optionText.forEachIndexed { index, option ->
                val selectedString = pluralStringResource(
                    id = R.plurals.radio_button_selected,
                    count = if (optionText.size == 1) 1 else index + 1,
                    option.text,
                    index + 1,
                    optionText.size,
                    optionText.size,
                )
                val unselectedString = pluralStringResource(
                    id = R.plurals.radio_button_unselected,
                    count = if (optionText.size == 1) 1 else index + 1,
                    option.text,
                    index + 1,
                    optionText.size,
                    optionText.size,
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .clearAndSetSemantics {
                            contentDescription = if (option.text == selectedOption?.text) {
                                selectedString
                            } else {
                                unselectedString
                            }
                        }
                        .semantics(mergeDescendants = true) {}
                        .clickable {
                            onOptionSelected(option)
                        },
                ) {
                    RadioButton(
                        selected = (option.text == selectedOption?.text),
                        colors = RadioButtonDefaults.colors(
                            selectedColor = MaterialTheme.colorScheme.primary,
                        ),
                        onClick = { onOptionSelected(option) },
                    )
                    Text(
                        text = option.text,
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
    val optionText: List<RadioOption>,
    val radioState: MutableState<RadioOption?>,
    val title: RadioSelectionTitle? = null,
    val color: Color? = null,
    val textStyle: TextStyle? = null,
    val colModifier: Modifier = Modifier
        .padding(bottom = smallPadding),
    val colAlignment: Alignment.Horizontal = Alignment.Start,
    val textModifier: Modifier = Modifier
        .fillMaxWidth()
        .padding(end = smallPadding),
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
            .padding(bottom = 16.dp, start = 16.dp)
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
            optionText = listOf(
                RadioOption("option one"),
                RadioOption("option two"),
            ),
            radioState = mutableStateOf(RadioOption("option one")),
            title = RadioSelectionTitle("Example Heading", TitleType.Heading),
        ),
        RadioSelectionParameters(
            optionText = listOf(
                RadioOption("option one"),
                RadioOption("option two"),
                RadioOption(
                    "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed " +
                        "do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim " +
                        "ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut " +
                        "aliquip ex ea commodo consequat",
                ),
            ),
            radioState = mutableStateOf(RadioOption("option one")),
            title = RadioSelectionTitle("Example Heading", TitleType.Text),
        ),
        RadioSelectionParameters(
            optionText = listOf(
                RadioOption("option one"),
                RadioOption("option two"),
            ),
            radioState = mutableStateOf(RadioOption("option one")),
            title = RadioSelectionTitle("Example Heading", TitleType.BoldText),
        ),
        RadioSelectionParameters(
            optionText = listOf(
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
