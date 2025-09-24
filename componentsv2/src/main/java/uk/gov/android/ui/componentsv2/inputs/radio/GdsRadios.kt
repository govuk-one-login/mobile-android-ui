package uk.gov.android.ui.componentsv2.inputs.radio

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.LocalMinimumInteractiveComponentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.pluralStringResource
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
import uk.gov.android.ui.componentsv2.heading.GdsHeading
import uk.gov.android.ui.componentsv2.heading.GdsHeadingAlignment
import uk.gov.android.ui.componentsv2.heading.GdsHeadingStyle
import uk.gov.android.ui.theme.m3.GdsLocalColorScheme
import uk.gov.android.ui.theme.m3.GdsTheme
import uk.gov.android.ui.theme.m3.Typography
import uk.gov.android.ui.theme.spacingDouble
import uk.gov.android.ui.theme.spacingSingle
import uk.gov.android.ui.theme.util.UnstableDesignSystemAPI

/**
 * A composable that displays a list of radio selection options.
 *
 * @param items The list of options to display.
 * @param selectedItem The index of the selected item, or `null` if no item is selected.
 * @param onItemSelected A callback function that is called when an item is selected.
 * @param modifier The modifier to apply to the layout.
 * @param title An optional title to display above the radio selection options.
 * @sample GdsRadiosSample
 */
@OptIn(UnstableDesignSystemAPI::class)
@Composable
fun GdsRadios(
    items: ImmutableList<String>,
    selectedItem: Int?,
    onItemSelected: (Int) -> Unit,
    modifier: Modifier = Modifier,
    title: GdsRadiosTitle? = null,
) {
    Column(
        modifier
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.Start,
    ) {
        title?.let {
            GdsHeading(
                text = it.text,
                style = it.style,
                textFontWeight = it.fontWeight,
                textAlign = GdsHeadingAlignment.LeftAligned,
                modifier = Modifier.padding(bottom = spacingDouble),
            )
        }

        items.forEachIndexed { index, option ->
            GdsRadioOptionItem(
                text = option,
                radioOption = option,
                isSelected = selectedItem == index,
                onOptionSelected = {
                    onItemSelected(index)
                },
                index = index,
                totalOptions = items.size,
            )
        }
    }
}

@Composable
@Suppress("LongMethod")
fun GdsRadioOptionItem(
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
        CompositionLocalProvider(LocalMinimumInteractiveComponentSize provides 0.dp) {
            RadioButton(
                selected = isSelected,
                colors = RadioButtonDefaults.colors(
                    selectedColor = GdsLocalColorScheme.current.selectedRadioButton,
                    unselectedColor = GdsLocalColorScheme.current.unselectedRadioButton,
                ),
                onClick = onOptionSelected,
                modifier = Modifier.padding(top = spacingDouble, end = spacingDouble, bottom = spacingDouble),
            )
        }
        Text(
            text = text,
            color = MaterialTheme.colorScheme.onBackground,
            style = Typography.bodyLarge,
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = spacingSingle),
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
    val pluralsResId =
        if (isSelected) R.plurals.radio_button_selected else R.plurals.radio_button_unselected
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

internal data class GdsRadiosPreviewData(
    val items: ImmutableList<String>,
    val title: GdsRadiosTitle? = null,
    val selectedIndex: Int? = null,
)

data class GdsRadiosContent(
    val items: ImmutableList<String>,
    val title: GdsRadiosTitle? = null,
    val selectedIndex: Int? = null,
)

@Composable
internal fun GdsRadiosSample(content: GdsRadiosContent) {
    val selectedIndex = remember { mutableIntStateOf(content.selectedIndex ?: 0) }

    GdsRadios(
        items = content.items,
        selectedItem = selectedIndex.intValue,
        onItemSelected = { selectedIndex.intValue = it },
        title = content.title,
    )
}

internal class GdsRadiosProvider : PreviewParameterProvider<GdsRadiosPreviewData> {
    override val values: Sequence<GdsRadiosPreviewData> = sequenceOf(
        GdsRadiosPreviewData(
            items = persistentListOf(OPTION1),
            title = GdsRadiosTitle(EXAMPLE_TITLE, GdsHeadingStyle.Body),
        ),
        GdsRadiosPreviewData(
            items = persistentListOf(OPTION1, OPTION2),
            title = GdsRadiosTitle("Example Heading", GdsHeadingStyle.Title3),
            selectedIndex = 1,
        ),
        GdsRadiosPreviewData(
            items = persistentListOf(OPTION1, OPTION2),
            title = GdsRadiosTitle("Example Bold Title", GdsHeadingStyle.Body, FontWeight.Bold),
            selectedIndex = 0,
        ),
        GdsRadiosPreviewData(
            items = persistentListOf(OPTION1, OPTION2, "option three"),
            selectedIndex = 2,
        ),
        GdsRadiosPreviewData(
            items = persistentListOf(OPTION1, OPTION2, LONG_OPTION),
            title = GdsRadiosTitle(EXAMPLE_TITLE, GdsHeadingStyle.Body),
            selectedIndex = 1,
        ),
        GdsRadiosPreviewData(
            items = persistentListOf(
                "option one: Lorem ipsum dolor sit amet, consectetur adipiscing elit",
                "option two: Lorem ipsum dolor sit amet, consectetur adipiscing elit",
                "option three:Lorem ipsum dolor sit amet, consectetur adipiscing elit",
                "option four:Lorem ipsum dolor sit amet, consectetur adipiscing elit",
            ),
            title = GdsRadiosTitle(EXAMPLE_TITLE, GdsHeadingStyle.Body),
            selectedIndex = 3,
        ),
    )
}

@PreviewLightDark
@Composable
internal fun GdsRadiosPreview(
    @PreviewParameter(GdsRadiosProvider::class) radioSelectionItems: GdsRadiosPreviewData,
) {
    GdsTheme {
        GdsRadios(
            items = radioSelectionItems.items,
            selectedItem = radioSelectionItems.selectedIndex,
            onItemSelected = {},
            title = radioSelectionItems.title,
            modifier = Modifier.padding(horizontal = spacingDouble),
        )
    }
}

const val OPTION1 = "option one"
const val OPTION2 = "option two"
const val LONG_OPTION = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed " +
    "do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim " +
    "ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut " +
    "aliquip ex ea commodo consequat"
private const val EXAMPLE_TITLE = "Example Title"
