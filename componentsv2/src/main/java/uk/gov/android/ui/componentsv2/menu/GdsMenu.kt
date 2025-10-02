package uk.gov.android.ui.componentsv2.menu

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalRippleConfiguration
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import kotlinx.collections.immutable.ImmutableList
import uk.gov.android.ui.componentsv2.button.GdsButtonDefaults
import uk.gov.android.ui.theme.m3.GdsLocalColorScheme

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("ComposeModifierMissing")
@Composable
fun GdsMenu(
    expanded: Boolean,
    content: ImmutableList<GdsMenuContent>,
    onDismissRequest: () -> Unit = {},
) {
    var focusStateEnabled by remember { mutableStateOf(false) }
    val colour = getRippleColour(focusStateEnabled)
    CompositionLocalProvider(
        LocalRippleConfiguration provides GdsButtonDefaults.gdsRippleConfig(colour),
    ) {
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = onDismissRequest,
            modifier = Modifier
                .background(color = GdsLocalColorScheme.current.menuItem),
        ) {
            content.forEach { item ->
                var isItemFocused by remember { mutableStateOf(false) }
                DropdownMenuItem(
                    text = {
                        Text(
                            text = item.title,
                            style = MaterialTheme.typography.bodyMedium.copy(
                                fontWeight = FontWeight.Normal,
                            ),
                        )
                    },
                    onClick = item.onClick,
                    colors = MenuDefaults.itemColors(
                        textColor = getTextColour(isItemFocused),
                    ),
                    modifier = Modifier
                        .background(color = getBackgroundColour(isItemFocused))
                        .semantics(mergeDescendants = true) { }
                        .onFocusChanged { focusState ->
                            focusStateEnabled = focusState.isFocused
                            isItemFocused = focusState.isFocused
                        },
                )
            }
        }
    }
}

@Composable
private fun getRippleColour(isInFocus: Boolean): Color {
    return if (isInFocus) {
        GdsLocalColorScheme.current.focusButtonHighlighted
    } else {
        GdsLocalColorScheme.current.menuItemHighlighted
    }
}

@Composable
private fun getBackgroundColour(isInFocus: Boolean): Color {
    return if (isInFocus) {
        GdsLocalColorScheme.current.focusState
    } else {
        GdsLocalColorScheme.current.menuItem
    }
}

@Composable
private fun getTextColour(isInFocus: Boolean): Color {
    return if (isInFocus) {
        GdsLocalColorScheme.current.focusStateContent
    } else {
        MaterialTheme.colorScheme.onBackground
    }
}
