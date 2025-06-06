package uk.gov.android.ui.patterns.leftalignedscreen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.SubcomposeLayout
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlinx.collections.immutable.ImmutableList
import uk.gov.android.ui.componentsv2.button.ButtonType
import uk.gov.android.ui.componentsv2.button.GdsButton
import uk.gov.android.ui.componentsv2.heading.GdsHeading
import uk.gov.android.ui.componentsv2.heading.GdsHeadingAlignment
import uk.gov.android.ui.componentsv2.supportingtext.GdsSupportingText
import uk.gov.android.ui.theme.m3.GdsTheme
import uk.gov.android.ui.theme.spacingDouble
import uk.gov.android.ui.theme.util.UnstableDesignSystemAPI

private const val ONE_THIRD = 1f / 3f
private const val FONT_SCALE_DOUBLE = 2f

/**
 * Left Aligned Screen
 *
 * This pattern displays the main content which is placed in a scrollable container.
 * The bottom content (supporting text, primary and secondary button) is fixed.
 * When the bottom content takes up more than 1/3 of the screen, the supporting text is moved into the body
 * @param title represents the main title. Use of [GdsHeading] is recommended
 * @param modifier A [Modifier] to be applied to the root layout of the screen (optional).
 * @sample LazyListScope.toBodyContent
 * @param body representing the main content.
 * @param supportingText additional text displayed below in the bottom content. Use of [GdsSupportingText] composable is recommended (optional).
 * @param primaryButton primary action button. Use of [GdsButton] composable is recommended (optional).
 * @param secondaryButton secondary action button. Use of [GdsButton] composable is recommended (optional).
 * @param arrangement specifies the vertical alignment and default spacing between each component (optional).
 * @sample LeftAlignedScreenFromContentParams
 * @param forceScroll [Boolean] determines whether a scroll can be added to a keyboard down arrow event to avoid focus becoming stuck
 */
@SuppressLint("ConfigurationScreenWidthHeight")
@Suppress("LongMethod")
@Composable
fun LeftAlignedScreen(
    title: @Composable (horizontalPadding: Dp) -> Unit,
    modifier: Modifier = Modifier,
    body: (LazyListScope.(horizontalItemPadding: Dp) -> Unit)? = null,
    supportingText: (@Composable (horizontalPadding: Dp) -> Unit)? = null,
    primaryButton: (@Composable () -> Unit)? = null,
    secondaryButton: (@Composable () -> Unit)? = null,
    arrangement: Arrangement.Vertical = LeftAlignedScreenDefaults.ItemArrangement,
    forceScroll: Boolean = false,
) {
    val screenHeight = LocalConfiguration.current.screenHeightDp.dp
    val thresholdHeight = screenHeight * ONE_THIRD
    val density = LocalDensity.current

    Column(
        modifier.padding(top = spacingDouble),
        verticalArrangement = arrangement,
    ) {
        /* Measures the height of SupportingTextContainer plus the BottomContent.
        If the height is over 1/3 of the total screen, the SupportingText is moved
        into the MainContent which is scrollable */
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
            val supportingTextPlaceables = if (supportingText == null) {
                emptyList()
            } else {
                subcompose("supportingText") {
                    SupportingTextContainer(
                        primaryButton != null,
                        secondaryButton != null,
                    ) { supportingText.invoke(LeftAlignedScreenDefaults.HorizontalPadding) }
                }.map { it.measure(constraints) }
            }
            val supportingTextHeight = supportingTextPlaceables.maxOfOrNull { it.height } ?: 0

            // Check if BottomContent + Supporting text exceeds 1/3 of the screen threshold
            val totalBottomContentHeight = bottomContentHeight + supportingTextHeight
            val bottomContentOverThreshold =
                with(density) { totalBottomContentHeight.toDp() } > thresholdHeight

            // Measure MainContent. Add SupportingText to MainContent if above threshold
            val mainPlaceables = subcompose("main") {
                MainContent(
                    title = title,
                    body = body,
                    supportingText = if (bottomContentOverThreshold) {
                        supportingText
                    } else {
                        null
                    },
                    arrangement = arrangement,
                    forceScroll = forceScroll,
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
 * Left Aligned Screen Helper Method
 *
 * Uses the slot based method to create the composable
 *
 * @param title represents the main title.
 * @param modifier A [Modifier] to be applied to the root layout of the screen (optional).
 * @param body representing the main content (optional).
 * @param supportingText additional text displayed below in the bottom content (optional).
 * @param primaryButton primary action button (optional).
 * @param secondaryButton secondary action button (optional).
 */

@OptIn(UnstableDesignSystemAPI::class)
@Composable
fun LeftAlignedScreen(
    title: String,
    modifier: Modifier = Modifier,
    body: ImmutableList<LeftAlignedScreenBody>? = null,
    supportingText: String? = null,
    primaryButton: LeftAlignedScreenButton? = null,
    secondaryButton: LeftAlignedScreenButton? = null,
) {
    LeftAlignedScreen(
        modifier = modifier,
        title = { horizontalPadding ->
            GdsHeading(
                text = title,
                modifier = Modifier.padding(horizontal = horizontalPadding),
                textAlign = GdsHeadingAlignment.LeftAligned,
            )
        },
        body = { horizontalItemPadding ->
            toBodyContent(
                horizontalItemPadding = horizontalItemPadding,
                body = body,
            )
        },
        supportingText = supportingText?.let { text ->
            { horizontalPadding ->
                GdsSupportingText(
                    text = text,
                    modifier = Modifier.padding(horizontal = horizontalPadding),
                )
            }
        },
        primaryButton = primaryButton?.let {
            {
                GdsButton(
                    text = it.text,
                    onClick = it.onClick,
                    buttonType = ButtonType.Primary,
                    modifier = Modifier.fillMaxWidth(),
                    enabled = it.enabled,
                )
            }
        },
        secondaryButton = secondaryButton?.let {
            {
                GdsButton(
                    text = it.text,
                    onClick = it.onClick,
                    buttonType = ButtonType.Secondary,
                    modifier = Modifier.fillMaxWidth(),
                    enabled = it.enabled,
                )
            }
        },
    )
}

@Composable
private fun MainContent(
    title: @Composable (horizontalPadding: Dp) -> Unit,
    forceScroll: Boolean,
    body: (LazyListScope.(horizontalItemPadding: Dp) -> Unit)? = null,
    arrangement: Arrangement.Vertical = Arrangement.spacedBy(spacingDouble),
    @SuppressLint("ComposableLambdaParameterNaming")
    supportingText: (@Composable (horizontalPadding: Dp) -> Unit)? = null,
) {
    val scrollState: LazyListState = rememberLazyListState()
    val columnModifier = if (forceScroll) {
        Modifier
            .fillMaxSize()
            .bringIntoView(scrollState)
    } else {
        Modifier.fillMaxSize()
    }
    LazyColumn(
        verticalArrangement = arrangement,
        modifier = columnModifier,
        state = scrollState,
    ) {
        item { title(LeftAlignedScreenDefaults.HorizontalPadding) }

        body?.let {
            it(LeftAlignedScreenDefaults.HorizontalPadding)
        }

        supportingText?.let {
            item { it.invoke(LeftAlignedScreenDefaults.HorizontalPadding) }
        }
    }
}

@Composable
private fun SupportingTextContainer(
    primaryButtonPresent: Boolean,
    secondaryButtonPresent: Boolean,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    val bottomPadding = if (!primaryButtonPresent && !secondaryButtonPresent) {
        spacingDouble
    } else {
        0.dp
    }
    Row(
        modifier = modifier.padding(
            top = spacingDouble,
            bottom = bottomPadding,
        ),
    ) {
        content()
    }
}

@Composable
private fun BottomContent(
    modifier: Modifier = Modifier,
    primaryButton: (@Composable () -> Unit)? = null,
    secondaryButton: (@Composable () -> Unit)? = null,
) {
    Column(
        modifier.padding(horizontal = LeftAlignedScreenDefaults.HorizontalPadding),
    ) {
        primaryButton?.let {
            val bottomPadding = if (secondaryButton == null) spacingDouble else 0.dp
            Spacer(modifier = Modifier.height(spacingDouble))

            primaryButton()

            Spacer(modifier = Modifier.height(bottomPadding))
        }

        secondaryButton?.let {
            val topPadding = if (primaryButton == null) 0.dp else spacingDouble

            Spacer(modifier = Modifier.height(topPadding))

            secondaryButton()

            Spacer(modifier = Modifier.height(spacingDouble))
        }
    }
}

object LeftAlignedScreenDefaults {
    val ItemArrangement = Arrangement.spacedBy(spacingDouble)
    val HorizontalPadding: Dp = spacingDouble
}

@PreviewLightDark
@Composable
internal fun PreviewLeftAlignedScreen(
    @PreviewParameter(LeftAlignedScreenContentProvider::class)
    content: LeftAlignedScreenContent,
) {
    GdsTheme {
        LeftAlignedScreenFromContentParams(content)
    }
}

@Composable
@Preview(showBackground = true, fontScale = FONT_SCALE_DOUBLE)
internal fun PreviewLeftAlignedScreenAccessibility(
    @PreviewParameter(LeftAlignedScreenContentAccessibilityProvider::class)
    content: LeftAlignedScreenContent,
) {
    GdsTheme {
        LeftAlignedScreenFromContentParams(content)
    }
}
