package uk.gov.android.ui.testwrapper.componentsv2.inputs.radio

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import kotlinx.collections.immutable.persistentListOf
import uk.gov.android.ui.componentsv2.heading.GdsHeadingStyle
import uk.gov.android.ui.componentsv2.inputs.radio.GdsRadiosTitle
import uk.gov.android.ui.componentsv2.inputs.radio.GdsRadios
import uk.gov.android.ui.componentsv2.inputs.radio.LONG_OPTION
import uk.gov.android.ui.componentsv2.inputs.radio.OPTION1
import uk.gov.android.ui.componentsv2.inputs.radio.OPTION2
import uk.gov.android.ui.theme.m3.GdsTheme
import uk.gov.android.ui.theme.spacingDouble

@Composable
fun GdsRadiosDemo(
    onSelected: (String) -> Unit = {}
) {
    GdsTheme {
        var selectedIndex by remember { mutableIntStateOf(0) }
        val radioItems = persistentListOf(OPTION1, OPTION2, LONG_OPTION)
        GdsRadios(
            items = radioItems,
            selectedItem = selectedIndex,
            onItemSelected = { selectedItem ->
                selectedIndex = selectedItem
                onSelected(radioItems[selectedIndex])
            },
            title = GdsRadiosTitle("GdsRadios example", GdsHeadingStyle.Title3),
        )
    }
}
