package uk.gov.android.ui.patterns.errorscreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewParameter
import uk.gov.android.ui.componentsv2.heading.GdsHeading
import uk.gov.android.ui.patterns.centrealignedscreen.CentreAlignedScreenSlotBased
import uk.gov.android.ui.theme.m3.GdsTheme
import uk.gov.android.ui.theme.spacingDouble
import uk.gov.android.ui.theme.util.UnstableDesignSystemAPI

@Composable
fun ErrorScreen(
    title: String,
    icon: ErrorScreenIcon,
    modifier: Modifier = Modifier,
) {
    CentreAlignedScreenSlotBased(
        modifier = modifier,
        mainContent = {
            MainContent(
                title,
                icon,
                Modifier,
            )
        },
    )
}

@Composable
internal fun MainContent(
    title: String,
    icon: ErrorScreenIcon,
    modifier: Modifier = Modifier,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
    ) {
        ErrorScreenHeader(title, icon)
    }
}

@OptIn(UnstableDesignSystemAPI::class)
@Composable
internal fun ErrorScreenHeader(
    title: String,
    icon: ErrorScreenIcon,
    modifier: Modifier = Modifier,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
            .semantics(mergeDescendants = true) {},
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(icon.icon),
            contentDescription = icon.description,
            modifier = Modifier.padding(horizontal = spacingDouble),
            tint = MaterialTheme.colorScheme.onBackground,
        )
        Spacer(modifier = Modifier.height(spacingDouble))
        GdsHeading(
            text = title,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = spacingDouble),
            textAlign = TextAlign.Center,
        )
    }
}

@PreviewLightDark
@Preview(showBackground = true)
@Composable
internal fun PreviewErrorScreen(
    @PreviewParameter(ErrorScreenContentProvider::class)
    content: ErrorScreenContent,
) {
    GdsTheme {
        ErrorScreen(
            title = content.title,
            icon = content.icon,
        )
    }
}
