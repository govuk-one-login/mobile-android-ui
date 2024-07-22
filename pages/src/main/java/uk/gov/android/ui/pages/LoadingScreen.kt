package uk.gov.android.ui.pages

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import uk.gov.android.ui.theme.m3.GdsTheme
import uk.gov.android.ui.theme.smallPadding

@Suppress("LongMethod")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoadingScreen(
    parameters: LoadingScreenParameters
) {
    parameters.apply {
        val progressIndicatorContentDesc = stringResource(
            id = R.string.loadingScreen__progressBar__contentDescription
        )

        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    title = {
                        Text(
                            text = "",
                            style = MaterialTheme.typography.headlineMedium,
                            color = MaterialTheme.colorScheme.onBackground
                        )
                    },
                    navigationIcon = {
                        if (displayNavIcon) {
                            IconButton(
                                onClick = onClickNavIcon
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Close,
                                    contentDescription = stringResource(
                                        id = R.string.loadingScreen__navIcon__contentDescription
                                    )
                                )
                            }
                        }
                    },
                    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                        containerColor = MaterialTheme.colorScheme.surface
                    )
                )
            },
            content = { paddingValues ->
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .padding(paddingValues)
                        .fillMaxSize()
                        .padding(start = smallPadding, end = smallPadding)
                ) {
                    Column {
                        CenteredRow {
                            CircularProgressIndicator(
                                modifier = Modifier
                                    .width(48.dp)
                                    .semantics {
                                        contentDescription = progressIndicatorContentDesc
                                    },
                                color = MaterialTheme.colorScheme.primary,
                                trackColor = MaterialTheme.colorScheme.surfaceVariant
                            )
                        }
                        CenteredRow {
                            Text(
                                text = stringResource(id = displayText)
                            )
                        }
                    }
                }
            }
        )
    }
}

@Composable
private fun CenteredRow(
    content: @Composable () -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                top = smallPadding,
                bottom = smallPadding
            )
    ) {
        content.invoke()
    }
}

data class LoadingScreenParameters(
    val displayText: Int = R.string.loadingScreen__default__text,
    var displayNavIcon: Boolean = true,
    val onClickNavIcon: () -> Unit = {}
)

class LoadingScreenParameterProvider : PreviewParameterProvider<LoadingScreenParameters> {
    override val values: Sequence<LoadingScreenParameters> = sequenceOf(
        LoadingScreenParameters(),
        LoadingScreenParameters(
            displayText = R.string.loadingScreen__text
        ),
        LoadingScreenParameters(
            displayNavIcon = false
        ),
        LoadingScreenParameters(
            displayText = R.string.loadingScreen__text,
            displayNavIcon = false
        )
    )
}

@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Composable
fun LoadingScreenPreview(
    @PreviewParameter(LoadingScreenParameterProvider::class)
    parameters: LoadingScreenParameters
) {
    GdsTheme {
        LoadingScreen(
            parameters = parameters
        )
    }
}
