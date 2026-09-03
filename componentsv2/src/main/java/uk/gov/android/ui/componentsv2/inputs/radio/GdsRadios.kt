

package uk.gov.android.ui.componentsv2.inputs.radio

import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.background
import androidx.compose.foundation.indication
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.LocalMinimumInteractiveComponentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import kotlinx.collections.immutable.ImmutableList
import uk.gov.android.ui.componentsv2.R
import uk.gov.android.ui.componentsv2.heading.GdsHeading
import uk.gov.android.ui.componentsv2.heading.GdsHeadingAlignment
import uk.gov.android.ui.componentsv2.inputs.radio.previewparameterprovider.GdsRadioOptionItemProvider
import uk.gov.android.ui.componentsv2.inputs.radio.previewparameterprovider.GdsRadiosPreviewDataProvider
import uk.gov.android.ui.componentsv2.inputs.radio.radiobuttonparameters.GdsRadioOptionItemPreviewData
import uk.gov.android.ui.componentsv2.inputs.radio.radiobuttonparameters.GdsRadiosContent
import uk.gov.android.ui.componentsv2.inputs.radio.radiobuttonparameters.GdsRadiosPreviewData
import uk.gov.android.ui.theme.m3.GdsLocalColorScheme
import uk.gov.android.ui.theme.m3.GdsTheme
import uk.gov.android.ui.theme.m3.Typography
import uk.gov.android.ui.theme.spacingDouble
import uk.gov.android.ui.theme.spacingSingle
import uk.gov.android.ui.theme.spacingSingleAndAHalf

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
    isFocusedForPreview: Boolean = false,
) {
    var isFocused by remember { mutableStateOf(false) }
    val showFocus = isFocused || isFocusedForPreview

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

    val interactionSource = remember { MutableInteractionSource() }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .onFocusChanged { isFocused = it.isFocused }
            .selectable(
                selected = isSelected,
                onClick = onOptionSelected,
                interactionSource = interactionSource,
                indication = null,
            )
            .semantics(mergeDescendants = true) {
                contentDescription = if (isSelected) selectedString else unselectedString
            },
        horizontalArrangement = Arrangement.Start,
    ) {
        RadioFocusIndicator(
            text = text,
            isSelected = isSelected,
            showFocus = showFocus,
            interactionSource = interactionSource,
        )
    }
}


@Composable
private fun RadioFocusIndicator(
    text: String,
    isSelected: Boolean,
    showFocus: Boolean,
    interactionSource: MutableInteractionSource,
) {
    CompositionLocalProvider(LocalMinimumInteractiveComponentSize provides 0.dp) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .padding(vertical = spacingSingleAndAHalf)
                .padding(end = spacingDouble)
                .clip(CircleShape)
                .indication(interactionSource, LocalIndication.current)
                .then(
                    if (showFocus) {
                        Modifier.background(GdsLocalColorScheme.current.focusState)
                    } else {
                        Modifier
                    },
                )
                .padding(4.dp),
        ) {
            RadioButton(
                selected = isSelected,
                colors = getRadioButtonColors(showFocus),
                // onClick is null so the RadioButton is not an independent focus target.
                // The parent Row's clickable modifier handles all interaction (touch, keyboard
                // Space/Enter) and is the single focus stop for the whole row.

                // Add clearAndSetSemantics to internal children of the RadioButton to ensure
                // TalkBack announcements are in line with the previous implementation,
                // treating the row as the single semantic node.
                onClick = null,
                interactionSource = interactionSource,
                modifier = Modifier.clearAndSetSemantics {},
            )
        }
    }
    Text(
        text = text,
        color = MaterialTheme.colorScheme.onBackground,
        style = Typography.bodyLarge,
        modifier = Modifier
            .fillMaxWidth()
            .padding(end = spacingSingle)
            .clearAndSetSemantics {},
    )
}
@Composable
private fun getRadioButtonColors(isFocused: Boolean) = if (isFocused) {
    RadioButtonDefaults.colors(
        selectedColor = GdsLocalColorScheme.current.focusStateContent,
        unselectedColor = GdsLocalColorScheme.current.focusStateContent,
    )
} else {
    RadioButtonDefaults.colors(
        selectedColor = GdsLocalColorScheme.current.selectedRadioButton,
        unselectedColor = GdsLocalColorScheme.current.unselectedRadioButton,
    )
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


@Composable
internal fun GdsRadiosSample(content: GdsRadiosContent) {
    var selectedIndex by rememberSaveable { mutableIntStateOf(content.selectedIndex ?: 0) }

    GdsRadios(
        items = content.items,
        selectedItem = selectedIndex,
        onItemSelected = { selectedIndex = it },
        title = content.title,
    )
}




@PreviewLightDark
@Composable
internal fun GdsRadioOptionItemPreview(
    @PreviewParameter(GdsRadioOptionItemProvider::class) data: GdsRadioOptionItemPreviewData,
) {
    GdsTheme {
        GdsRadioOptionItem(
            text = data.text,
            radioOption = data.text,
            isSelected = data.isSelected,
            onOptionSelected = {},
            index = 0,
            totalOptions = 1,
            isFocusedForPreview = data.isFocused,
        )
    }
}

@PreviewLightDark
@Composable
internal fun GdsRadiosPreview(
    @PreviewParameter(GdsRadiosPreviewDataProvider::class) radioSelectionItems: GdsRadiosPreviewData,
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

