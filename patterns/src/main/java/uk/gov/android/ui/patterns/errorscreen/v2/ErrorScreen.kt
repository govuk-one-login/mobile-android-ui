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
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.Dp
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

/**
 * Renders a centre-aligned error screen with a structured layout.
 *
 * This screen is designed for displaying an warning/error icon, title, body content,
 * and bottom content with primary/secondary buttons in a visually consistent manner.
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
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(
            VerticalPadding,
            Alignment.CenterVertically,
        ),
        modifier = modifier,
    ) {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(
                VerticalPadding,
                Alignment.CenterVertically,
            ),
            modifier = Modifier
                .fillMaxSize()
                .weight(1f)
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
                    icon(HorizontalPadding)

                    Spacer(
                        modifier = Modifier
                            .height(VerticalPadding),
                    )

                    title(HorizontalPadding)
                }
            }

            body?.let {
                it(HorizontalPadding)
            }
        }

        Column(
            verticalArrangement = Arrangement.spacedBy(
                VerticalPadding,
                Alignment.CenterVertically,
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = HorizontalPadding,
                    end = HorizontalPadding,
                    bottom = VerticalPadding,
                ),
        ) {
            primaryButton?.invoke()

            secondaryButton?.invoke()

            tertiaryButton?.invoke()
        }
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

@OptIn(UnstableDesignSystemAPI::class)
@ExcludeFromJacocoGeneratedReport
@PreviewLightDark
@Preview(showBackground = true)
@Composable
internal fun PreviewErrorScreen(
    @PreviewParameter(ErrorScreenContentProvider::class)
    content: ErrorScreenContent,
) {
    GdsTheme {
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
            primaryButton = {
                content.primaryButton?.let {
                    PrimaryButton(it)
                }
            },
            secondaryButton = {
                content.secondaryButton?.let {
                    SecondaryButton(it)
                }
            },
            tertiaryButton = {
                content.tertiaryButton?.let {
                    SecondaryButton(it)
                }
            },
        )
    }
}

@OptIn(UnstableDesignSystemAPI::class)
@ExcludeFromJacocoGeneratedReport
@PreviewLightDark
@Preview(showBackground = true, fontScale = 1.5f)
@Composable
internal fun PreviewErrorScreenAccessibility(
    @PreviewParameter(ErrorScreenContentProvider::class)
    content: ErrorScreenContent,
) {
    GdsTheme {
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
            primaryButton = {
                content.primaryButton?.let {
                    PrimaryButton(it)
                }
            },
            secondaryButton = {
                content.secondaryButton?.let {
                    SecondaryButton(it)
                }
            },
            tertiaryButton = {
                content.tertiaryButton?.let {
                    SecondaryButton(it)
                }
            },
        )
    }
}
