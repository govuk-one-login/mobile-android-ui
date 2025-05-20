package uk.gov.android.ui.patterns.loadingscreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewParameter
import uk.gov.android.ui.patterns.R
import uk.gov.android.ui.theme.largePadding
import uk.gov.android.ui.theme.m3.GdsTheme
import uk.gov.android.ui.theme.util.UnstableDesignSystemAPI

/**
 * Loading screen
 *
 * A composable that displays a loading indicator [CircularProgressIndicator] and a text message.
 * This is typically used to indicate that a background operation is in progress.
 *
 * @param text The message to display (e.g., "Loading").
 * @param modifier Modifier applied to the root container of the loading screen.
 *
 */
@UnstableDesignSystemAPI
@Composable
fun LoadingScreen(
    modifier: Modifier = Modifier,
    text: String = stringResource(R.string.loading),
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        CircularProgressIndicator()
        Text(
            text = text,
            color = MaterialTheme.colorScheme.onBackground,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(top = largePadding),
            textAlign = TextAlign.Center,
        )
    }
}

@OptIn(UnstableDesignSystemAPI::class)
@PreviewLightDark
@Composable
private fun PreviewDefaultLoadingScreen(
    @PreviewParameter(LoadingScreenContentProvider::class)
    content: String,
) {
    GdsTheme {
        LoadingScreen(text = content)
    }
}

@OptIn(UnstableDesignSystemAPI::class)
@PreviewLightDark
@Composable
private fun PreviewDefaultLoadingScreenNoContent() {
    GdsTheme {
        LoadingScreen()
    }
}
