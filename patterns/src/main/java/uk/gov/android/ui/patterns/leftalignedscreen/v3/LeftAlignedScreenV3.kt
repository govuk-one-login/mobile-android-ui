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
 * Slot-based convenience entry point for [LeftAlignedScreenV3] that keeps the defaults from
 * LeftAlignedScreenV2 for drop-in compatibility.
 *
 * The main content (title and body) sits in a scrollable container, with the bottom content
 * (supporting text and buttons) beneath it. The bottom content uses the default
 * [BottomContentBehaviour.Adaptive] behaviour: it stays pinned to the bottom until it exceeds one
 * third of the screen height, at which point it moves into the scrollable body.
 *
 * Testing note: the Adaptive behaviour measures the bottom content with a `SubcomposeLayout`. When
 * the bottom content is over the threshold it is composed twice (once to measure, once as the real
 * content moved into the body), so each element resolves to two nodes. Use `onAllNodesWithText`
 * rather than `onNodeWithText` in that case to avoid a "2 nodes found" failure.
 *
 * @param modifier [Modifier] applied to the root of the screen (optional).
 * @param title the main title slot. Use of [GdsHeading] is recommended (optional).
 * @param body the main content slot, added as items to the scrollable body (optional).
 * @param supportingText text shown above the buttons in the bottom content. Use of
 * [GdsSupportingText] is recommended (optional).
 * @param primaryButton the primary action slot. Use of [GdsButton] is recommended (optional).
 * @param secondaryButton the secondary action slot. Use of [GdsButton] is recommended (optional).
 * @param forceScroll when true, the body responds to keyboard up/down arrows for scrolling
 * (optional).
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
 * Simple-data convenience entry point for [LeftAlignedScreenV3] that keeps the defaults from
 * LeftAlignedScreenV2 for drop-in compatibility.
 *
 * Takes plain data (strings, a body list and button models) and builds the default GDS components
 * for you, rather than requiring composable slots. The bottom content uses the default
 * [BottomContentBehaviour.Adaptive] behaviour.
 *
 * @param modifier [Modifier] applied to the root of the screen (optional).
 * @param title the main title text (optional).
 * @param body the list of [LeftAlignedScreenBody] items shown in the scrollable content (optional).
 * @param supportingText text shown above the buttons in the bottom content (optional).
 * @param primaryButton the primary action, rendered as a primary [GdsButton] (optional).
 * @param secondaryButton the secondary action, rendered as a secondary [GdsButton] (optional).
 * @param forceScroll when true, the body responds to keyboard up/down arrows for scrolling
 * (optional).
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
 * Primary entry point for [LeftAlignedScreenV3], configured through parameter objects.
 *
 * This is the most flexible form: the main and bottom content are supplied as
 * [MainContentParameters] and [BottomContentParameters], and both the bottom content behaviour and
 * background colour can be set explicitly. The convenience overloads above delegate to this.
 *
 * @param modifier [Modifier] applied to the root of the screen (optional).
 * @param mainContentParameters the title and body content and their layout configuration (optional).
 * @param bottomContentParameters the supporting text and button content and their configuration
 * (optional).
 * @param bottomContentBehaviour whether the bottom content is fixed or adaptive; defaults to
 * [BottomContentBehaviour.Adaptive] to match the legacy behaviour (optional).
 * @param backgroundColor the screen background; defaults to [Color.Unspecified], which falls back
 * to the theme surface colour (optional).
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
