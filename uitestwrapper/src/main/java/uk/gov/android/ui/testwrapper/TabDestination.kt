package uk.gov.android.ui.testwrapper

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable
import uk.gov.android.ui.testwrapper.componentsv2.Components
import uk.gov.android.ui.testwrapper.patterns.Patterns
import uk.gov.android.ui.testwrapper.theme.Theme

@Serializable
sealed class TabDestination(
    val label: String,
) {
    @Serializable
    data object Components : TabDestination(
        label = "Components (v2)",
    )

    @Serializable
    data object Patterns : TabDestination(
        label = "Patterns",
    )

    @Serializable
    data object Theme : TabDestination(
        label = "Theme",
    )

    companion object {
        @JvmStatic
        fun entries() =
            listOf(
                TabDestination.Components,
                TabDestination.Patterns,
                TabDestination.Theme,
            ).sortedBy(TabDestination::label)

        fun NavGraphBuilder.applyTabDestinations(
            modifier: Modifier = Modifier,
            onNavigate: (Any) -> Unit = {},
        ) {
            composable<TabDestination.Components> {
                Components(modifier, onNavigate)
            }
            composable<TabDestination.Patterns> {
                Patterns(modifier, onNavigate)
            }
            composable<TabDestination.Theme> {
                Theme(modifier, onNavigate)
            }
        }
    }
}
