package uk.gov.android.ui.testwrapper.componentsv2

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import kotlinx.serialization.Serializable
import uk.gov.android.ui.testwrapper.ComponentListDetail
import uk.gov.android.ui.testwrapper.DetailItem
import uk.gov.android.ui.testwrapper.Placeholder
import uk.gov.android.ui.testwrapper.componentsv2.camera.qr.QrScannerResult
import uk.gov.android.ui.testwrapper.navigation.navTypeOf
import uk.gov.android.ui.theme.smallPadding
import uk.gov.android.ui.theme.spacingDouble
import kotlin.reflect.typeOf

@Serializable
sealed class ComponentsDestination(
    open val label: String,
) {

    @Serializable
    data class DetailedItem(
        val text: String,
        val items: List<DetailItem>,
    ) : ComponentsDestination(
        label = text,
    ) {
        companion object {
            /**
             * Mappings for serializing the [DetailedItem] properties. Specifically, this would be
             * the [DetailedItem.items] [List] property.
             */
            val typeMap = mapOf(
                typeOf<List<DetailItem>>() to navTypeOf<List<DetailItem>>(),
            )
        }
    }

    @Serializable
    data class Placeholder(
        val text: String
    ) : ComponentsDestination(label = text)

    companion object {
        fun NavGraphBuilder.applyComponentDestinations(
            modifier: Modifier = Modifier,
            onNavigate: (Any) -> Unit = {},
        ) {
            composable<Placeholder> { navBackStackEntry ->
                val arguments: Placeholder = navBackStackEntry.toRoute()
                Placeholder(
                    label = arguments.label, modifier = modifier.padding(smallPadding)
                )
            }
            composable<QrScannerResult> { navBackStackEntry ->
                val arguments: QrScannerResult = navBackStackEntry.toRoute()

                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = modifier.fillMaxSize(),
                ) {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(spacingDouble)
                    ) {
                        Text("URL obtained from barcode:")
                        Text(arguments.url)
                    }
                }
            }
            composable<DetailedItem>(typeMap = DetailedItem.typeMap) { navBackStackEntry ->
                val arguments: DetailedItem = navBackStackEntry.toRoute()
                ComponentListDetail(
                    items = arguments.items,
                    modifier = modifier,
                    onNavigate = onNavigate,
                )
            }
        }

        @JvmStatic
        fun entries() = listOf(
            DetailedItem(
                text = "Camera",
                items = listOf(
                    DetailItem(label = CAMERA_CONTENT, name = "Camera Content: Demo"),
                    DetailItem(
                        label = QR_CODE_CENTRALISED_SCANNING,
                        name = QR_CODE_CENTRALISED_SCANNING
                    ),
                    DetailItem(label = QR_CODE_SCANNING, name = QR_CODE_SCANNING),
                ).sortedBy(DetailItem::label)
            ),
            DetailedItem(
                text = "Dialogue",
                items = listOf(
                    DetailItem(label = DIALOGUE, name = "Dialogue")

                )
            ),
            DetailedItem(
                text = "Inputs",
                items = listOf(
                    DetailItem(label = RADIO, name = "Radio")

                )
            ),
            DetailedItem(
                text = "List",
                items = listOf(
                    DetailItem(label = NUMBERED_LIST, name = "Numbered List"),
                    DetailItem(label = BULLETED_LIST, name = "Bulleted List"),
                )

            ),
            Placeholder(text = "Button"),
            Placeholder(text = "Card"),
            Placeholder(text = "Heading"),
            Placeholder(text = "Images"),
            Placeholder(text = "Menu"),
            Placeholder(text = "Supporting Text"),
            Placeholder(text = "Text"),
            Placeholder(text = "Warning"),
            DetailedItem(
                text = "Status",
                items = listOf(
                    DetailItem(label = STATUS_OVERLAY, name = "Status Overlay")
                )

            ),
            DetailedItem(
                text = "Top App Bar",
                items = listOf(
                    DetailItem(label = TOP_APP_BAR, name = "Top App Bar")
                )

            ),
        ).sortedBy(ComponentsDestination::label)
    }
}