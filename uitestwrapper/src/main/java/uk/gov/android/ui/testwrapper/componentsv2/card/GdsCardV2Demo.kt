package uk.gov.android.ui.testwrapper.componentsv2.card

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.semantics
import uk.gov.android.ui.componentsv2.R
import uk.gov.android.ui.componentsv2.button.ButtonTypeV2
import uk.gov.android.ui.componentsv2.button.GdsButton
import uk.gov.android.ui.componentsv2.card.v2.GdsCard
import uk.gov.android.ui.componentsv2.card.v2.GdsCardDefaults
import uk.gov.android.ui.componentsv2.card.v2.gdsCardShadow
import uk.gov.android.ui.testwrapper.DemoTemplate
import uk.gov.android.ui.theme.m3.Typography
import uk.gov.android.ui.theme.spacingDouble
import uk.gov.android.ui.theme.spacingSingle

/**
 * Demonstrates the GDS Card v2 defaults and the Material 3 capabilities it inherits.
 *
 * The card applies no padding of its own, so every example provides its own content spacing.
 */
@Composable
@Suppress("LongMethod")
fun GdsCardV2Demo(modifier: Modifier = Modifier) {
    DemoTemplate {
        Column(
            modifier = modifier.verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(spacingDouble),
        ) {
            // Defaults: card background, 16.dp corner radius, no border, no shadow.
            GdsCard {
                CardText(
                    title = "Default",
                    body = "Card background, 16.dp corners, no border and no shadow.",
                )
            }

            GdsCard(border = GdsCardDefaults.border()) {
                CardText(
                    title = "With border",
                    body = "1.dp Grey 3 border, applied with GdsCardDefaults.border().",
                )
            }

            GdsCard(modifier = Modifier.gdsCardShadow()) {
                CardText(
                    title = "With shadow",
                    body = "1.dp shadow, applied with Modifier.gdsCardShadow().",
                )
            }

            TappableCard()

            // Horizontal layout, an icon, and heading semantics on the title.
            GdsCard {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(spacingDouble),
                    horizontalArrangement = Arrangement.spacedBy(spacingDouble),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_error_filled),
                        contentDescription = null,
                    )
                    Column(verticalArrangement = Arrangement.spacedBy(spacingSingle)) {
                        Text(
                            text = "Horizontal layout",
                            style = Typography.titleMedium,
                            modifier = Modifier.semantics { heading() },
                        )
                        Text(
                            text = "An icon and text laid out in a Row inside the content slot.",
                            style = Typography.bodyLarge,
                        )
                    }
                }
            }

            // Edge-to-edge header image, clipped by the card's corner radius, plus a button.
            GdsCard {
                Image(
                    painter = painterResource(R.drawable.ic_tile_image),
                    contentDescription = "Tile image",
                    contentScale = ContentScale.FillWidth,
                    modifier = Modifier.fillMaxWidth(),
                )
                CardText(
                    title = "Edge-to-edge header",
                    body = "An image, text and a button composed in the content slot.",
                )
                GdsButton(
                    text = "Continue",
                    buttonType = ButtonTypeV2.Primary(),
                    onClick = {},
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            start = spacingDouble,
                            end = spacingDouble,
                            bottom = spacingDouble,
                        ),
                )
            }
        }
    }
}

@Composable
private fun TappableCard() {
    var taps by remember { mutableIntStateOf(0) }

    GdsCard(onClick = { taps++ }) {
        CardText(
            title = "Tappable",
            body = "The whole card is one tap target. Tapped $taps times.",
        )
    }
}

@Composable
private fun CardText(title: String, body: String, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(spacingDouble),
        verticalArrangement = Arrangement.spacedBy(spacingSingle),
    ) {
        Text(
            text = title,
            style = Typography.titleMedium,
            modifier = Modifier.semantics { heading() },
        )
        Text(text = body, style = Typography.bodyLarge)
    }
}
