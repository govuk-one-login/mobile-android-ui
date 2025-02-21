package uk.gov.android.ui.patterns.centrealignedscreen

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
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
import androidx.compose.ui.layout.SubcomposeLayout
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
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

private const val ONE_THIRD = 1f / 3f

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
@Suppress("LongMethod")
@Composable
fun CentreAlignedScreen(
    title: String,
    modifier: Modifier = Modifier,
    image: CentreAlignedScreenImage? = null,
    body: ImmutableList<CentreAlignedScreenBodyContent>? = null,
    supportingText: String? = null,
    primaryButton: CentreAlignedScreenButton? = null,
    secondaryButton: CentreAlignedScreenButton? = null,
) {
    val screenHeight = LocalConfiguration.current.screenHeightDp.dp
    val thresholdHeight = screenHeight * ONE_THIRD
    val density = LocalDensity.current

    Column(modifier.padding(horizontal = 16.dp)) {
        SubcomposeLayout { constraints ->
            // Measure BottomContent
            val bottomPlaceables = subcompose("bottom") {
                BottomContent(
                    modifier = Modifier.fillMaxWidth(),
                    primaryButton = primaryButton,
                    secondaryButton = secondaryButton,
                )
            }.map { it.measure(constraints) }
            val bottomContentHeight = bottomPlaceables.maxOfOrNull { it.height } ?: 0

            // Measure SupportingText
            val supportingTextPlaceables = subcompose("supportingText") {
                SupportingText(supportingText)
            }.map { it.measure(constraints) }
            val supportingTextHeight = supportingTextPlaceables.maxOfOrNull { it.height } ?: 0

            // Check if BottomContent + Supporting text exceeds 1/3 of the screen threshold
            val totalBottomContentHeight = bottomContentHeight + supportingTextHeight
            val bottomContentOverThreshold =
                with(density) { totalBottomContentHeight.toDp() } > thresholdHeight

            // Measure MainContent. Add SupportingText to MainContent if above threshold
            val mainPlaceables = subcompose("main") {
                MainContent(
                    title,
                    Modifier,
                    image,
                    body,
                    if (bottomContentOverThreshold) {
                        { SupportingText(text = supportingText) }
                    } else {
                        null
                    },
                )
            }.map {
                val bottomContentSupportingTextHeight =
                    if (!bottomContentOverThreshold) supportingTextHeight else 0
                it.measure(
                    constraints.copy(
                        maxHeight = constraints.maxHeight - bottomContentHeight - bottomContentSupportingTextHeight,
                    ),
                )
            }

            layout(constraints.maxWidth, constraints.maxHeight) {
                var yPosition = 0

                mainPlaceables.forEach {
                    it.placeRelative(0, yPosition)
                    yPosition += it.height
                }

                if (!bottomContentOverThreshold) {
                    supportingTextPlaceables.forEach {
                        it.placeRelative(
                            0,
                            constraints.maxHeight - bottomContentHeight - supportingTextHeight,
                        )
                    }
                }

                bottomPlaceables.forEach {
                    it.placeRelative(0, constraints.maxHeight - bottomContentHeight)
                }
            }
        }
    }
}

@Composable
private fun MainContent(
    title: String,
    modifier: Modifier = Modifier,
    image: CentreAlignedScreenImage? = null,
    body: ImmutableList<CentreAlignedScreenBodyContent>? = null,
    @SuppressLint("ComposableLambdaParameterNaming")
    supportingText: @Composable (() -> Unit)? = null,
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

            Spacer(modifier = Modifier.height(spacingDouble))
        }

        Text(
            text = title,
            color = MaterialTheme.colorScheme.onBackground,
            style = Typography.displaySmall,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
        )

        body?.let {
            Spacer(modifier = Modifier.height(spacingDouble))

            BodyContent(it)
        }

        supportingText?.invoke()
    }
}

@Composable
private fun BodyContent(
    body: ImmutableList<CentreAlignedScreenBodyContent>,
) {
    body.forEachIndexed { i, item ->
        when (item) {
            is CentreAlignedScreenBodyContent.Text -> {
                Text(
                    text = item.bodyText,
                    style = Typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onBackground,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth(),
                )
            }

            is CentreAlignedScreenBodyContent.BulletList -> {
                Column(modifier = Modifier.fillMaxWidth()) {
                    GdsBulletedList(
                        bulletListItems = item.items,
                        title = item.title,
                    )
                }
            }
        }

        if (i < body.lastIndex) {
            Spacer(modifier = Modifier.height(spacingDouble))
        }
    }
}

@Composable
private fun SupportingText(
    text: String?,
    modifier: Modifier = Modifier,
) {
    text?.let {
        Text(
            text = text,
            style = Typography.labelMedium,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = modifier
                .fillMaxWidth()
                .padding(top = spacingDouble),
            textAlign = TextAlign.Center,
        )
    }
}

@Composable
private fun BottomContent(
    modifier: Modifier = Modifier,
    primaryButton: CentreAlignedScreenButton? = null,
    secondaryButton: CentreAlignedScreenButton? = null,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom,
        modifier = modifier,
    ) {
        primaryButton?.let {
            val bottomPadding = if (secondaryButton == null) spacingDouble else 0.dp

            Spacer(modifier = Modifier.height(spacingDouble))

            GdsButton(
                text = it.text,
                buttonType = ButtonType.Primary,
                onClick = it.onClick,
                modifier = Modifier.fillMaxWidth(),
            )

            Spacer(modifier = Modifier.height(bottomPadding))
        }

        secondaryButton?.let {
            val topPadding = if (primaryButton == null) 0.dp else spacingDouble

            Spacer(modifier = Modifier.height(topPadding))

            GdsButton(
                text = it.text,
                buttonType = ButtonType.Secondary,
                onClick = it.onClick,
                modifier = Modifier.fillMaxWidth(),
            )

            Spacer(modifier = Modifier.height(spacingDouble))
        }
    }
}

@PreviewLightDark
@Preview(showBackground = true)
@Composable
internal fun PreviewCentreAlignedScreen(
    @PreviewParameter(CentreAlignedScreenContentProvider::class)
    content: CentreAlignedScreenContent,
) {
    GdsTheme {
        CentreAlignedScreen(
            title = content.title,
            image = content.image,
            body = content.body,
            supportingText = content.supportingText,
            primaryButton = content.primaryButton,
            secondaryButton = content.secondaryButton,
        )
    }
}

@PreviewLightDark
@Preview(showBackground = true, fontScale = 2f)
@Composable
internal fun PreviewCentreAlignedScreenAccessibility() {
    val content = CentreAlignedScreenContentProvider().values.elementAt(1)
    GdsTheme {
        CentreAlignedScreen(
            title = content.title,
            image = content.image,
            body = content.body,
            supportingText = content.supportingText,
            primaryButton = content.primaryButton,
            secondaryButton = content.secondaryButton,
        )
    }
}
