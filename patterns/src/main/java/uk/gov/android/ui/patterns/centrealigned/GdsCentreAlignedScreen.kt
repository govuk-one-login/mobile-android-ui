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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
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
    image: ImageResource? = null,
    body: Body? = null,
    supportingText: String? = null,
    primaryButtonText: String? = null,
    secondaryButtonText: String? = null,
) {
    Column(
        modifier = modifier
            .padding(
                horizontal = 16.dp,
            )
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
    ) {
        MainContent(
            title = title,
            image = image,
            body = body,
            modifier = Modifier.weight(1f),
        )

        BottomContent(
            supportingText = supportingText,
            primaryButtonText = primaryButtonText,
            secondaryButtonText = secondaryButtonText,
        )
    }
}

@Composable
private fun MainContent(
    title: String,
    modifier: Modifier = Modifier,
    image: ImageResource? = null,
    body: Body? = null,
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
                contentDescription = stringResource(it.description),
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
                modifier = Modifier.align(Alignment.CenterHorizontally),
            )
        }
    }
}

@Composable
private fun BodyContent(
    body: Body,
    modifier: Modifier = Modifier,
) {
    body.bodyContentList.forEach {
        when (it) {
            is BodyContent.Text -> {
                Text(
                    text = it.bodyText,
                    modifier = modifier,
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onBackground,
                    textAlign = TextAlign.Center,
                )
            }

            is BodyContent.BulletList -> {
                GdsBulletedList(
                    bulletListItems = it.items,
                    title = it.title,
                    modifier = modifier,
                )
            }
        }
        Spacer(modifier = Modifier.height(spacingSingle))
    }
}

@Composable
private fun BottomContent(
    supportingText: String? = null,
    primaryButtonText: String? = null,
    secondaryButtonText: String? = null,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom,
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

        primaryButtonText?.let {
            GdsButton(
                text = it,
                buttonType = ButtonType.Primary,
                onClick = {},
                modifier = Modifier.fillMaxWidth(),
            )
        }

        Spacer(modifier = Modifier.height(spacingSingle))

        secondaryButtonText?.let {
            GdsButton(
                buttonType = ButtonType.Secondary,
                onClick = {},
                text = it,
                modifier = Modifier.fillMaxWidth(),
            )
        }

        Spacer(modifier = Modifier.height(spacingSingle))
    }
}

@PreviewLightDark
@Composable
@Preview
private fun PreviewGdsCentreAlignedScreen() {
    val firstContent = ContentProvider().values.first()
    GdsTheme {
        GdsCentreAlignedScreen(
            title = firstContent.title,
            image = firstContent.image,
            body = firstContent.body,
            supportingText = firstContent.supportingText,
            primaryButtonText = firstContent.primaryButtonText,
            secondaryButtonText = firstContent.secondaryButtonText,
        )
    }
}
