package uk.gov.android.ui.testwrapper.componentsv2.inputs.radio

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import kotlinx.collections.immutable.persistentListOf
import uk.gov.android.ui.componentsv2.heading.GdsHeadingStyle
import uk.gov.android.ui.componentsv2.inputs.radio.GdsRadios
import uk.gov.android.ui.componentsv2.inputs.radio.GdsRadiosTitle
import uk.gov.android.ui.componentsv2.inputs.radio.LONG_OPTION
import uk.gov.android.ui.componentsv2.inputs.radio.OPTION1
import uk.gov.android.ui.componentsv2.inputs.radio.OPTION2
import uk.gov.android.ui.theme.m3.GdsTheme
import uk.gov.android.ui.theme.smallPadding

@Composable
fun GdsRadiosDemo(
    modifier: Modifier = Modifier,
    onSelected: (String) -> Unit = {},
) {
    GdsTheme {
        Column(modifier = modifier) {
            var selectedIndex by remember { mutableIntStateOf(0) }
            val radioItems = persistentListOf(OPTION1, OPTION2, LONG_OPTION)
            GdsRadios(
                items = radioItems,
                selectedItem = selectedIndex,
                onItemSelected = { selectedItem ->
                    selectedIndex = selectedItem
                    onSelected(radioItems[selectedIndex])
                },
                title = GdsRadiosTitle("GdsRadios example", GdsHeadingStyle.Body),
                modifier = Modifier.padding(smallPadding),
            )

            // Focus state preview — simulates external keyboard focus on the first item
            var focusedPreviewIndex by remember { mutableStateOf<Int?>(0) }
            GdsRadios(
                items = persistentListOf(OPTION1, OPTION2),
                selectedItem = null,
                onItemSelected = { focusedPreviewIndex = it },
                title = GdsRadiosTitle("Focus state (keyboard) without selection", GdsHeadingStyle.Body),
                focusedItemIndexForPreview = focusedPreviewIndex,
                modifier = Modifier.padding(smallPadding),
            )
        }
    }
}
