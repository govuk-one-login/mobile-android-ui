package uk.gov.android.ui.testwrapper.patterns

import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import kotlinx.collections.immutable.toPersistentList
import kotlinx.serialization.Serializable
import uk.gov.android.ui.testwrapper.DetailItem
import uk.gov.android.ui.testwrapper.PatternListDetail
import uk.gov.android.ui.testwrapper.Placeholder
import uk.gov.android.ui.testwrapper.navigation.navTypeOf
import uk.gov.android.ui.theme.smallPadding
import kotlin.reflect.typeOf

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

    @Serializable
    data class DetailedItem(
        val text: String,
        val items: List<DetailItem>,
    ) : PatternsDestination(
        label = text,
    ) {

        companion object {
            /**
             * Mappings for serializing the [DetailedItem] properties. Specifically, this would be
             * the [DetailedItem.items] [List] property.
             */
            val typeMap =
                mapOf(
                    typeOf<List<DetailItem>>() to navTypeOf<ArrayList<DetailItem>>(),
                )
        }
    }

    companion object {
        fun NavGraphBuilder.applyPatternDestinations(
            modifier: Modifier = Modifier,
            displayTabRow: (Boolean) -> Unit,
        ) {
            composable<Placeholder> { navBackStackEntry ->
                val arguments: Placeholder = navBackStackEntry.toRoute()
                Placeholder(
                    label = arguments.label,
                    modifier = modifier.padding(smallPadding),
                )
            }
            composable<DetailedItem>(typeMap = DetailedItem.typeMap) { navBackStackEntry ->
                val arguments: DetailedItem = navBackStackEntry.toRoute()
                PatternListDetail(
                    items = arguments.items.toPersistentList(),
                    modifier = modifier,
                    displayTabRow = displayTabRow,
                )
            }
        }

        fun entries() =
            listOf(
                Placeholder(text = "Center Aligned Screen"),
                Placeholder(text = "Dialog"),
                DetailedItem(
                    text = "Error Screen",
                    items =
                    listOf(
                        DetailItem(label = ERROR_SCREEN, name = "Error Screen"),
                        DetailItem(label = ERROR_FULL_SCREEN, name = "Error Full Screen"),
                        DetailItem(label = ERROR_SCREEN, name = "Error Screen (Deprecated)"),
                        DetailItem(
                            label = ERROR_FULL_SCREEN,
                            name = "Error Full Screen (Deprecated)",
                        ),
                    ),
                ),
                Placeholder(text = "Left Aligned Screen"),
                DetailedItem(
                    text = "Loading Screen",
                    items =
                    listOf(
                        DetailItem(label = LOADING_SCREEN, name = "Loading Screen"),
                        DetailItem(label = LOADING_FULL_SCREEN, name = "Loading Full Screen"),
                        DetailItem(label = LOADING_DYNAMIC_SCREEN, name = "Loading Dynamic Screen"),
                        DetailItem(label = LOADING_DYNAMIC_FULL_SCREEN, name = "Loading Dynamic Full Screen"),
                    ),
                ),
                // Add new demo items here
            ).sortedBy(PatternsDestination::label)
    }
}
