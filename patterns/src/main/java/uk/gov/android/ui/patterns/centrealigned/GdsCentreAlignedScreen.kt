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
import uk.gov.android.ui.theme.spacingDouble

/**
 * Renders a centre-aligned screen with a structured layout.
 *
 * This screen is designed for displaying an image, title, body content, supporting text,
 * and primary/secondary buttons in a visually consistent manner.
 *
 * @param title The main title displayed at the top of the screen.
 * @param modifier A [Modifier] to be applied to the root layout of the screen (optional).
 * @param image image displayed at the top of the screen (optional).
 * @param body list of [CentreAlignedScreenBodyContent] representing the main content (optional).
 * @param supportingText additional text displayed below in the bottom content (optional).
 * @param primaryButton primary action button (optional).
 * @param secondaryButton secondary action button (optional).
 */
@Composable
fun GdsCentreAlignedScreen(
    title: String,
    modifier: Modifier = Modifier,
    image: CentreAlignedScreenImage? = null,
    body: ImmutableList<CentreAlignedScreenBodyContent>? = null,
    supportingText: String? = null,
    primaryButton: CentreAlignedScreenButton? = null,
    secondaryButton: CentreAlignedScreenButton? = null,
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

        Spacer(modifier = Modifier.height(spacingDouble))

        Text(
            text = title,
            color = MaterialTheme.colorScheme.onBackground,
            style = Typography.headlineLarge,
            modifier = Modifier.align(Alignment.CenterHorizontally),
        )

        Spacer(modifier = Modifier.height(spacingDouble))

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
                    style = Typography.bodyLarge,
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
        Spacer(modifier = Modifier.height(spacingDouble))
    }
}

@Composable
private fun BottomContent(
    modifier: Modifier = Modifier,
    supportingText: String? = null,
    primaryButton: CentreAlignedScreenButton? = null,
    secondaryButton: CentreAlignedScreenButton? = null,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom,
        modifier = modifier,
    ) {
        Spacer(
            modifier = Modifier.height(spacingDouble),
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

        Spacer(modifier = Modifier.height(spacingDouble))

        primaryButton?.let {
            GdsButton(
                text = it.text,
                buttonType = ButtonType.Primary,
                onClick = it.onClick,
                modifier = Modifier.fillMaxWidth(),
            )
        }

        Spacer(modifier = Modifier.height(spacingDouble))

        secondaryButton?.let {
            GdsButton(
                text = it.text,
                buttonType = ButtonType.Secondary,
                onClick = it.onClick,
                modifier = Modifier.fillMaxWidth(),
            )
        }

        Spacer(modifier = Modifier.height(spacingDouble))
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
