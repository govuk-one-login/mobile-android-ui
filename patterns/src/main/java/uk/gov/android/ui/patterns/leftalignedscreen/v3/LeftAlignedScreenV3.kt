package uk.gov.android.ui.patterns.leftalignedscreen.v3

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.SubcomposeLayout
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList
import uk.gov.android.ui.componentsv2.button.GdsButton
import uk.gov.android.ui.componentsv2.heading.GdsHeading
import uk.gov.android.ui.componentsv2.supportingtext.GdsSupportingText
import uk.gov.android.ui.patterns.utils.ModifierExtensions.keyboardScroll
import uk.gov.android.ui.patterns.utils.clearListSemanticsForTalkBack
import uk.gov.android.ui.theme.m3.GdsTheme
import uk.gov.android.ui.theme.meta.ExcludeFromJacocoGeneratedReport
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
 * @param body representing the main content.
 * @param supportingText additional text displayed below in the bottom content. Use of [GdsSupportingText] composable is recommended (optional).
 * @param primaryButton primary action button. Use of [GdsButton] composable is recommended (optional).
 * @param secondaryButton secondary action button. Use of [GdsButton] composable is recommended (optional).
 * @param arrangement specifies the vertical alignment and default spacing between each component (optional).
 * @param forceScroll [Boolean] determines whether a scroll can be added to a keyboard down arrow event to avoid focus becoming stuck
 */
@Composable
fun LeftAlignedScreenV3(
    modifier: Modifier = Modifier,
    title: (@Composable (horizontalPadding: Dp) -> Unit)? = null,
    body: (LazyListScope.(horizontalItemPadding: Dp) -> Unit)? = null,
    supportingText: (@Composable (horizontalPadding: Dp) -> Unit)? = null,
    primaryButton: (@Composable () -> Unit)? = null,
    secondaryButton: (@Composable () -> Unit)? = null,
    arrangement: Arrangement.Vertical = LeftAlignedScreenDefaults.ItemArrangement,
    forceScroll: Boolean = false,
) = LeftAlignedLayout(
    modifier = modifier,
    mainContentParameters = MainContentParameters(
        titleContent = title,
        bodyContent = body,
    ),
    backgroundColor = Color.Unspecified,
    bottomContentBehaviour = LeftAlignedScreenDefaults.bottomContentBehaviour,
    bottomContentParameters = BottomContentParameters(
        supportingText = supportingText,
        primaryButton = primaryButton,
        secondaryButton = secondaryButton,
    ),
    arrangement = arrangement,
    forceScroll = forceScroll,
)

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
fun LeftAlignedScreenV3(
    modifier: Modifier = Modifier,
    title: String? = null,
    body: ImmutableList<LeftAlignedScreenBody>? = null,
    supportingText: String? = null,
    primaryButton: LeftAlignedScreenButton? = null,
    secondaryButton: LeftAlignedScreenButton? = null,
    forceScroll: Boolean = false,
) {
    LeftAlignedLayout(
        modifier = modifier,
        mainContentParameters = MainContentParameters.generateDefault(title, body),
        backgroundColor = Color.Unspecified,
        bottomContentBehaviour = LeftAlignedScreenDefaults.bottomContentBehaviour,
        bottomContentParameters = BottomContentParameters.generateDefault(
            supportingText = supportingText,
            primaryButton = primaryButton,
            secondaryButton = secondaryButton,
        ),
        forceScroll = forceScroll,
    )
}

/**
 * LeftAlignedScreen
 * TODO
 */
@Suppress("MaxLineLength")
@Composable
fun LeftAlignedScreenV3(
    bottomContentParameters: BottomContentParameters,
    mainContentParameters: MainContentParameters,
    modifier: Modifier = Modifier,
    arrangement: Arrangement.Vertical = LeftAlignedScreenDefaults.ItemArrangement,
    bottomContentBehaviour: BottomContentBehaviour = LeftAlignedScreenDefaults.bottomContentBehaviour,
    backgroundColor: Color = Color.Unspecified,
    forceScroll: Boolean = false,
) = LeftAlignedLayout(
    bottomContentParameters = bottomContentParameters,
    mainContentParameters = mainContentParameters,
    modifier = modifier,
    arrangement = arrangement,
    bottomContentBehaviour = bottomContentBehaviour,
    backgroundColor = backgroundColor,
    forceScroll = forceScroll,
)

@Composable
private fun LeftAlignedLayout(
    bottomContentParameters: BottomContentParameters,
    bottomContentBehaviour: BottomContentBehaviour,
    mainContentParameters: MainContentParameters,
    backgroundColor: Color,
    modifier: Modifier = Modifier,
    arrangement: Arrangement.Vertical = LeftAlignedScreenDefaults.ItemArrangement,
    forceScroll: Boolean = false,
) = when (bottomContentBehaviour) {
    BottomContentBehaviour.Adaptive ->
        LeftAlignedAdaptiveLayout(
            bottomContentParameters = bottomContentParameters,
            mainContentParameters = mainContentParameters,
            modifier = modifier,
            arrangement = arrangement,
            backgroundColor = backgroundColor,
            forceScroll = forceScroll,
        )

    BottomContentBehaviour.Fixed ->
        LeftAlignedFixedLayout(
            bottomContentParameters = bottomContentParameters,
            mainContentParameters = mainContentParameters,
            modifier = modifier,
            arrangement = arrangement,
            backgroundColor = backgroundColor,
            forceScroll = forceScroll,
        )
}

@SuppressLint("ConfigurationScreenWidthHeight")
@Suppress("LongMethod")
@Composable
private fun LeftAlignedAdaptiveLayout(
    bottomContentParameters: BottomContentParameters,
    mainContentParameters: MainContentParameters,
    backgroundColor: Color,
    modifier: Modifier = Modifier,
    arrangement: Arrangement.Vertical = LeftAlignedScreenDefaults.ItemArrangement,
    forceScroll: Boolean = false,
) = Surface(
    modifier = modifier,
    color = backgroundColor
        .takeUnless { it == Color.Unspecified }
        ?: MaterialTheme.colorScheme.surface,
) {
    val screenHeight = LocalConfiguration.current.screenHeightDp.dp
    val thresholdHeight = screenHeight * ONE_THIRD
    val density = LocalDensity.current

    Column(
        modifier = Modifier.padding(top = spacingDouble),
        verticalArrangement = arrangement,
    ) {
        /* Measures the height of SupportingTextContainer plus the BottomContent.
        If the height is over 1/3 of the total screen, the BottomContent is moved
        into the MainContent which is scrollable */
        SubcomposeLayout { constraints ->
            // Measure BottomContent
            val bottomPlaceables = subcompose("bottom") {
                BottomContentV2(
                    modifier = Modifier.fillMaxWidth(),
                    parameters = bottomContentParameters,
                    isSticky = true,
                )
            }.map { it.measure(constraints) }
            val bottomContentHeight = bottomPlaceables.maxOfOrNull { it.height } ?: 0

            // Check if BottomContent exceeds 1/3 of the screen threshold
            val bottomContentOverThreshold =
                with(density) { bottomContentHeight.toDp() } > thresholdHeight

            // Measure MainContent and add BottomContent to MainContent if above threshold
            val mainPlaceables = subcompose("main") {
                MainContent(
                    titleContent = mainContentParameters.titleContent,
                    bodyContent = mainContentParameters.bodyContent,
                    bottomContent = {
                        // Based on the height calculated above, display the BottomContent as part
                        // of the MainContent
                        if (bottomContentOverThreshold) {
                            BottomContentV2(
                                modifier = Modifier.fillMaxWidth(),
                                parameters = bottomContentParameters
                                    .copy(backgroundColor = Color.Unspecified),
                                isSticky = false,
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

@Composable
private fun LeftAlignedFixedLayout(
    bottomContentParameters: BottomContentParameters,
    mainContentParameters: MainContentParameters,
    backgroundColor: Color,
    modifier: Modifier = Modifier,
    arrangement: Arrangement.Vertical = LeftAlignedScreenDefaults.ItemArrangement,
    forceScroll: Boolean = false,
) = Surface(
    modifier = modifier,
    color = backgroundColor
        .takeUnless { it == Color.Unspecified }
        ?: MaterialTheme.colorScheme.surface,
) {
    Column(
        modifier = Modifier
            .padding(top = spacingDouble)
            .fillMaxSize(),
        verticalArrangement = arrangement,
    ) {
        MainContentV2(
            parameters = mainContentParameters,
            forceScroll = forceScroll,
        )
        BottomContentV2(
            modifier = Modifier.fillMaxWidth(),
            parameters = bottomContentParameters,
            isSticky = true,
        )
    }
}

@Composable
private fun MainContent(
    forceScroll: Boolean,
    titleContent: (@Composable (horizontalPadding: Dp) -> Unit)? = null,
    bodyContent: (LazyListScope.(horizontalItemPadding: Dp) -> Unit)? = null,
    arrangement: Arrangement.Vertical = Arrangement.spacedBy(spacingDouble),
    bottomContent: @Composable (() -> Unit)? = null,
) {
    val scrollState: LazyListState = rememberLazyListState()
    LazyColumn(
        verticalArrangement = arrangement,
        modifier = Modifier
            .fillMaxSize()
            .also { mod ->
                if (forceScroll) {
                    mod.keyboardScroll(scrollState)
                }
            }
            .testTag(LeftAlignedScreenTestTag.BODY_LAZY_COLUMN_TEST_TAG)
            .clearListSemanticsForTalkBack(),
        state = scrollState,
    ) {
        titleContent?.let { title ->
            item { title(LeftAlignedScreenDefaults.HorizontalPadding) }
        }
        bodyContent?.let { body ->
            body(LeftAlignedScreenDefaults.HorizontalPadding)
        }
        bottomContent?.let { bottom -> item { bottom() } }
    }
}

object LeftAlignedScreenDefaults {
    val ItemArrangement = Arrangement.spacedBy(spacingDouble)
    val HorizontalPadding: Dp = spacingDouble
    val bottomContentBehaviour = BottomContentBehaviour.Adaptive
}

internal object LeftAlignedScreenTestTag {
    const val BODY_LAZY_COLUMN_TEST_TAG = "BODY_LAZY_COLUMN_TEST_TAG"
}

@ExcludeFromJacocoGeneratedReport
@Composable
@PreviewLightDark
internal fun PreviewLeftAlignedScreenV3(
    @PreviewParameter(LeftAlignedScreenContentProvider::class)
    content: LeftAlignedScreenContent,
) {
    GdsTheme {
        LeftAlignedScreenV3(
            title = content.title,
            body = content.body?.toImmutableList(),
            supportingText = content.supportingText,
            primaryButton = LeftAlignedScreenButton(
                text = content.primaryButton.orEmpty(),
                onClick = {},
            ),
            secondaryButton = LeftAlignedScreenButton(
                text = content.primaryButton.orEmpty(),
                onClick = {},
            ),
        )
    }
}

//@Composable
//@Preview(showBackground = true, fontScale = FONT_SCALE_DOUBLE)
//// @Preview(showBackground = true, fontScale = 3f)
//internal fun PreviewLeftAlignedScreenV3Accessibility(
//    @PreviewParameter(LeftAlignedScreenContentAccessibilityProvider::class)
//    content: LeftAlignedScreenContent,
//) {
//    GdsTheme {
//        LeftAlignedScreenFromContentParams(content)
//    }
//}
