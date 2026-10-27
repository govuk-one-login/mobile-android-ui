package uk.gov.android.ui.patterns.leftalignedscreen.v3

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.SubcomposeLayout
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlinx.collections.immutable.ImmutableList
import uk.gov.android.ui.componentsv2.button.ButtonTypeV2
import uk.gov.android.ui.componentsv2.button.GdsButton
import uk.gov.android.ui.componentsv2.heading.GdsHeading
import uk.gov.android.ui.componentsv2.heading.GdsHeadingAlignment
import uk.gov.android.ui.componentsv2.supportingtext.GdsSupportingText
import uk.gov.android.ui.componentsv2.topappbar.GdsTopAppBar
import uk.gov.android.ui.componentsv2.topappbar.GdsTopAppBarDefaults
import uk.gov.android.ui.patterns.leftalignedscreen.v3.MainContentParameters.Companion.GenerateDefault
import uk.gov.android.ui.patterns.utils.ModifierExtensions.keyboardScroll
import uk.gov.android.ui.theme.m3.Backgrounds
import uk.gov.android.ui.theme.m3.GdsTheme
import uk.gov.android.ui.theme.m3.toMappedColors
import uk.gov.android.ui.theme.meta.ExcludeFromJacocoGeneratedReport
import uk.gov.android.ui.theme.spacingDouble
import uk.gov.android.ui.theme.spacingSingle
import uk.gov.android.ui.theme.spacingTriple

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
 * @param topAppBar the top app bar slot, rendered above the scrollable content. The screen owns a
 * pinned [TopAppBarScrollBehavior] and passes it to this slot; forward it to
 * [GdsTopAppBar]'s `scrollBehaviour` so the bar reacts to the body scrolling. Use of [GdsTopAppBar]
 * is recommended (optional).
 * @param title the main title slot. Use of [GdsHeading] is recommended (optional).
 * @param body the main content slot, added as items to the scrollable body (optional).
 * @param supportingText text shown above the buttons in the bottom content. Receives the
 * configured horizontal padding and a vertical padding hook that can be used, customised with a
 * different value, or ignored to control the gap above this element independently of
 * [BottomContentConfig.verticalPadding]. Use of [GdsSupportingText] is recommended (optional).
 * @param primaryButton the primary action slot. Use of [GdsButton] is recommended (optional).
 * @param secondaryButton the secondary action slot. Use of [GdsButton] is recommended (optional).
 * @param forceScroll when true, the body responds to keyboard up/down arrows for scrolling
 * (optional).
 * @param mainContentConfig the layout configuration for the main content, including
 * [MainContentConfig.verticalPadding] to customise the default 16.dp gap between body elements
 * (e.g. 8.dp or 24.dp). Note that any [Modifier] set on it is overridden when [forceScroll] is
 * true (optional).
 * @param bottomContentConfig the layout configuration for the bottom content, including
 * [BottomContentConfig.verticalPadding] to customise the default 16.dp gap between footer elements
 * (optional).
 * @param bottomContentBehaviour whether the bottom content is fixed or adaptive; defaults to
 * [BottomContentBehaviour.Adaptive] to match the legacy behaviour (optional).
 * @param backgroundColor the screen background; defaults to [Color.Unspecified], which falls back
 * to the theme surface colour (optional).
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LeftAlignedScreenV3(
    modifier: Modifier = Modifier,
    topAppBar: (@Composable (scrollBehaviour: TopAppBarScrollBehavior) -> Unit)? = null,
    title: (@Composable (horizontalPadding: Dp) -> Unit)? = null,
    body: (LazyListScope.(horizontalItemPadding: Dp) -> Unit)? = null,
    supportingText: (@Composable (horizontalPadding: Dp, verticalPadding: Dp) -> Unit)? = null,
    primaryButton: (@Composable () -> Unit)? = null,
    secondaryButton: (@Composable () -> Unit)? = null,
    forceScroll: Boolean = false,
    mainContentConfig: MainContentConfig = MainContentConfig(),
    bottomContentConfig: BottomContentConfig = BottomContentConfig(),
    bottomContentBehaviour: BottomContentBehaviour =
        LeftAlignedScreenV3Defaults.bottomContentBehaviour,
    backgroundColor: Color = Color.Unspecified,
) {
    val scrollState: LazyListState = rememberLazyListState()
    val columnModifier = if (forceScroll) {
        Modifier.keyboardScroll(scrollState)
    } else {
        mainContentConfig.modifier
    }
    LeftAlignedLayout(
        modifier = modifier,
        backgroundColor = backgroundColor,
        topAppBar = topAppBar,
        title = title,
        body = body,
        mainContentConfig = mainContentConfig.copy(modifier = columnModifier),
        supportingText = supportingText,
        primaryButton = primaryButton,
        secondaryButton = secondaryButton,
        bottomContentConfig = bottomContentConfig,
        bottomContentBehaviour = bottomContentBehaviour,
        scrollState = scrollState,
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
 * @param topAppBar the top app bar, rendered above the scrollable content as an opinionated
 * [GdsTopAppBar] built from the [LeftAlignedScreenTopAppBar] data (optional).
 * @param title the main title text (optional).
 * @param body the list of [LeftAlignedScreenBody] items shown in the scrollable content (optional).
 * @param supportingText text shown above the buttons in the bottom content (optional).
 * @param primaryButton the primary action, rendered as a primary [GdsButton] (optional).
 * @param secondaryButton the secondary action, rendered as a secondary [GdsButton] (optional).
 * @param forceScroll when true, the body responds to keyboard up/down arrows for scrolling
 * (optional).
 * @param mainContentConfig the layout configuration for the main content. Note that any [Modifier]
 * set on it is overridden when [forceScroll] is true (optional).
 * @param bottomContentConfig the layout configuration for the bottom content (optional).
 * @param bottomContentBehaviour whether the bottom content is fixed or adaptive; defaults to
 * [BottomContentBehaviour.Adaptive] to match the legacy behaviour (optional).
 * @param backgroundColor the screen background; defaults to [Color.Unspecified], which falls back
 * to the theme surface colour (optional).
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LeftAlignedScreenV3(
    modifier: Modifier = Modifier,
    topAppBar: LeftAlignedScreenTopAppBar? = null,
    title: String? = null,
    body: ImmutableList<LeftAlignedScreenBody>? = null,
    supportingText: String? = null,
    primaryButton: LeftAlignedScreenButton? = null,
    secondaryButton: LeftAlignedScreenButton? = null,
    forceScroll: Boolean = false,
    mainContentConfig: MainContentConfig = MainContentConfig(),
    bottomContentConfig: BottomContentConfig = BottomContentConfig(),
    bottomContentBehaviour: BottomContentBehaviour =
        LeftAlignedScreenV3Defaults.bottomContentBehaviour,
    backgroundColor: Color = Color.Unspecified,
) {
    val scrollState: LazyListState = rememberLazyListState()
    val columnModifier = if (forceScroll) {
        Modifier.keyboardScroll(scrollState)
    } else {
        mainContentConfig.modifier
    }

    val mainContentParameters = MainContentParameters.generateDefault(title, body)
    val bottomContentParameters = BottomContentParameters.generateDefault(
        supportingText = supportingText,
        primaryButton = primaryButton,
        secondaryButton = secondaryButton,
    )
    val topAppBarContentParameters = TopAppBarContentParameters.generateDefault(topAppBar)

    LeftAlignedLayout(
        modifier = modifier,
        backgroundColor = backgroundColor,
        topAppBar = topAppBarContentParameters.topAppBar,
        title = mainContentParameters.titleContent,
        body = mainContentParameters.bodyContent,
        mainContentConfig = mainContentConfig.copy(modifier = columnModifier),
        supportingText = bottomContentParameters.supportingText,
        primaryButton = bottomContentParameters.primaryButton,
        secondaryButton = bottomContentParameters.secondaryButton,
        bottomContentConfig = bottomContentConfig,
        bottomContentBehaviour = bottomContentBehaviour,
        scrollState = scrollState,
    )
}

@SuppressLint("ConfigurationScreenWidthHeight")
@Suppress("LongMethod", "LongParameterList")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun LeftAlignedLayout(
    backgroundColor: Color,
    topAppBar: (@Composable (scrollBehaviour: TopAppBarScrollBehavior) -> Unit)?,
    title: (@Composable (horizontalPadding: Dp) -> Unit)?,
    body: (LazyListScope.(horizontalItemPadding: Dp) -> Unit)?,
    mainContentConfig: MainContentConfig,
    supportingText: (@Composable (horizontalPadding: Dp, verticalPadding: Dp) -> Unit)?,
    primaryButton: (@Composable () -> Unit)?,
    secondaryButton: (@Composable () -> Unit)?,
    bottomContentConfig: BottomContentConfig,
    bottomContentBehaviour: BottomContentBehaviour,
    modifier: Modifier = Modifier,
    scrollState: LazyListState = rememberLazyListState(),
) = Surface(
    modifier = modifier,
    color = backgroundColor
        .takeUnless { it == Color.Unspecified }
        ?: MaterialTheme.colorScheme.surface,
) {
    val screenHeight = LocalConfiguration.current.screenHeightDp.dp
    val thresholdHeight = screenHeight * ONE_THIRD
    val density = LocalDensity.current

    // A single scroll behaviour instance is shared between the top app bar and the scrollable
    // body: the bar receives it (so it can react to scrolling, e.g. the scrolled container colour)
    // and the body feeds it scroll deltas through the nested scroll connection attached below.
    val topAppBarScrollBehaviour = GdsTopAppBarDefaults.scrollBehaviour()

    Column(modifier = Modifier.fillMaxSize()) {
        TopAppBarContentV3(
            topAppBar = topAppBar,
            scrollBehaviour = topAppBarScrollBehaviour,
        )

        /* Measures the height of SupportingTextContainer plus the BottomContent.
        For the Adaptive behaviour, if the height is over 1/3 of the total screen, the
        BottomContent is moved into the MainContent which is scrollable. For the Fixed behaviour
        the BottomContent always stays pinned as a sticky footer. */
        SubcomposeLayout(
            modifier = Modifier.nestedScroll(topAppBarScrollBehaviour.nestedScrollConnection),
        ) { constraints ->
            // Measure BottomContent
            val bottomPlaceables = subcompose("bottom") {
                BottomContentV3(
                    supportingText = supportingText,
                    primaryButton = primaryButton,
                    secondaryButton = secondaryButton,
                    config = bottomContentConfig,
                    isSticky = true,
                )
            }.map { it.measure(constraints) }
            val bottomContentHeight = bottomPlaceables.maxOfOrNull { it.height } ?: 0

            // Check if BottomContent exceeds 1/3 of the screen threshold. For the Fixed behaviour
            // the bottom content never moves into the scrollable body, so this is always false.
            val bottomContentOverThreshold =
                bottomContentBehaviour == BottomContentBehaviour.Adaptive &&
                    with(density) { bottomContentHeight.toDp() } > thresholdHeight

            // Measure MainContent and add BottomContent to MainContent if above threshold
            val mainPlaceables = subcompose("main") {
                MainContentV3(
                    titleContent = title,
                    bodyContent = body,
                    config = mainContentConfig,
                    scrollState = scrollState,
                    // Based on the height calculated above, display the BottomContent as part
                    // of the MainContent
                    bottomContent = if (bottomContentOverThreshold) {
                        {
                            BottomContentV3(
                                supportingText = supportingText,
                                primaryButton = primaryButton,
                                secondaryButton = secondaryButton,
                                // Remove custom background colour used for the "Sticky" position
                                config = bottomContentConfig
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
    PreviewLeftAlignedScreenV3(content, BottomContentBehaviour.Fixed)
}

@ExcludeFromJacocoGeneratedReport
@Composable
@Preview
internal fun PreviewLeftAlignedScreenAdaptiveV3(
    @PreviewParameter(LeftAlignedScreenContentProvider::class)
    content: LeftAlignedScreenContent,
) {
    PreviewLeftAlignedScreenV3(content, BottomContentBehaviour.Adaptive)
}

@ExcludeFromJacocoGeneratedReport
@Composable
@Preview
internal fun PreviewLeftAlignedScreenWithSpacing() {
    PreviewLeftAlignedScreenCustomVerticalPaddingV3(48.dp)
}

@ExcludeFromJacocoGeneratedReport
@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun PreviewLeftAlignedScreenV3(
    content: LeftAlignedScreenContent,
    bottomContentBehaviour: BottomContentBehaviour,
    mainContentConfig: MainContentConfig = MainContentConfig(),
    bottomContentConfig: BottomContentConfig = BottomContentConfig(
        backgroundColor = Backgrounds.card.toMappedColors(),
    ),
) {
    GdsTheme {
        LeftAlignedScreenV3(
            topAppBar = TopAppBarContentParameters.generateDefault(content.topAppBar).topAppBar,
            title = content.title?.let { title ->
                { horizontalPadding ->
                    GdsHeading(
                        text = title,
                        modifier = Modifier.padding(horizontal = horizontalPadding),
                        textAlign = GdsHeadingAlignment.LeftAligned,
                    )
                }
            },
            body = { horizontalItemPadding ->
                content.body?.forEach {
                    item { it.GenerateDefault(horizontalItemPadding) }
                }
            },
            supportingText = content.supportingText?.let { text ->
                { horizontalPadding, verticalPadding ->
                    GdsSupportingText(
                        text = text,
                        modifier = Modifier.padding(
                            horizontal = horizontalPadding,
                            vertical = verticalPadding,
                        ),
                    )
                }
            },
            primaryButton = content.primaryButton?.let { button ->
                {
                    GdsButton(
                        text = button,
                        onClick = {},
                        buttonType = ButtonTypeV2.Primary(),
                        modifier = Modifier.fillMaxWidth(),
                    )
                }
            },
            secondaryButton = content.secondaryButton?.let { button ->
                {
                    GdsButton(
                        text = button,
                        onClick = {},
                        buttonType = ButtonTypeV2.Secondary(),
                        modifier = Modifier.fillMaxWidth(),
                    )
                }
            },
            mainContentConfig = mainContentConfig,
            bottomContentConfig = bottomContentConfig,
            bottomContentBehaviour = bottomContentBehaviour,
            backgroundColor = Backgrounds.screen.toMappedColors(),
        )
    }
}

@ExcludeFromJacocoGeneratedReport
@Composable
@Preview(showBackground = true)
internal fun PreviewLeftAlignedScreenCustomVerticalPaddingV3(
    @PreviewParameter(LeftAlignedScreenVerticalPaddingProvider::class)
    verticalPadding: Dp,
) {
    PreviewLeftAlignedScreenV3(
        content = LeftAlignedScreenContentProvider().values.first(),
        bottomContentBehaviour = BottomContentBehaviour.Fixed,
        mainContentConfig = MainContentConfig(verticalPadding = 16.dp),
        bottomContentConfig = BottomContentConfig(
            verticalPadding = verticalPadding,
            backgroundColor = Backgrounds.card.toMappedColors(),
        ),
    )
}

internal class LeftAlignedScreenVerticalPaddingProvider : PreviewParameterProvider<Dp> {
    override val values = sequenceOf(spacingSingle, spacingDouble, spacingTriple)
}
