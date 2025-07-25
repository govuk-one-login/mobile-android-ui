package uk.gov.android.ui.patterns.errorscreen.v2

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.SubcomposeLayout
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import uk.gov.android.ui.componentsv2.button.GdsButton
import uk.gov.android.ui.componentsv2.heading.GdsHeading
import uk.gov.android.ui.componentsv2.heading.GdsHeadingAlignment
import uk.gov.android.ui.componentsv2.images.GdsIcon
import uk.gov.android.ui.patterns.errorscreen.ErrorScreenTitleTestTag.ERROR_SCREEN_TITLE_TEST_TAG
import uk.gov.android.ui.patterns.errorscreen.v2.ErrorScreenDefaults.HorizontalPadding
import uk.gov.android.ui.patterns.errorscreen.v2.ErrorScreenDefaults.VerticalPadding
import uk.gov.android.ui.patterns.errorscreen.v2.ErrorScreenTitleTestTag.ERROR_BODY_LAZY_COLUMN_TEST_TAG
import uk.gov.android.ui.theme.m3.GdsTheme
import uk.gov.android.ui.theme.meta.ExcludeFromJacocoGeneratedReport
import uk.gov.android.ui.theme.spacingDouble
import uk.gov.android.ui.theme.util.UnstableDesignSystemAPI

private const val ONE_THIRD = 1f / 3f
private const val DENSITY_PREVIEW_INDEX = 5

/**
 * Renders a centre-aligned error screen with a structured layout.
 *
 * This screen is designed for displaying an warning/error icon, title, body content,
 * and bottom content with primary/secondary buttons in a visually consistent manner.
 *
 * When the bottom content takes up more than 1/3 of the screen, it is moved into the body.
 *
 * @param icon image displayed at the top of the screen. [GdsIcon] is recommended.
 * @param title represents the main title. Use of [GdsHeading] is recommended.
 * @param modifier A [Modifier] to be applied to the root layout of the screen (optional).
 * @sample LazyListScope.toBodyContent
 * @param body list of items representing the main content (optional).
 * @param primaryButton primary action button. Use of [GdsButton] composable is recommended (optional).
 * @param secondaryButton secondary action button. Use of [GdsButton] composable is recommended (optional).
 * @param tertiaryButton tertiary action button. Use of [GdsButton] composable is recommended (optional).
 */

@Suppress("LongMethod")
@Composable
fun ErrorScreen(
    icon: @Composable ((horizontalPadding: Dp) -> Unit),
    title: @Composable (horizontalPadding: Dp) -> Unit,
    modifier: Modifier = Modifier,
    body: (LazyListScope.(horizontalItemPadding: Dp) -> Unit)? = null,
    primaryButton: (@Composable () -> Unit)? = null,
    secondaryButton: (@Composable () -> Unit)? = null,
    tertiaryButton: (@Composable () -> Unit)? = null,
) {
    val screenHeight = LocalConfiguration.current.screenHeightDp.dp
    val thresholdHeight = screenHeight * ONE_THIRD
    val density = LocalDensity.current

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier,
    ) {
        val verticalPaddingRequired = primaryButton != null ||
            secondaryButton != null ||
            tertiaryButton != null

        /* Measures the height of SupportingTextContainer plus the BottomContent.
        If the height is over 1/3 of the total screen, the BottomContent is moved
        into the MainContent which is scrollable */
        SubcomposeLayout { constraints ->
            // Draw the BottomContent to enable checking it's height
            val bottomPlaceables = subcompose("bottom") {
                BottomContent(
                    verticalPaddingRequired = verticalPaddingRequired,
                    primaryButton = primaryButton,
                    secondaryButton = secondaryButton,
                    tertiaryButton = tertiaryButton,
                )
            }.map { it.measure(constraints) }
            val bottomContentHeight = bottomPlaceables.maxOfOrNull { it.height } ?: 0

            // Check if BottomContent exceeds 1/3 of the screen threshold
            val bottomContentOverThreshold =
                with(density) { bottomContentHeight.toDp() } > thresholdHeight

            // Measure MainContent and add BottomContent to MainContent if above threshold
            val mainPlaceables = subcompose("content") {
                MainContent(
                    modifier = Modifier.weight(1f),
                    icon = icon,
                    title = title,
                    body = body,
                    bottomContent = {
                        // Based on the height calculated above, display the BottomContent as part
                        // of the MainContent
                        if (bottomContentOverThreshold) {
                            BottomContent(
                                verticalPaddingRequired = false,
                                primaryButton = primaryButton,
                                secondaryButton = secondaryButton,
                                tertiaryButton = tertiaryButton,
                            )
                        }
                    },
                )
            }.map {
                // Check the height of the BottomContent and how much it should take to display the
                // MainContent properly
                val appliedBottomContentAnchored =
                    if (!bottomContentOverThreshold) bottomContentHeight else 0
                it.measure(
                    // Calculate the MainContent display widow/ section
                    constraints.copy(
                        maxHeight = constraints.maxHeight - appliedBottomContentAnchored,
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

                // This will only be displayed is the BottomContent is less than 1/3 if the screen
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
private fun MainContent(
    modifier: Modifier = Modifier,
    icon: (@Composable (horizontalPadding: Dp) -> Unit)? = null,
    title: (@Composable (horizontalPadding: Dp) -> Unit)? = null,
    body: (LazyListScope.(horizontalItemPadding: Dp) -> Unit)? = null,
    bottomContent: (@Composable () -> Unit)? = null,
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(
            VerticalPadding,
            Alignment.CenterVertically,
        ),
        modifier = modifier
            .fillMaxSize()
            .testTag(ERROR_BODY_LAZY_COLUMN_TEST_TAG),
    ) {
        item {
            Column(
                modifier = Modifier
                    .testTag(ERROR_SCREEN_TITLE_TEST_TAG)
                    .semantics(mergeDescendants = true) {
                        heading()
                    },
            ) {
                icon?.invoke(HorizontalPadding)

                Spacer(
                    modifier = Modifier
                        .height(VerticalPadding),
                )

                title?.invoke(HorizontalPadding)
            }
        }

        body?.let {
            it(HorizontalPadding)
        }

        bottomContent?.let {
            item { it.invoke() }
        }
    }
}

@Composable
private fun BottomContent(
    verticalPaddingRequired: Boolean,
    primaryButton: @Composable (() -> Unit)?,
    secondaryButton: @Composable (() -> Unit)?,
    tertiaryButton: @Composable (() -> Unit)?,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(
            VerticalPadding,
            Alignment.CenterVertically,
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = HorizontalPadding,
                vertical = if (verticalPaddingRequired) VerticalPadding else 0.dp,
            ),
    ) {
        primaryButton?.invoke()

        secondaryButton?.invoke()

        tertiaryButton?.invoke()
    }
}

object ErrorScreenDefaults {
    val HorizontalPadding: Dp = spacingDouble
    val VerticalPadding: Dp = spacingDouble
}

internal object ErrorScreenTitleTestTag {
    const val ERROR_SCREEN_TITLE_TEST_TAG = "ERROR_SCREEN_TITLE_TEST_TAG"
    const val ERROR_BODY_LAZY_COLUMN_TEST_TAG = "ERROR_BODY_LAZY_COLUMN_TEST_TAG"
}

@ExcludeFromJacocoGeneratedReport
@PreviewLightDark
@Preview(showBackground = true)
@Composable
internal fun PreviewErrorScreen(
    @PreviewParameter(ErrorScreenContentProvider::class)
    content: ErrorScreenContent,
) {
    GdsTheme {
        ErrorScreenPreviewComposable(content)
    }
}

@ExcludeFromJacocoGeneratedReport
@PreviewLightDark
@Preview(showBackground = true, fontScale = 1.5f)
@Composable
internal fun PreviewErrorScreenAccessibility(
    @PreviewParameter(ErrorScreenContentProvider::class)
    content: ErrorScreenContent,
) {
    GdsTheme {
        ErrorScreenPreviewComposable(content)
    }
}

@ExcludeFromJacocoGeneratedReport
@PreviewLightDark
@Preview(showBackground = true, fontScale = 2.5f)
@Composable
internal fun PreviewErrorScreenHighDensity() {
    GdsTheme {
        ErrorScreenPreviewComposable(
            ErrorScreenContentProvider().values.elementAt(DENSITY_PREVIEW_INDEX),
        )
    }
}

@OptIn(UnstableDesignSystemAPI::class)
@Composable
internal fun ErrorScreenPreviewComposable(
    @PreviewParameter(ErrorScreenContentProvider::class)
    content: ErrorScreenContent,
) {
    ErrorScreen(
        icon = { horizontalPadding ->
            GdsIcon(
                image = ImageVector.vectorResource(content.icon.icon),
                contentDescription = stringResource(content.icon.description),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = horizontalPadding),
                color = colorScheme.onBackground,
            )
        },
        title = { horizontalPadding ->
            GdsHeading(
                text = content.title,
                modifier = Modifier
                    .padding(horizontal = horizontalPadding),
                textAlign = GdsHeadingAlignment.CenterAligned,
            )
        },
        body = { horizontalPadding ->
            toBodyContent(content.body, horizontalPadding)
        },
        primaryButton =
        content.primaryButton?.let {
            { PrimaryButton(it) }
        },
        secondaryButton =
        content.secondaryButton?.let {
            { SecondaryButton(it) }
        },
        tertiaryButton =
        content.tertiaryButton?.let {
            { SecondaryButton(it) }
        },
    )
}
