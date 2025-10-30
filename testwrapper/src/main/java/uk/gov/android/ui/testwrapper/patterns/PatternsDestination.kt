package uk.gov.android.ui.testwrapper.patterns

import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import kotlinx.serialization.Serializable
import uk.gov.android.ui.testwrapper.Placeholder
import uk.gov.android.ui.theme.smallPadding

@Serializable
sealed class PatternsDestination(
    val label: String,
) {

    @Serializable
    data class Placeholder(
        val text: String,
    ) : PatternsDestination(
        label = text,
    )

    companion object {
        fun NavGraphBuilder.applyPatternDestinations(
            modifier: Modifier = Modifier,
        ) {
            composable<Placeholder> { navBackStackEntry ->
                val arguments: Placeholder = navBackStackEntry.toRoute()
                Placeholder(
                    label = arguments.label,
                    modifier = modifier.padding(smallPadding)
                )
            }
        }

        fun entries() = listOf(
            Placeholder(text = "Center Aligned Screen"),
            Placeholder(text = "Dialog"),
            Placeholder(text = "Error Screen"),
            Placeholder(text = "Left Aligned Screen"),
            Placeholder(text = "Loading Screen"),
            // Add new demo items here
        ).sortedBy(PatternsDestination::label)
    }
}

