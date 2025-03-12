package uk.gov.android.ui.componentsv2.inputs.radio

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import uk.gov.android.ui.componentsv2.R
import uk.gov.android.ui.theme.m3.GdsTheme
import uk.gov.android.ui.theme.m3.Typography
import uk.gov.android.ui.theme.spacingDouble

@Composable
fun GdsRadioSelection(
    radioSelectionItems: ImmutableList<String>,
    modifier: Modifier = Modifier,
    title: RadioSelectionTitle? = null,
) {
    val selectedOptionState = remember { mutableStateOf(radioSelectionItems.firstOrNull()) }
    val selectedOption = selectedOptionState.value
    val onOptionSelected: (String) -> Unit = { selectedOptionState.value = it }

    Column(
        modifier
            .background(MaterialTheme.colorScheme.background)
            .padding(end = spacingDouble),
        horizontalAlignment = Alignment.Start,
    ) {
        title?.let { RadioSelectionTitle(it) }

        radioSelectionItems.forEachIndexed { index, option ->
            RadioSelectionOptionItem(
                text = option,
                option = option,
                selectedOption = selectedOption,
                onOptionSelected = onOptionSelected,
                index = index,
                optionTextSize = radioSelectionItems.size,
            )
        }
    }
}

@Composable
@Suppress("LongMethod")
fun RadioSelectionOptionItem(
    text: String,
    option: String,
    selectedOption: String?,
    onOptionSelected: (String) -> Unit,
    index: Int,
    optionTextSize: Int,
    modifier: Modifier = Modifier,
) {
    val selectedString = getRadioOptionAccessibilityText(
        index = index,
        option = RadioOption(option),
        optionTextSize = optionTextSize,
        isSelected = option == selectedOption,
    )

    val unselectedString = getRadioOptionAccessibilityText(
        index = index,
        option = RadioOption(option),
        optionTextSize = optionTextSize,
        isSelected = false,
    )

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .clearAndSetSemantics {
                contentDescription = if (option == selectedOption) {
                    selectedString
                } else {
                    unselectedString
                }
            }
            .semantics(mergeDescendants = true) {}
            .clickable { onOptionSelected(option) },
        horizontalArrangement = Arrangement.Start,
    ) {
        RadioButton(
            selected = (option == selectedOption),
            colors = RadioButtonDefaults.colors(selectedColor = MaterialTheme.colorScheme.primary),
            onClick = { onOptionSelected(option) },
            modifier = Modifier.padding(start = 4.dp),
        )
        Text(
            text = text,
            color = MaterialTheme.colorScheme.onBackground,
            style = Typography.bodyLarge,
            modifier = Modifier.padding(end = spacingDouble),
        )
    }
}

@Composable
private fun getRadioOptionAccessibilityText(
    index: Int,
    option: RadioOption,
    optionTextSize: Int,
    isSelected: Boolean,
): String {
    val pluralsResId = if (isSelected) R.plurals.radio_button_selected else R.plurals.radio_button_unselected
    return if (index == 0 && optionTextSize > 1) {
        pluralStringResource(
            id = pluralsResId,
            count = 1,
            option.text,
            optionTextSize,
            optionTextSize,
        )
    } else {
        pluralStringResource(
            id = pluralsResId,
            count = index + 1,
            option.text,
            index + 1,
            optionTextSize,
        )
    }
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
            MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold)
        }

        TitleType.Heading -> {
            titleContentDescription = "${title.text} ${stringResource(R.string.heading)}"
            MaterialTheme.typography.headlineSmall
        }

        TitleType.Text -> {
            titleContentDescription = title.text
            MaterialTheme.typography.bodyLarge
        }
    }

    Text(
        text = title.text,
        style = textStyle,
        color = MaterialTheme.colorScheme.onBackground,
        modifier = modifier
            .padding(start = spacingDouble, bottom = spacingDouble)
            .semantics { contentDescription = titleContentDescription },
    )
}

internal data class RadioSelectionPreviewData(
    val items: ImmutableList<String>,
    val title: RadioSelectionTitle? = null,
)

internal class RadioSelectionProvider : PreviewParameterProvider<RadioSelectionPreviewData> {
    override val values: Sequence<RadioSelectionPreviewData> = sequenceOf(
        RadioSelectionPreviewData(
            items = persistentListOf("option one"),
            title = RadioSelectionTitle("Example Title", TitleType.Text),
        ),
        RadioSelectionPreviewData(
            items = persistentListOf("option one", "option two"),
            title = RadioSelectionTitle("Example Heading", TitleType.Heading),
        ),
        RadioSelectionPreviewData(
            items = persistentListOf(
                "option one",
                "option two",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed " +
                    "do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim " +
                    "ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut " +
                    "aliquip ex ea commodo consequat",
            ),
            title = RadioSelectionTitle("Example Title", TitleType.Text),
        ),
        RadioSelectionPreviewData(
            items = persistentListOf("option one", "option two"),
            title = RadioSelectionTitle("Example Bold Text", TitleType.BoldText),
        ),
        RadioSelectionPreviewData(
            items = persistentListOf("option one", "option two", "option three"),
        ),
    )
}

@PreviewLightDark
@Composable
private fun Preview(
    @PreviewParameter(RadioSelectionProvider::class) radioSelectionItems: RadioSelectionPreviewData,
) {
    GdsTheme {
        GdsRadioSelection(
            radioSelectionItems = radioSelectionItems.items,
            title = radioSelectionItems.title,
        )
    }
}
