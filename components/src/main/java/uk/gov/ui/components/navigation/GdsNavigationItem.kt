package uk.gov.ui.components.navigation

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier

/**
 * Wrapper data class for the [NavigationBarItem] Composable.
 */
data class GdsNavigationItem(
    private val icon: @Composable () -> Unit,
    private val onClick: () -> Unit,
    private val selected: Boolean,
    private val modifier: Modifier = Modifier,
    private val alwaysShowLabel: Boolean = true,
    private val colors: @Composable () -> NavigationBarItemColors = {
        NavigationBarItemDefaults.colors()
    },
    private val enabled: Boolean = true,
    private val interactionSource: @Composable () -> MutableInteractionSource = {
        remember { MutableInteractionSource() }
    },
    private val label: @Composable (() -> Unit)? = null,
) {
    /**
     * Converts the [GdsNavigationItem] into a [NavigationBarItem] Composable.
     */
    val generate: @Composable RowScope.() -> Unit
        get() = {
            NavigationBarItem(
                alwaysShowLabel = alwaysShowLabel,
                colors = colors(),
                enabled = enabled,
                icon = icon,
                interactionSource = interactionSource(),
                label = label,
                modifier = modifier,
                onClick = onClick,
                selected = selected,
            )
        }
}
