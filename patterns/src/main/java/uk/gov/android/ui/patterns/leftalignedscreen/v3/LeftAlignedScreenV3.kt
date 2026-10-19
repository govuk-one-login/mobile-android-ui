package uk.gov.android.ui.patterns.leftalignedscreen.v3

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.tooling.preview.Preview
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
import uk.gov.android.ui.theme.m3.Backgrounds
import uk.gov.android.ui.theme.m3.GdsTheme
import uk.gov.android.ui.theme.m3.toMappedColors
import uk.gov.android.ui.theme.meta.ExcludeFromJacocoGeneratedReport

private const val ONE_THIRD = 1f / 3f

/**
 * LeftAlignedScreen compatibility method
 *
 * Uses the defaults set by LeftAlignedScreenV2
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
 * @param forceScroll [Boolean].
 */
@Composable
fun LeftAlignedScreenV3(
    modifier: Modifier = Modifier,
    title: (@Composable (horizontalPadding: Dp) -> Unit)? = null,
    body: (LazyListScope.(horizontalItemPadding: Dp) -> Unit)? = null,
    supportingText: (@Composable (horizontalPadding: Dp) -> Unit)? = null,
    primaryButton: (@Composable () -> Unit)? = null,
    secondaryButton: (@Composable () -> Unit)? = null,
    forceScroll: Boolean = false,
) {
    val scrollState: LazyListState = rememberLazyListState()
    val columnModifier = if (forceScroll) {
        Modifier.keyboardScroll(scrollState)
    } else {
        Modifier
    }
    LeftAlignedLayout(
        modifier = modifier,
        mainContentParameters = MainContentParameters(
            titleContent = title,
            bodyContent = body,
            modifier = columnModifier,
        ),
        backgroundColor = Color.Unspecified,
        bottomContentBehaviour = LeftAlignedScreenV3Defaults.bottomContentBehaviour,
        bottomContentParameters = BottomContentParameters(
            supportingText = supportingText,
            primaryButton = primaryButton,
            secondaryButton = secondaryButton,
        ),
    )
}

/**
 * LeftAlignedScreen compatibility method
 *
 * Uses the defaults set by LeftAlignedScreenV2
 *
 * @param title represents the main title.
 * @param modifier A [Modifier] to be applied to the root layout of the screen (optional).
 * @param body representing the main content (optional).
 * @param supportingText additional text displayed below in the bottom content (optional).
 * @param primaryButton primary action button (optional).
 * @param secondaryButton secondary action button (optional).
 * @param forceScroll [Boolean].
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
    val scrollState: LazyListState = rememberLazyListState()
    val modifier = if (forceScroll) {
        Modifier.keyboardScroll(scrollState)
    } else {
        Modifier
    }

    LeftAlignedLayout(
        modifier = modifier,
        mainContentParameters =
            MainContentParameters.generateDefault(title, body).copy(modifier = modifier),
        backgroundColor = Color.Unspecified,
        bottomContentBehaviour = LeftAlignedScreenV3Defaults.bottomContentBehaviour,
        bottomContentParameters = BottomContentParameters.generateDefault(
            supportingText = supportingText,
            primaryButton = primaryButton,
            secondaryButton = secondaryButton,
        ),
    )
}

/**
 * Left Aligned Screen Helper Method
 *
 * Uses the slot based method to create the composable.
 *
 * @param modifier A [Modifier] to be applied to the root composable of the screen (optional).
 * @param mainContentParameters [MainContentParameters] the composables to be arranged and configured.
 * @param bottomContentParameters [BottomContentParameters] the composables to be arranged and configured.
 * @param bottomContentBehaviour for determining the behaviour of the [BottomContent] composable,
 * defaults to Adaptive, which matches the legacy behaviour (optional).
 * @param backgroundColor [Color] of the background defaults to Unspecified, leaving the
 * background color to be set from the theme (optional).
 */
@Composable
fun LeftAlignedScreenV3(
    modifier: Modifier = Modifier,
    mainContentParameters: MainContentParameters = MainContentParameters.empty(),
    bottomContentParameters: BottomContentParameters = BottomContentParameters.empty(),
    bottomContentBehaviour: BottomContentBehaviour =
        LeftAlignedScreenV3Defaults.bottomContentBehaviour,
    backgroundColor: Color = Color.Unspecified,
) = LeftAlignedLayout(
    bottomContentParameters = bottomContentParameters,
    mainContentParameters = mainContentParameters,
    modifier = modifier,
    bottomContentBehaviour = bottomContentBehaviour,
    backgroundColor = backgroundColor,
)

@Composable
private fun LeftAlignedLayout(
    bottomContentParameters: BottomContentParameters,
    bottomContentBehaviour: BottomContentBehaviour,
    mainContentParameters: MainContentParameters,
    backgroundColor: Color,
    modifier: Modifier = Modifier,
    scrollState: LazyListState = rememberLazyListState(),
) = when (bottomContentBehaviour) {
    BottomContentBehaviour.Adaptive ->
        LeftAlignedAdaptiveLayout(
            bottomContentParameters = bottomContentParameters,
            mainContentParameters = mainContentParameters,
            modifier = modifier,
            scrollState = scrollState,
            backgroundColor = backgroundColor,
        )

    BottomContentBehaviour.Fixed ->
        LeftAlignedFixedLayout(
            bottomContentParameters = bottomContentParameters,
            mainContentParameters = mainContentParameters,
            modifier = modifier,
            scrollState = scrollState,
            backgroundColor = backgroundColor,
        )
}

@SuppressLint("ConfigurationScreenWidthHeight")
@Suppress("LongMethod")
@Composable
private fun LeftAlignedAdaptiveLayout(
    bottomContentParameters: BottomContentParameters,
    mainContentParameters: MainContentParameters,
    backgroundColor: Color,
    scrollState: LazyListState,
    modifier: Modifier = Modifier,
) = Surface(
    modifier = modifier,
    color = backgroundColor
        .takeUnless { it == Color.Unspecified }
        ?: MaterialTheme.colorScheme.surface,
) {
    val screenHeight = LocalConfiguration.current.screenHeightDp.dp
    val thresholdHeight = screenHeight * ONE_THIRD
    val density = LocalDensity.current

    Column(modifier = Modifier.fillMaxSize()) {
        /* Measures the height of SupportingTextContainer plus the BottomContent.
        If the height is over 1/3 of the total screen, the BottomContent is moved
        into the MainContent which is scrollable */
        SubcomposeLayout { constraints ->
            // Measure BottomContent
            val bottomPlaceables = subcompose("bottom") {
                BottomContentV2(
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
                MainContentV2(
                    parameters = mainContentParameters,
                    scrollState = scrollState,
                    // Based on the height calculated above, display the BottomContent as part
                    // of the MainContent
                    bottomContent = if (bottomContentOverThreshold) {
                        {
                            BottomContentV2(
                                parameters = bottomContentParameters
                                    .copy(backgroundColor = Color.Unspecified),
                                isSticky = false,
                            )
                        }
                    } else {
                        null
                    },
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
    scrollState: LazyListState,
    modifier: Modifier = Modifier,
) = Surface(
    modifier = modifier,
    color = backgroundColor
        .takeUnless { it == Color.Unspecified }
        ?: MaterialTheme.colorScheme.surface,
) {
    Column(modifier = Modifier.fillMaxSize()) {
        MainContentV2(
            parameters = mainContentParameters,
            scrollState = scrollState,
        )
        BottomContentV2(
            parameters = bottomContentParameters,
            isSticky = true,
        )
    }
}

object LeftAlignedScreenV3Defaults {
    val bottomContentBehaviour = BottomContentBehaviour.Adaptive
}

internal object LeftAlignedScreenTestTag {
    const val BODY_LAZY_COLUMN_TEST_TAG = "BODY_LAZY_COLUMN_TEST_TAG"
}

@ExcludeFromJacocoGeneratedReport
@Composable
@PreviewLightDark
internal fun PreviewLeftAlignedScreenFixedV3(
    @PreviewParameter(LeftAlignedScreenContentProvider::class)
    content: LeftAlignedScreenContent,
) {
    GdsTheme {
        LeftAlignedScreenV3(
            bottomContentParameters = BottomContentParameters.generateDefault(
                supportingText = content.supportingText,
                primaryButton = content.primaryButton?.let { button ->
                    LeftAlignedScreenButton(
                        text = button,
                        onClick = {},
                    )
                },
                secondaryButton = content.secondaryButton?.let { button ->
                    LeftAlignedScreenButton(
                        text = button,
                        onClick = {},
                    )
                },
            ).copy(backgroundColor = Backgrounds.card.toMappedColors()),
            mainContentParameters = MainContentParameters.generateDefault(
                title = content.title,
                body = content.body?.toImmutableList(),
            ),
            bottomContentBehaviour = BottomContentBehaviour.Fixed,
            backgroundColor = Backgrounds.screen.toMappedColors(),
        )
    }
}

@ExcludeFromJacocoGeneratedReport
@Composable
@Preview
internal fun PreviewLeftAlignedScreenAdaptiveV3(
    @PreviewParameter(LeftAlignedScreenContentProvider::class)
    content: LeftAlignedScreenContent,
) {
    GdsTheme {
        LeftAlignedScreenV3(
            bottomContentParameters = BottomContentParameters.generateDefault(
                supportingText = content.supportingText,
                primaryButton = content.primaryButton?.let { button ->
                    LeftAlignedScreenButton(
                        text = button,
                        onClick = {},
                    )
                },
                secondaryButton = content.secondaryButton?.let { button ->
                    LeftAlignedScreenButton(
                        text = button,
                        onClick = {},
                    )
                },
            ).copy(backgroundColor = Backgrounds.card.toMappedColors()),
            mainContentParameters = MainContentParameters.generateDefault(
                title = content.title,
                body = content.body?.toImmutableList(),
            ),
            bottomContentBehaviour = BottomContentBehaviour.Adaptive,
            backgroundColor = Backgrounds.screen.toMappedColors(),
        )
    }
}
