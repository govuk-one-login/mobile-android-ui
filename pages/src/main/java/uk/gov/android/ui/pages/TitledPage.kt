package uk.gov.android.ui.pages

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import uk.gov.android.ui.components.GdsHeading
import uk.gov.android.ui.components.HeadingParameters
import uk.gov.android.ui.components.HeadingSize
import uk.gov.android.ui.theme.GdsTheme
import uk.gov.ui.components.appbar.GdsTopAppBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TitledPage(parameters: TitledPageParameters) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    parameters.apply {
        GdsTheme {
            Scaffold(
                modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
                topBar = {
                    GdsTopAppBar(
                        title = {
                            GdsHeading(
                                headingParameters = HeadingParameters(
                                    size = HeadingSize.H2(),
                                    text = titleId,
                                    textAlign = TextAlign.Center,
                                    backgroundColor = MaterialTheme.colorScheme.background,
                                    padding = PaddingValues(vertical = 18.dp)
                                )
                            )
                        },
                        colors = {
                            TopAppBarDefaults.centerAlignedTopAppBarColors(
                                containerColor = MaterialTheme.colorScheme.background,
                                scrolledContainerColor = MaterialTheme.colorScheme.background
                            )
                        },
                        scrollBehavior = scrollBehavior
                    ).generate()
                },
            ) { paddingValues ->
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                        .verticalScroll(rememberScrollState())
                ) {
                    content()
                }
            }
        }

    }
}

data class TitledPageParameters(val titleId: Int, val content: @Composable () -> Unit = {})

class TitledPageParametersProvider : PreviewParameterProvider<TitledPageParameters> {
    override val values: Sequence<TitledPageParameters> = sequenceOf(
        TitledPageParameters(titleId = R.string.preview__titledPage__title)
    )
}

@Preview(
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Preview(
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun Preview(
    @PreviewParameter(TitledPageParametersProvider::class)
    parameters: TitledPageParameters
) {
    TitledPage(parameters = parameters)
}
