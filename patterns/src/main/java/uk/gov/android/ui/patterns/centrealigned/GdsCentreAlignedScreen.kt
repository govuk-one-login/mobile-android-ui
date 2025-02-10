package uk.gov.android.ui.patterns.centrealignedscreen

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import uk.gov.android.ui.components.m3.GdsBulletList
import uk.gov.android.ui.components.m3.buttons.ButtonParameters
import uk.gov.android.ui.components.m3.buttons.ButtonType
import uk.gov.android.ui.components.m3.buttons.GdsButton
import uk.gov.android.ui.theme.m3.GdsTheme
import uk.gov.android.ui.theme.m3.Typography
import uk.gov.android.ui.theme.spacingSingle

@Composable
@Suppress("LongMethod")
fun GdsCentreAlignedScreen(content: Content) {
    Column(
        modifier = Modifier
            .padding(
                start = 16.dp,
                top = 0.dp,
                end = 16.dp,
                bottom = 0.dp,
            )
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .fillMaxSize()
                .weight(1f),
        ) {
            content.image?.let {
                Image(
                    painter = painterResource(it.image),
                    contentDescription = stringResource(it.description),
                )
            }

            Spacer(modifier = Modifier.padding(spacingSingle))

            Text(
                text = stringResource(content.title),
                color = MaterialTheme.colorScheme.onBackground,
                style = MaterialTheme.typography.headlineLarge,
                modifier = Modifier.align(Alignment.CenterHorizontally),
            )

            Spacer(modifier = Modifier.padding(spacingSingle))

            content.body?.bodyContentList?.forEach {
                when (it) {
                    is BodyContent.Text -> {
                        Text(
                            text = stringResource(it.bodyText),
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.onBackground,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.align(Alignment.CenterHorizontally),
                        )
                        Spacer(modifier = Modifier.padding(spacingSingle))
                    }

                    is BodyContent.BulletList -> {
                        GdsBulletList(it.bulletList)
                        Spacer(modifier = Modifier.padding(spacingSingle))
                    }
                }
            }
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom,
        ) {
            Spacer(modifier = Modifier.padding(spacingSingle))

            content.supportingText?.let {
                Text(
                    text = stringResource(it),
                    style = Typography.bodySmall,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                )
            }

            Spacer(modifier = Modifier.padding(spacingSingle))

            content.primaryButtonText?.let {
                GdsButton(
                    ButtonParameters(
                        modifier = Modifier
                            .fillMaxWidth(),
                        buttonType = ButtonType.PRIMARY(),
                        onClick = {},
                        text = stringResource(it),
                    ),
                )
            }

            Spacer(modifier = Modifier.padding(spacingSingle))

            content.secondaryButtonText?.let {
                GdsButton(
                    ButtonParameters(
                        modifier = Modifier
                            .fillMaxWidth(),
                        buttonType = ButtonType.SECONDARY(),
                        onClick = {},
                        text = stringResource(it),
                    ),
                )
            }

            Spacer(modifier = Modifier.padding(spacingSingle))
        }
    }
}

@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
)
@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Composable
private fun PreviewContent(
    @PreviewParameter(ContentProvider::class)
    content: Content,
) {
    GdsTheme {
        GdsCentreAlignedScreen(content)
    }
}
