package uk.gov.android.ui.testwrapper.patterns.leftalignedscreen

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf
import uk.gov.android.ui.componentsv2.list.ListItem
import uk.gov.android.ui.componentsv2.list.ListTitle
import uk.gov.android.ui.componentsv2.list.TitleType
import uk.gov.android.ui.patterns.leftalignedscreen.LeftAlignedScreenBodyV2
import uk.gov.android.ui.patterns.leftalignedscreen.LeftAlignedScreenButton
import uk.gov.android.ui.patterns.leftalignedscreen.LeftAlignedScreenV2

@SuppressLint("ComposeModifierMissing")
@Composable
fun LeftAlignedScreenDemo() {
    var selectedIndex by remember { mutableIntStateOf(0) }
    LeftAlignedScreenV2(
        title = "Left Aligned Screen",
        body = persistentListOf(
            LeftAlignedScreenBodyV2.Warning(text = "Warning"),
            LeftAlignedScreenBodyV2.Selection(
                items = persistentListOf("One", "Two", "Three"),
                selectedItem = selectedIndex,
                onItemSelected = { selectedItem ->
                    selectedIndex = selectedItem
                },
            ),
            LeftAlignedScreenBodyV2.SecondaryButton(text = "Another button", onClick = {}),
            LeftAlignedScreenBodyV2.NumberedList(
                title = ListTitle(text = "Numbered list", titleType = TitleType.Text),
                list = listItems(),
            ),
            LeftAlignedScreenBodyV2.SecondaryButton(text = "Yet another button", onClick = {}),
        ),
        primaryButton = LeftAlignedScreenButton(text = "Allow", onClick = {}),
        secondaryButton = LeftAlignedScreenButton(text = "Cancel", onClick = {}),
        forceScroll = true,
    )
}

@Composable
fun LeftAlignedScreenNoTitleDemo() {
    var selectedIndex by remember { mutableIntStateOf(0) }
    LeftAlignedScreenV2(
        body = persistentListOf(
            LeftAlignedScreenBodyV2.Warning(text = "Warning"),
            LeftAlignedScreenBodyV2.Selection(
                items = persistentListOf("One", "Two", "Three"),
                selectedItem = selectedIndex,
                onItemSelected = { selectedItem ->
                    selectedIndex = selectedItem
                },
            ),
            LeftAlignedScreenBodyV2.SecondaryButton(text = "Another button", onClick = {}),
            LeftAlignedScreenBodyV2.BulletList(
                title = ListTitle(text = "Numbered list", titleType = TitleType.Text),
                items = listItems(),
            ),
            LeftAlignedScreenBodyV2.SecondaryButton(text = "Yet another button", onClick = {}),
        ),
        primaryButton = LeftAlignedScreenButton(text = "Allow", onClick = {}),
        secondaryButton = LeftAlignedScreenButton(text = "Cancel", onClick = {}),
        forceScroll = true,
    )
}

private fun listItems(): PersistentList<ListItem> {
    return persistentListOf(
        ListItem(text = "Item one"),
        ListItem(text = "Item two"),
        ListItem(text = "Item three"),
        ListItem(text = "Item four"),
        ListItem(text = "Item five"),
        ListItem(text = "Item six"),
        ListItem(text = "Item seven"),
        ListItem(text = "Item eight"),
        ListItem(text = "Item nine"),
        ListItem(text = "Item ten"),
        ListItem(text = "Item eleven"),
        ListItem(text = "Item twelve"),
        ListItem(text = "Item thirteen"),
        ListItem(text = "Item fourteen"),
        ListItem(text = "Item fifteen"),
        ListItem(text = "Item sixteen"),
        ListItem(text = "Item seventeen"),
        ListItem(text = "Item eighteen"),
        ListItem(text = "Item nineteen"),
        ListItem(text = "Item twenty"),
        ListItem(text = "Item twenty one"),
        ListItem(text = "Item twenty two"),
        ListItem(text = "Item twenty three"),
        ListItem(text = "Item twenty four"),
    )
}
