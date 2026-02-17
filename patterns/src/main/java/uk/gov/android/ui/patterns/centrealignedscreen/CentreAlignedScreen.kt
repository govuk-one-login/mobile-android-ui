package uk.gov.android.ui.patterns.centrealignedscreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.SubcomposeLayout
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
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
import uk.gov.android.ui.componentsv2.button.buttonColors
import uk.gov.android.ui.componentsv2.button.customButtonColors
import uk.gov.android.ui.componentsv2.heading.GdsHeading
import uk.gov.android.ui.componentsv2.heading.GdsHeadingAlignment
import uk.gov.android.ui.componentsv2.supportingtext.GdsSupportingText
import uk.gov.android.ui.patterns.centrealignedscreen.CentreAlignedScreenDefaults.HorizontalPadding
import uk.gov.android.ui.patterns.centrealignedscreen.CentreAlignedScreenDefaults.NoPadding
import uk.gov.android.ui.patterns.centrealignedscreen.CentreAlignedScreenDefaults.VerticalPadding
import uk.gov.android.ui.patterns.centrealignedscreen.CentreAlignedScreenTestTag.BODY_LAZY_COLUMN_TEST_TAG
import uk.gov.android.ui.theme.m3.GdsTheme
import uk.gov.android.ui.theme.m3.Typography
import uk.gov.android.ui.theme.meta.ExcludeFromJacocoGeneratedReport
import uk.gov.android.ui.theme.spacingDouble

private const val ONE_THIRD = 1f / 3f

/**
 * Renders a centre-aligned screen with a structured layout.
 *
 * This screen is designed for displaying an image, title, body content,
 * and bottom content with supporting text, primary/secondary buttons in a visually consistent manner.
 *
 * When the bottom content takes up more than 1/3 of the screen, it is moved into the body.
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
fun CentreAlignedScreen(
    title: @Composable (horizontalPadding: Dp) -> Unit,
    modifier: Modifier = Modifier,
    image: @Composable ((horizontalPadding: Dp) -> Unit)? = null,
    body: (LazyListScope.(horizontalItemPadding: Dp) -> Unit)? = null,
    supportingText: (@Composable (horizontalPadding: Dp, topPadding: Dp) -> Unit)? = null,
    primaryButton: (@Composable () -> Unit)? = null,
    secondaryButton: (@Composable () -> Unit)? = null,
    tertiaryButton: (@Composable () -> Unit)? = null,
) {
    val screenHeight = LocalWindowInfo.current.containerSize.height.dp
    val thresholdHeight = screenHeight * ONE_THIRD
    val density = LocalDensity.current

    Column(modifier.background(colorScheme.background)) {
        /* Measures the height of SupportingTextContainer plus the BottomContent.
        If the height is over 1/3 of the total screen, the BottomContent is moved
        into the MainContent which is scrollable */
        SubcomposeLayout { constraints ->
            // Measure BottomContent
            val bottomPlaceables = subcompose("bottom") {
                BottomContent(
                    modifier = Modifier.fillMaxWidth(),
                    supportingText = supportingText,
                    primaryButton = primaryButton,
                    secondaryButton = secondaryButton,
                    tertiaryButton = tertiaryButton,
                )
            }.map { it.measure(constraints) }
            val bottomContentHeight = bottomPlaceables.maxOfOrNull { it.height } ?: 0

            // Check if BottomContent (including supporting text) exceeds 1/3 of the screen threshold
            val bottomContentOverThreshold =
                with(density) { bottomContentHeight.toDp() } > thresholdHeight

            // Measure MainContent and add BottomContent to MainContent if above threshold
            val mainPlaceables = subcompose("main") {
                MainContent(
                    title = title,
                    modifier = Modifier,
                    image = image,
                    body = body,
                    bottomContent = {
                        // Based on the height calculated above, display the BottomContent as part
                        // of the MainContent
                        if (bottomContentOverThreshold) {
                            BottomContent(
                                modifier = Modifier.fillMaxWidth(),
                                isAnchored = false,
                                supportingText = supportingText,
                                primaryButton = primaryButton,
                                secondaryButton = secondaryButton,
                                tertiaryButton = tertiaryButton,
                            )
                        }
                    },
                )
            }.map {
                // Check the height of the Bottom Content and how much it should take to display the
                // MainContent properly
                val appliedBottomContentHeightApplied =
                    if (!bottomContentOverThreshold) bottomContentHeight else 0
                it.measure(
                    // Calculate the main content display widow/ section
                    constraints.copy(
                        maxHeight = constraints.maxHeight - appliedBottomContentHeightApplied,
                    ),
                )
            }

            layout(constraints.maxWidth, constraints.maxHeight) {
                var yPosition = 0

                mainPlaceables.forEach {
                    it.placeRelative(0, yPosition)
                    yPosition += it.height
                }

                // Display the placeables defined above accordingly
                if (!bottomContentOverThreshold) {
                    bottomPlaceables.forEach {
                        it.placeRelative(0, constraints.maxHeight - bottomContentHeight)
                    }
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
            GdsHeading(
                text = title,
                modifier = Modifier.padding(horizontal = horizontalPadding),
                textAlign = GdsHeadingAlignment.CenterAligned,
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
                            buttonColors = ButtonType.Primary.buttonColors(),
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
    bottomContent: @Composable (() -> Unit)? = null,
    arrangement: Arrangement.Vertical =
        Arrangement.spacedBy(VerticalPadding, Alignment.CenterVertically),
) {
    LazyColumn(
        verticalArrangement = arrangement,
        modifier = modifier
            .fillMaxSize()
            .testTag(BODY_LAZY_COLUMN_TEST_TAG),
    ) {
        image?.let {
            item { image(HorizontalPadding) }
        }

        item {
            title(HorizontalPadding)
        }

        body?.let {
            it(HorizontalPadding)
        }

        bottomContent?.let {
            item {
                bottomContent.invoke()
            }
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
            color = colorScheme.onBackground,
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
    isAnchored: Boolean = true,
    supportingText: (@Composable (horizontalPadding: Dp, topPadding: Dp) -> Unit)? = null,
    primaryButton: (@Composable () -> Unit)? = null,
    secondaryButton: (@Composable () -> Unit)? = null,
    tertiaryButton: (@Composable () -> Unit)? = null,
) {
    val buttons = listOfNotNull(primaryButton, secondaryButton, tertiaryButton).toImmutableList()
    val verticalItemPadding = if (buttons.isEmpty() || !isAnchored) NoPadding else VerticalPadding
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(spacingDouble, Alignment.Bottom),
        modifier = modifier.padding(horizontal = HorizontalPadding, vertical = verticalItemPadding),
    ) {
        supportingText?.invoke(HorizontalPadding, NoPadding)
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
