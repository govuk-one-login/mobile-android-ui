package uk.gov.android.ui.patterns.leftalignedscreen

import android.annotation.SuppressLint
import androidx.compose.foundation.background
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.SubcomposeLayout
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlinx.collections.immutable.ImmutableList
import uk.gov.android.ui.componentsv2.button.ButtonTypeV2
import uk.gov.android.ui.componentsv2.button.GdsButton
import uk.gov.android.ui.componentsv2.heading.GdsHeading
import uk.gov.android.ui.componentsv2.heading.GdsHeadingAlignment
import uk.gov.android.ui.componentsv2.supportingtext.GdsSupportingText
import uk.gov.android.ui.patterns.leftalignedscreen.LeftAlignedScreenTestTag.BODY_LAZY_COLUMN_TEST_TAG
import uk.gov.android.ui.patterns.utils.clearListSemanticsForTalkBack
import uk.gov.android.ui.theme.m3.GdsTheme
import uk.gov.android.ui.theme.spacingDouble

private const val ONE_THIRD = 1f / 3f
private const val FONT_SCALE_DOUBLE = 2f

/**
 * Left Aligned Screen
 *
 * This pattern displays the main content which is placed in a scrollable container.
 * The bottom content (supporting text, primary and secondary button) is fixed.
 * When the bottom content takes up more than 1/3 of the screen, it is moved into the body.
 *
 * NOTE FOR TESTING: This function uses SubcomposeLayout to measure the bottom content so we know
 * whether to move it into the scrollable container body. The buttons in the bottom content can be
 * measured twice which results in a call to composeTestRule.onNode failing as 2 nodes are detected.
 * The workaround to this issue is to call composeTestRule.onAllNodes[1].
 *
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
    modifier: Modifier = Modifier,
    title: (@Composable (horizontalPadding: Dp) -> Unit)? = null,
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
        modifier
            .padding(top = spacingDouble)
            .background(MaterialTheme.colorScheme.background),
        verticalArrangement = arrangement,
    ) {
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
                )
            }.map { it.measure(constraints) }
            val bottomContentHeight = bottomPlaceables.maxOfOrNull { it.height } ?: 0

            // Check if BottomContent exceeds 1/3 of the screen threshold
            val bottomContentOverThreshold =
                with(density) { bottomContentHeight.toDp() } > thresholdHeight

            // Measure MainContent and add BottomContent to MainContent if above threshold
            val mainPlaceables = subcompose("main") {
                MainContent(
                    title = title,
                    body = body,
                    bottomContent = {
                        // Based on the height calculated above, display the BottomContent as part
                        // of the MainContent
                        if (bottomContentOverThreshold) {
                            BottomContent(
                                modifier = Modifier.fillMaxWidth(),
                                supportingText = supportingText,
                                primaryButton = primaryButton,
                                secondaryButton = secondaryButton,
                            )
                        }
                    },
                    arrangement = arrangement,
                    forceScroll = forceScroll,
                )
            }.map {
                // Check the height of the Bottom Content and how much it should take to display the
                // MainContent properly
                val appliedBottomContentHeight =
                    if (!bottomContentOverThreshold) bottomContentHeight else 0
                it.measure(
                    // Calculate the main content display widow/ section
                    constraints.copy(
                        maxHeight = constraints.maxHeight - appliedBottomContentHeight,
                    ),
                )
            }

            // Display the placeables defined above accordingly
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
@Composable
@Deprecated(
    message = "Use LeftAlignedScreenV2 with added forceScroll parameter instead. Aim to be " +
        "removed by 04/05/2026",
    replaceWith = ReplaceWith(
        "uk.gov.android.ui.patterns.leftalignedscreen.LeftAlignedScreenV2",
    ),
    level = DeprecationLevel.WARNING,
)
fun LeftAlignedScreenV2(
    modifier: Modifier = Modifier,
    title: String? = null,
    body: ImmutableList<LeftAlignedScreenBodyV2>? = null,
    supportingText: String? = null,
    primaryButton: LeftAlignedScreenButton? = null,
    secondaryButton: LeftAlignedScreenButton? = null,
) {
    LeftAlignedScreen(
        modifier = modifier,
        title = title?.let {
            { horizontalPadding ->
                GdsHeading(
                    text = it,
                    modifier = Modifier.padding(horizontal = horizontalPadding),
                    textAlign = GdsHeadingAlignment.LeftAligned,
                )
            }
        },
        body = { horizontalItemPadding ->
            toBodyContentV2(
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
                    buttonType = ButtonTypeV2.Primary(),
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
                    buttonType = ButtonTypeV2.Secondary(),
                    modifier = Modifier.fillMaxWidth(),
                    enabled = it.enabled,
                )
            }
        },
    )
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
 * @param forceScroll A [Boolean] indicating whether the screen should support vertical scrolling
 */
@Composable
fun LeftAlignedScreenV2(
    modifier: Modifier = Modifier,
    title: String? = null,
    body: ImmutableList<LeftAlignedScreenBodyV2>? = null,
    supportingText: String? = null,
    primaryButton: LeftAlignedScreenButton? = null,
    secondaryButton: LeftAlignedScreenButton? = null,
    forceScroll: Boolean = false,
) {
    LeftAlignedScreen(
        modifier = modifier,
        title = title?.let {
            { horizontalPadding ->
                GdsHeading(
                    text = it,
                    modifier = Modifier.padding(horizontal = horizontalPadding),
                    textAlign = GdsHeadingAlignment.LeftAligned,
                )
            }
        },
        body = { horizontalItemPadding ->
            toBodyContentV2(
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
                    buttonType = ButtonTypeV2.Primary(),
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
                    buttonType = ButtonTypeV2.Secondary(),
                    modifier = Modifier.fillMaxWidth(),
                    enabled = it.enabled,
                )
            }
        },
        forceScroll = forceScroll,
    )
}

@Composable
private fun MainContent(
    forceScroll: Boolean,
    title: (@Composable (horizontalPadding: Dp) -> Unit)? = null,
    body: (LazyListScope.(horizontalItemPadding: Dp) -> Unit)? = null,
    arrangement: Arrangement.Vertical = Arrangement.spacedBy(spacingDouble),
    @SuppressLint("ComposableLambdaParameterNaming")
    bottomContent: @Composable (() -> Unit)? = null,
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
        modifier = columnModifier
            .testTag(BODY_LAZY_COLUMN_TEST_TAG)
            .clearListSemanticsForTalkBack(),
        state = scrollState,
    ) {
        item { title?.invoke(LeftAlignedScreenDefaults.HorizontalPadding) }

        body?.let {
            it(LeftAlignedScreenDefaults.HorizontalPadding)
        }

        bottomContent?.let {
            item { it.invoke() }
        }
    }
}

@Composable
private fun BottomContent(
    modifier: Modifier = Modifier,
    supportingText: (@Composable (horizontalPadding: Dp) -> Unit)? = null,
    primaryButton: (@Composable () -> Unit)? = null,
    secondaryButton: (@Composable () -> Unit)? = null,
) {
    Column(
        modifier.padding(horizontal = LeftAlignedScreenDefaults.HorizontalPadding),
    ) {
        val supportingTextPadding =
            if (primaryButton == null || secondaryButton == null) {
                LeftAlignedScreenDefaults.HorizontalPadding
            } else
                LeftAlignedScreenDefaults.NoPadding

        supportingText?.let {
            Row(
                modifier = Modifier.padding(
                    top = spacingDouble,
                    bottom = supportingTextPadding,
                ),
            ) {
                it.invoke(LeftAlignedScreenDefaults.HorizontalPadding)
            }
        }
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
    val NoPadding: Dp = 0.dp
}

internal object LeftAlignedScreenTestTag {
    const val BODY_LAZY_COLUMN_TEST_TAG = "BODY_LAZY_COLUMN_TEST_TAG"
}

@PreviewLightDark
@Composable
@Preview(fontScale = 3f)
internal fun PreviewLeftAlignedScreenV2(
    @PreviewParameter(LeftAlignedScreenContentProviderV2::class)
    content: LeftAlignedScreenContentV2,
) {
    GdsTheme {
        LeftAlignedScreenFromContentParamsV2(content)
    }
}

@Composable
@Preview(showBackground = true, fontScale = FONT_SCALE_DOUBLE)
@Preview(showBackground = true, fontScale = 3f)
internal fun PreviewLeftAlignedScreenV2Accessibility(
    @PreviewParameter(LeftAlignedScreenContentAccessibilityProviderV2::class)
    content: LeftAlignedScreenContentV2,
) {
    GdsTheme {
        LeftAlignedScreenFromContentParamsV2(content)
    }
}
