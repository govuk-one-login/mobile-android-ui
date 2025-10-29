package uk.gov.android.ui.testwrapper.patterns

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import uk.gov.android.ui.componentsv2.heading.GdsHeading
import uk.gov.android.ui.componentsv2.heading.GdsHeadingAlignment
import uk.gov.android.ui.componentsv2.heading.GdsHeadingStyle
import uk.gov.android.ui.theme.smallPadding
import uk.gov.android.ui.theme.util.UnstableDesignSystemAPI

enum class PatternsDestination(
    val route: String,
    val label: String
) {
    CENTER_ALIGNED("centrealignedscreen", "Center Aligned Screen"),
    DIALOG("dialog", "Dialog"),
    ERROR("errorscreen", "Error Screen"),
    LEFT_ALIGNED("leftalignedscreen", "Left Aligned Screen"),
    LOADING("loadingscreen", "Loading Screen"),
}

@OptIn(UnstableDesignSystemAPI::class)
@Composable
fun Patterns(
    modifier: Modifier = Modifier,
    navHostController: NavHostController
) {
    LazyColumn(
        modifier = modifier.fillMaxSize()
    ) {
        items(PatternsDestination.entries) { destination ->
            GdsHeading(
                text = destination.label,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable(onClick = {
                        navHostController.navigate(destination.route)
                    })
                    .padding(smallPadding),
                textAlign = GdsHeadingAlignment.LeftAligned,
                style = GdsHeadingStyle.Title3
            )
            HorizontalDivider(color = Color.Black)
        }
    }
}

// Add new demo items here

// Add new demo composables here
