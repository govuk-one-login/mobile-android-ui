package uk.gov.android.ui.patterns.centrealigned

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import kotlinx.collections.immutable.ImmutableList
import uk.gov.android.ui.componentsv2.bulletedlist.GdsBulletedList
import uk.gov.android.ui.componentsv2.button.ButtonType
import uk.gov.android.ui.componentsv2.button.GdsButton
import uk.gov.android.ui.theme.m3.GdsTheme
import uk.gov.android.ui.theme.m3.Typography
import uk.gov.android.ui.theme.spacingSingle

@Composable
fun GdsCentreAlignedScreen(
    title: String,
    modifier: Modifier = Modifier,
    image: CentreAlignedScreenImage? = null,
    body: ImmutableList<CentreAlignedScreenBodyContent>? = null,
    supportingText: String? = null,
    primaryButton: CentreAlignedScreenButton.Primary? = null,
    secondaryButton: CentreAlignedScreenButton.Secondary? = null,
) {
    Column(
        modifier = modifier
            .padding(horizontal = 16.dp)
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
    ) {
        MainContent(
            title,
            Modifier.weight(1f),
            image,
            body,
        )

        BottomContent(
            modifier = Modifier.fillMaxWidth(),
            supportingText,
            primaryButton,
            secondaryButton,
        )
    }
}

@Composable
private fun MainContent(
    title: String,
    modifier: Modifier = Modifier,
    image: CentreAlignedScreenImage? = null,
    body: ImmutableList<CentreAlignedScreenBodyContent>? = null,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
    ) {
        image?.let {
            Image(
                painter = painterResource(it.image),
                contentDescription = it.description,
            )
        }

        Spacer(modifier = Modifier.height(spacingSingle))

        Text(
            text = title,
            color = MaterialTheme.colorScheme.onBackground,
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.align(Alignment.CenterHorizontally),
        )

        Spacer(modifier = Modifier.height(spacingSingle))

        body?.let {
            BodyContent(
                it,
            )
        }
    }
}

@Composable
private fun BodyContent(
    body: ImmutableList<CentreAlignedScreenBodyContent>,
) {
    body.forEach {
        when (it) {
            is CentreAlignedScreenBodyContent.Text -> {
                Text(
                    text = it.bodyText,
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onBackground,
                    textAlign = TextAlign.Center,
                )
            }

            is CentreAlignedScreenBodyContent.BulletList -> {
                GdsBulletedList(
                    bulletListItems = it.items,
                    title = it.title,
                )
            }
        }
        Spacer(modifier = Modifier.height(spacingSingle))
    }
}

@Composable
private fun BottomContent(
    modifier: Modifier = Modifier,
    supportingText: String? = null,
    primaryButton: CentreAlignedScreenButton.Primary? = null,
    secondaryButton: CentreAlignedScreenButton.Secondary? = null,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom,
        modifier = modifier,
    ) {
        Spacer(
            modifier = Modifier.height(spacingSingle),
        )

        supportingText?.let {
            Text(
                text = it,
                style = Typography.bodySmall,
                color = MaterialTheme.colorScheme.onBackground,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(),
            )
        }

        Spacer(modifier = Modifier.height(spacingSingle))

        primaryButton?.let {
            GdsButton(
                text = it.text,
                buttonType = ButtonType.Primary,
                onClick = it.onClick,
                modifier = Modifier.fillMaxWidth(),
            )
        }

        Spacer(modifier = Modifier.height(spacingSingle))

        secondaryButton?.let {
            GdsButton(
                text = it.text,
                buttonType = ButtonType.Secondary,
                onClick = it.onClick,
                modifier = Modifier.fillMaxWidth(),
            )
        }

        Spacer(modifier = Modifier.height(spacingSingle))
    }
}

@PreviewLightDark
@Preview(showBackground = true)
@Composable
internal fun PreviewGdsCentreAlignedScreen(
    @PreviewParameter(CentreAlignedScreenContentProvider::class)
    content: CentreAlignedScreenContent,
) {
    GdsTheme {
        GdsCentreAlignedScreen(
            title = content.title,
            image = content.image,
            body = content.body,
            supportingText = content.supportingText,
            primaryButton = content.primaryButton,
            secondaryButton = content.secondaryButton,
        )
    }
}
