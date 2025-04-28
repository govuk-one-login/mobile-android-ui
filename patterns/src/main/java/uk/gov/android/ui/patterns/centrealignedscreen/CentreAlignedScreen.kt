package uk.gov.android.ui.patterns.centrealignedscreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.SubcomposeLayout
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList
import uk.gov.android.ui.componentsv2.R
import uk.gov.android.ui.componentsv2.button.ButtonType
import uk.gov.android.ui.componentsv2.button.GdsButton
import uk.gov.android.ui.componentsv2.button.customButtonColors
import uk.gov.android.ui.componentsv2.heading.GdsHeading
import uk.gov.android.ui.componentsv2.supportingtext.GdsSupportingText
import uk.gov.android.ui.patterns.centrealignedscreen.CentreAlignedScreenDefaults.HorizontalPadding
import uk.gov.android.ui.patterns.centrealignedscreen.CentreAlignedScreenDefaults.NoPadding
import uk.gov.android.ui.patterns.centrealignedscreen.CentreAlignedScreenDefaults.VerticalPadding
import uk.gov.android.ui.patterns.centrealignedscreen.CentreAlignedScreenTestTag.BODY_LAZY_COLUMN_TEST_TAG
import uk.gov.android.ui.patterns.leftalignedscreen.toBodyContent
import uk.gov.android.ui.theme.m3.GdsTheme
import uk.gov.android.ui.theme.m3.Typography
import uk.gov.android.ui.theme.m3_disabled
import uk.gov.android.ui.theme.m3_onDisabled
import uk.gov.android.ui.theme.meta.ExcludeFromJacocoGeneratedReport
import uk.gov.android.ui.theme.spacingDouble

private const val ONE_THIRD = 1f / 3f

/**
 * Renders a centre-aligned screen with a structured layout.
 *
 * This screen is designed for displaying an image, title, body content, supporting text,
 * and bottom content with primary/secondary buttons in a visually consistent manner.
 *
 * @param title represents the main title. Use of [GdsHeading] is recommended
 * @param modifier A [Modifier] to be applied to the root layout of the screen (optional).
 * @param image image displayed at the top of the screen (optional).
 * @sample LazyListScope.toBodyContent
 * @param body representing the main content.
 * @param supportingText additional text displayed below in the bottom content. Use of [GdsSupportingText] composable is recommended (optional).
 * @param primaryButton primary action button. Use of [GdsButton] composable is recommended (optional).
 * @param secondaryButton primary action button. Use of [GdsButton] composable is recommended (optional).
 * @param tertiaryButton primary action button. Use of [GdsButton] composable is recommended (optional).
 */
@Suppress("LongMethod")
@SuppressWarnings("squid:S107")
@Composable
internal fun CentreAlignedScreen(
    title: @Composable (horizontalPadding: Dp) -> Unit,
    modifier: Modifier = Modifier,
    image: @Composable ((horizontalPadding: Dp) -> Unit)? = null,
    body: (LazyListScope.(horizontalItemPadding: Dp) -> Unit)? = null,
    supportingText: (@Composable (horizontalPadding: Dp, topPadding: Dp) -> Unit)? = null,
    primaryButton: (@Composable () -> Unit)? = null,
    secondaryButton: (@Composable () -> Unit)? = null,
    tertiaryButton: (@Composable () -> Unit)? = null,
) {
    val screenHeight = LocalConfiguration.current.screenHeightDp.dp
    val thresholdHeight = screenHeight * ONE_THIRD
    val density = LocalDensity.current

    Column(modifier) {
        SubcomposeLayout { constraints ->
            // Measure BottomContent
            val bottomPlaceables = subcompose("bottom") {
                BottomContent(
                    modifier = Modifier.fillMaxWidth(),
                    primaryButton = primaryButton,
                    secondaryButton = secondaryButton,
                    tertiaryButton = tertiaryButton,
                )
            }.map { it.measure(constraints) }
            val bottomContentHeight = bottomPlaceables.maxOfOrNull { it.height } ?: 0

            // Measure SupportingText
            val supportingTextPlaceables = subcompose("supportingText") {
                supportingText?.invoke(HorizontalPadding, VerticalPadding)
            }.map { it.measure(constraints) }
            val supportingTextHeight = supportingTextPlaceables.maxOfOrNull { it.height } ?: 0

            // Check if BottomContent + Supporting text exceeds 1/3 of the screen threshold
            val totalBottomContentHeight = bottomContentHeight + supportingTextHeight
            val bottomContentOverThreshold =
                with(density) { totalBottomContentHeight.toDp() } > thresholdHeight

            // Measure MainContent. Add SupportingText to MainContent if above threshold
            val mainPlaceables = subcompose("main") {
                MainContent(
                    title = title,
                    modifier = Modifier,
                    image = image,
                    body = body,
                    supportingText = if (bottomContentOverThreshold) {
                        supportingText
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
    CentreAlignedScreen(
        title = { horizontalPadding ->
            Text(
                text = title,
                color = MaterialTheme.colorScheme.onBackground,
                style = Typography.displaySmall,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = horizontalPadding),
                textAlign = TextAlign.Center,
            )
        },
        image = image?.let {
            { horizontalPadding ->
                Image(
                    painter = painterResource(it.image),
                    contentDescription = it.description,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = horizontalPadding),
                )
            }
        },
        body = body?.let {
            { horizontalPadding ->
                toBodyContent(body = body, horizontalItemPadding = horizontalPadding)
            }
        },
        modifier = modifier,
        supportingText = supportingText?.let {
            { horizontalPadding, verticalPadding ->
                SupportingText(
                    text = supportingText,
                    horizontalItemPadding = horizontalPadding,
                    verticalItemPadding = verticalPadding,
                )
            }
        },
        primaryButton = primaryButton?.let {
            {
                if (it.showIcon) {
                    GdsButton(
                        text = it.text,
                        buttonType = ButtonType.Icon(
                            buttonColors = ButtonDefaults.buttonColors(
                                containerColor = colorScheme.primary,
                                contentColor = colorScheme.onPrimary,
                                disabledContainerColor = m3_disabled,
                                disabledContentColor = m3_onDisabled,
                            ),
                            fontWeight = FontWeight.Bold,
                            iconImage = ImageVector.vectorResource(R.drawable.ic_external_site),
                            contentDescription = stringResource(R.string.opens_in_external_browser),
                        ),
                        onClick = it.onClick,
                        modifier = Modifier.fillMaxWidth(),
                        enabled = it.enabled,
                    )
                } else {
                    GdsButton(
                        text = it.text,
                        buttonType = ButtonType.Primary,
                        onClick = it.onClick,
                        modifier = Modifier.fillMaxWidth(),
                        enabled = it.enabled,
                    )
                }
            }
        },
        secondaryButton = secondaryButton?.let {
            {
                if (it.showIcon) {
                    GdsButton(
                        text = it.text,
                        buttonType = ButtonType.Icon(
                            buttonColors = customButtonColors(
                                contentColor = colorScheme.primary,
                                containerColor = colorScheme.background,
                            ),
                            iconImage = ImageVector.vectorResource(R.drawable.ic_external_site),
                            contentDescription = stringResource(R.string.opens_in_external_browser),
                        ),
                        onClick = it.onClick,
                        modifier = Modifier.fillMaxWidth(),
                        enabled = it.enabled,
                    )
                } else {
                    GdsButton(
                        text = it.text,
                        buttonType = ButtonType.Secondary,
                        onClick = it.onClick,
                        modifier = Modifier.fillMaxWidth(),
                        enabled = it.enabled,
                    )
                }
            }
        },
    )
}

@Composable
private fun MainContent(
    title: @Composable (horizontalPadding: Dp) -> Unit,
    modifier: Modifier = Modifier,
    image: @Composable ((horizontalPadding: Dp) -> Unit)? = null,
    body: (LazyListScope.(horizontalItemPadding: Dp) -> Unit)? = null,
    supportingText: (@Composable (horizontalPadding: Dp, topPadding: Dp) -> Unit)? = null,
    arrangement: Arrangement.Vertical =
        Arrangement.spacedBy(VerticalPadding, Alignment.CenterVertically),
) {
    LazyColumn(
        verticalArrangement = arrangement,
        modifier = modifier
            .fillMaxSize()
            .testTag(BODY_LAZY_COLUMN_TEST_TAG),
    ) {
        item {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .semantics(mergeDescendants = true) {},
            ) {
                image?.let {
                    image.invoke(HorizontalPadding)
                    Spacer(modifier = Modifier.height(spacingDouble))
                }

                title(HorizontalPadding)
            }
        }

        body?.let {
            it(HorizontalPadding)
        }

        supportingText?.let {
            item { it.invoke(HorizontalPadding, NoPadding) }
        }
    }
}

@Composable
private fun SupportingText(
    text: String?,
    horizontalItemPadding: Dp,
    verticalItemPadding: Dp,
    modifier: Modifier = Modifier,
) {
    text?.let {
        Text(
            text = text,
            style = Typography.labelMedium,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = modifier
                .fillMaxWidth()
                .padding(
                    top = verticalItemPadding,
                    start = horizontalItemPadding,
                    end = horizontalItemPadding,
                ),
            textAlign = TextAlign.Center,
        )
    }
}

@Composable
private fun BottomContent(
    modifier: Modifier = Modifier,
    primaryButton: (@Composable () -> Unit)? = null,
    secondaryButton: (@Composable () -> Unit)? = null,
    tertiaryButton: (@Composable () -> Unit)? = null,
) {
    val buttons = listOfNotNull(primaryButton, secondaryButton, tertiaryButton).toImmutableList()
    val verticalItemPadding = if (buttons.isEmpty()) NoPadding else VerticalPadding
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(spacingDouble, Alignment.Bottom),
        modifier = modifier.padding(horizontal = HorizontalPadding, vertical = verticalItemPadding),
    ) {
        buttons.forEach {
            it.invoke()
        }
    }
}

object CentreAlignedScreenDefaults {
    val HorizontalPadding: Dp = spacingDouble
    val VerticalPadding: Dp = spacingDouble
    val NoPadding: Dp = 0.dp
}

internal object CentreAlignedScreenTestTag {
    const val BODY_LAZY_COLUMN_TEST_TAG = "BODY_LAZY_COLUMN_TEST_TAG"
}

@ExcludeFromJacocoGeneratedReport
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

@ExcludeFromJacocoGeneratedReport
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
