package uk.gov.android.ui.testwrapper.componentsv2.card

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import uk.gov.android.ui.componentsv2.GdsCard
import uk.gov.android.ui.componentsv2.R
import uk.gov.android.ui.testwrapper.DemoTemplate

@Composable
fun GdsCardDemo() {
    DemoTemplate {
        GdsCard(
            title = "Card",
            onClick = {},
            image = painterResource(R.drawable.ic_tile_image),
            contentDescription = "Tile image",
            body = "Instance of GdsCard with button",
            displayPrimary = true,
            buttonText = "Continue",
        )
    }
}
