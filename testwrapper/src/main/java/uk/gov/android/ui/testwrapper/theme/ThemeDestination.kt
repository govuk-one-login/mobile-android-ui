package uk.gov.android.ui.testwrapper.theme

import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import kotlinx.collections.immutable.toPersistentList
import kotlinx.serialization.Serializable
import uk.gov.android.ui.testwrapper.DetailItem
import uk.gov.android.ui.testwrapper.Placeholder
import uk.gov.android.ui.testwrapper.ThemeListDetail
import uk.gov.android.ui.testwrapper.navigation.navTypeOf
import uk.gov.android.ui.theme.smallPadding
import kotlin.reflect.typeOf

@Serializable
sealed class ThemeDestination(
    val label: String,
) {
    @Serializable
    data class Placeholder(
        val text: String,
    ) : ThemeDestination(
        label = text,
    )

    @Serializable
    data class DetailedItem(
        val text: String,
        val items: List<DetailItem>,
    ) : ThemeDestination(
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
        fun NavGraphBuilder.applyThemeDestinations(modifier: Modifier = Modifier) {
            composable<Placeholder> { navBackStackEntry ->
                val arguments: Placeholder = navBackStackEntry.toRoute()
                Placeholder(
                    label = arguments.label,
                    modifier = modifier.padding(smallPadding),
                )
            }
            composable<DetailedItem>(typeMap = DetailedItem.typeMap) { navBackStackEntry ->
                val arguments: DetailedItem = navBackStackEntry.toRoute()
                ThemeListDetail(
                    items = arguments.items.toPersistentList(),
                    modifier = modifier,
                )
            }
        }

        fun entries() =
            listOf(
                DetailedItem(
                    text = "Styles",
                    items =
                    listOf(
                        DetailItem(label = STYLES_SCREEN, name = "Current Styles"),
                    ),
                ),
                // Add new demo items here
            ).sortedBy(ThemeDestination::label)
    }
}
