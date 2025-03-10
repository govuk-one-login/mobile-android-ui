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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.SubcomposeLayout
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import kotlinx.collections.immutable.ImmutableList
import uk.gov.android.ui.componentsv2.button.ButtonType
import uk.gov.android.ui.componentsv2.button.GdsButton
import uk.gov.android.ui.componentsv2.supportingtext.GdsSupportingText
import uk.gov.android.ui.componentsv2.title.GdsTitle
import uk.gov.android.ui.theme.m3.GdsTheme
import uk.gov.android.ui.theme.spacingDouble

private const val ONE_THIRD = 1f / 3f
private const val FONT_SCALE_DOUBLE = 2f
private const val ACCESSIBILITY_TEST_ELEMENT = 7

/**
 * Left Aligned Screen
 *
 * This pattern displays the main content which is placed in a scrollable container.
 * The bottom content (supporting text, primary and secondary button) is fixed.
 * When the bottom content takes up more than 1/3 of the screen, the supporting text is moved into the body
 *
 * @param modifier A [Modifier] to be applied to the root layout of the screen (optional).
 * @param body representing the main content.
 * @param supportingText additional text displayed below in the bottom content. Use of [GdsSupportingText] composable is recommended (optional).
 * @param primaryButton primary action button. Use of [GdsButton] composable is recommended (optional).
 * @param secondaryButton secondary action button. Use of [GdsButton] composable is recommended (optional).
 */
@Suppress("LongMethod")
@Composable
fun LeftAlignedScreen(
    body: LazyListScope.() -> Unit,
    modifier: Modifier = Modifier,
    supportingText: (@Composable () -> Unit)? = null,
    primaryButton: (@Composable () -> Unit)? = null,
    secondaryButton: (@Composable () -> Unit)? = null,
    arrangement: Arrangement.Vertical = Arrangement.spacedBy(
        spacingDouble,
        Alignment.CenterVertically,
    ),
) {
    val screenHeight = LocalConfiguration.current.screenHeightDp.dp
    val thresholdHeight = screenHeight * ONE_THIRD
    val density = LocalDensity.current

    Column(
        modifier,
        verticalArrangement = arrangement,
    ) {
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
                    ) { supportingText.invoke() }
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
                    body = body,
                    supportingText = if (bottomContentOverThreshold) {
                        supportingText
                    } else {
                        null
                    },
                    arrangement = arrangement,
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
        body = {
            item { GdsTitle(title) }
            toBodyContent(body)
        },
        supportingText = supportingText?.let {
            { GdsSupportingText(it) }
        },
        primaryButton = primaryButton?.let {
            {
                GdsButton(
                    text = it.text,
                    onClick = it.onClick,
                    buttonType = ButtonType.Primary,
                )
            }
        },
        secondaryButton = secondaryButton?.let {
            {
                GdsButton(
                    text = it.text,
                    onClick = it.onClick,
                    buttonType = ButtonType.Secondary,
                )
            }
        },
    )
}

@Composable
private fun MainContent(
    body: LazyListScope.() -> Unit,
    modifier: Modifier = Modifier,
    arrangement: Arrangement.Vertical = Arrangement.spacedBy(spacingDouble),
    @SuppressLint("ComposableLambdaParameterNaming")
    supportingText: (@Composable () -> Unit)? = null,
) {
    LazyColumn(
        verticalArrangement = arrangement,
        modifier = modifier.fillMaxSize(),
    ) {
        body()

        supportingText?.let {
            item { it.invoke() }
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
        modifier.padding(horizontal = spacingDouble),
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
internal fun PreviewLeftAlignedScreenAccessibility() {
    val content = LeftAlignedScreenContentProvider().values.elementAt(ACCESSIBILITY_TEST_ELEMENT)
    GdsTheme {
        LeftAlignedScreenFromContentParams(content)
    }
}
