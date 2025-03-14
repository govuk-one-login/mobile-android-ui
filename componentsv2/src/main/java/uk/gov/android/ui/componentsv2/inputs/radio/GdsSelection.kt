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
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import uk.gov.android.ui.componentsv2.R
import uk.gov.android.ui.theme.m3.GdsTheme
import uk.gov.android.ui.theme.m3.Typography
import uk.gov.android.ui.theme.spacingDouble
import uk.gov.android.ui.theme.spacingSingle

@Composable
fun GdsSelection(
    radioSelectionItems: ImmutableList<String>,
    modifier: Modifier = Modifier,
    content: @Composable (() -> Unit)? = null,
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
        content?.let { it() }

        radioSelectionItems.forEachIndexed { index, option ->
            RadioSelectionOptionItem(
                text = option,
                radioOption = option,
                isSelected = selectedOption == option,
                onOptionSelected = {
                    onOptionSelected(option)
                },
                index = index,
                totalOptions = radioSelectionItems.size,
            )
        }
    }
}

@Composable
fun GdsSelection(
    radioSelectionItems: ImmutableList<String>,
    modifier: Modifier = Modifier,
    title: RadioSelectionTitle? = null,
) {
    GdsSelection(
        radioSelectionItems,
        modifier,
        content = {
            title?.let { RadioSelectionTitle(it) }
        },
    )
}

@Composable
@Suppress("LongMethod")
fun RadioSelectionOptionItem(
    text: String,
    radioOption: String,
    isSelected: Boolean,
    onOptionSelected: () -> Unit,
    index: Int,
    totalOptions: Int,
    modifier: Modifier = Modifier,
) {
    val selectedString = getRadioOptionAccessibilityText(
        index = index,
        option = radioOption,
        totalOptions = totalOptions,
        isSelected = isSelected,
    )

    val unselectedString = getRadioOptionAccessibilityText(
        index = index,
        option = radioOption,
        totalOptions = totalOptions,
        isSelected = false,
    )

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .clearAndSetSemantics {
                contentDescription = if (isSelected) selectedString else unselectedString
            }
            .semantics(mergeDescendants = true) {}
            .clickable(onClick = onOptionSelected),
        horizontalArrangement = Arrangement.Start,
    ) {
        RadioButton(
            selected = isSelected,
            colors = RadioButtonDefaults.colors(selectedColor = MaterialTheme.colorScheme.primary),
            onClick = onOptionSelected,
        )
        Text(
            text = text,
            color = MaterialTheme.colorScheme.onBackground,
            style = Typography.bodyLarge,
            modifier = Modifier.padding(end = spacingSingle),
        )
    }
}

@Composable
private fun getRadioOptionAccessibilityText(
    index: Int,
    option: String,
    totalOptions: Int,
    isSelected: Boolean,
): String {
    val pluralsResId = if (isSelected) R.plurals.radio_button_selected else R.plurals.radio_button_unselected
    return if (index == 0 && totalOptions > 1) {
        pluralStringResource(
            id = pluralsResId,
            count = 1,
            option,
            totalOptions,
            totalOptions,
        )
    } else {
        pluralStringResource(
            id = pluralsResId,
            count = index + 1,
            option,
            index + 1,
            totalOptions,
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
            titleContentDescription = stringResource(R.string.heading, title.text)
            MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold)
        }

        TitleType.Heading -> {
            titleContentDescription = stringResource(R.string.heading, title.text)
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
            items = persistentListOf("option one", "option two"),
            title = RadioSelectionTitle("Example Bold Title", TitleType.BoldText),
        ),
        RadioSelectionPreviewData(
            items = persistentListOf("option one", "option two", "option three"),
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
            items = persistentListOf(
                "option one: Lorem ipsum dolor sit amet, consectetur adipiscing elit",
                "option two: Lorem ipsum dolor sit amet, consectetur adipiscing elit",
                "option three:Lorem ipsum dolor sit amet, consectetur adipiscing elit",
                "option four:Lorem ipsum dolor sit amet, consectetur adipiscing elit",
            ),
            title = RadioSelectionTitle("Example Title", TitleType.Text),
        ),
    )
}

@PreviewLightDark
@Composable
private fun Preview(
    @PreviewParameter(RadioSelectionProvider::class) radioSelectionItems: RadioSelectionPreviewData,
) {
    GdsTheme {
        GdsSelection(
            radioSelectionItems = radioSelectionItems.items,
            title = radioSelectionItems.title,
        )
    }
}
