package uk.gov.android.ui.patterns.leftalignedscreen.v3

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewFontScale
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.Dp
import kotlinx.collections.immutable.persistentListOf
import uk.gov.android.ui.componentsv2.heading.GdsHeading
import uk.gov.android.ui.componentsv2.heading.GdsHeadingAlignment
import uk.gov.android.ui.componentsv2.heading.GdsHeadingStyle
import uk.gov.android.ui.componentsv2.list.ListItem
import uk.gov.android.ui.patterns.R
import uk.gov.android.ui.patterns.leftalignedscreen.v3.LeftAlignedScreenTestTag.BODY_LAZY_COLUMN_TEST_TAG
import uk.gov.android.ui.patterns.leftalignedscreen.v3.MainContentParameters.Companion.GenerateDefault
import uk.gov.android.ui.patterns.utils.ModifierExtensions.keyboardScroll
import uk.gov.android.ui.theme.m3.ExtraTypography
import uk.gov.android.ui.theme.m3.GdsTheme
import uk.gov.android.ui.theme.meta.ExcludeFromJacocoGeneratedReport
import uk.gov.android.ui.theme.spacingTriple

private const val LAYOUT_WEIGHT_MAX = 1f

/**
 * The scrollable main content (title and body) of [LeftAlignedScreen], laid out in a scrollable
 * [Column].
 *
 * Using a regular [Column] with [verticalScroll] (rather than a [LazyColumn]) ensures all items
 * are always composed and present in the node tree. This is essential for keyboard accessibility:
 * Tab/Shift+Tab traversal and arrow-key scrolling work natively without any interception hacks,
 * because every focusable node (including buttons that may be off-screen) is always reachable by
 * the focus system.
 *
 * Spacing follows the Figma design (a standard 16.dp between components) and can be overridden per
 * element through the modifiers on each slot.
 *
 * @param titleContent the title slot, rendered as the first item in the column (optional).
 * @param bodyContent the body slot, a [ColumnScope] lambda contributing items (optional).
 * @param bottomContent appended as the final item when the bottom content is too large to remain a
 * sticky footer and must scroll with the body instead (optional).
 */
@Composable
internal fun ColumnScope.MainContent(
    titleContent: (@Composable (horizontalPadding: Dp) -> Unit)?,
    bodyContent: (@Composable ColumnScope.(horizontalItemPadding: Dp) -> Unit)?,
    config: MainContentConfig = MainContentConfig(),
    bottomContent: @Composable (() -> Unit)? = null,
) {
    val scrollState = rememberScrollState()
    with(config) {
        Column(
            verticalArrangement = Arrangement.spacedBy(verticalPadding),
            modifier = modifier
                .weight(LAYOUT_WEIGHT_MAX)
                .fillMaxWidth()
                .testTag(BODY_LAZY_COLUMN_TEST_TAG)
                .keyboardScroll(scrollState)
                .verticalScroll(scrollState)
                .padding(vertical = verticalPadding),
        ) {
            titleContent?.invoke(horizontalPadding)
            bodyContent?.invoke(this, horizontalPadding)
            bottomContent?.invoke()
        }
    }
}

/**
 * [MainContentParameters] convenience overload of [MainContent], kept for previews, tests and
 * demos.
 */
@Composable
internal fun ColumnScope.MainContent(
    parameters: MainContentParameters,
    config: MainContentConfig = MainContentConfig(),
    bottomContent: @Composable (() -> Unit)? = null,
) = MainContent(
    titleContent = parameters.titleContent,
    bodyContent = parameters.bodyContent,
    config = config,
    bottomContent = bottomContent,
)

@Immutable
internal class MainContentParametersProvider : PreviewParameterProvider<MainContentParameters> {
    val onlyTitleContent = MainContentParameters(
        titleContent = ::Heading,
    )

    val onlyBodyContent = MainContentParameters(
        titleContent = null,
        bodyContent = { horizontalPadding ->
            simpleBody().forEach {
                it.GenerateDefault(horizontalPadding)
            }
        },
    )

    val allStandardParams = MainContentParameters(
        titleContent = ::Heading,
        bodyContent = { horizontalPadding ->
            simpleBody().forEach {
                it.GenerateDefault(horizontalPadding)
            }
        },
    )

    val allComplexParams = MainContentParameters(
        titleContent = ::Heading,
        bodyContent = { horizontalPadding ->
            complexBody().forEach {
                it.GenerateDefault(horizontalPadding)
            }
        },
    )

    override val values: Sequence<MainContentParameters> =
        sequenceOf(
            onlyTitleContent,
            onlyBodyContent,
            allStandardParams,
            allComplexParams,
        )

    @Composable
    private fun Heading(horizontalPadding: Dp) = GdsHeading(
        text = TITLE_TEXT,
        modifier = Modifier.padding(horizontal = horizontalPadding),
        textAlign = GdsHeadingAlignment.LeftAligned,
    )

    internal fun simpleBody() = persistentListOf(
        LeftAlignedScreenBody.Image(
            R.drawable.preview__gdsvectorimage,
            IMAGE_DESCRIPTION,
            Modifier
                .fillMaxWidth()
                .background(Color.Gray),
        ),
        LeftAlignedScreenBody.Text(
            BODY_TEXT,
            modifier = Modifier.padding(
                top = spacingTriple,
            ),
        ),
    )

    internal fun complexBody() = persistentListOf(
        LeftAlignedScreenBody.Text(
            BODY_TEXT,
            modifier = Modifier.padding(
                top = spacingTriple,
            ),
        ),
        LeftAlignedScreenBody.AnnotatedText(
            buildAnnotatedString {
                append("textShort")
                withStyle(
                    SpanStyle(fontFamily = ExtraTypography.bodyLargeBold.fontFamily),
                ) {
                    append(" 26 June 2024 at 2:56pm (UK time)")
                }
                append(". textShort")
            },
        ),
        LeftAlignedScreenBody.Title(
            text = "Title2 - Left Aligned",
            style = GdsHeadingStyle.Title2,
            textAlign = GdsHeadingAlignment.LeftAligned,
        ),
        LeftAlignedScreenBody.Title(
            text = "Title2 - Center Aligned",
            style = GdsHeadingStyle.Title2,
            textAlign = GdsHeadingAlignment.CenterAligned,
        ),
        LeftAlignedScreenBody.Title(
            text = "Title2 - Right Aligned",
            style = GdsHeadingStyle.Title2,
            textAlign = GdsHeadingAlignment.RightAligned,
        ),
        LeftAlignedScreenBody.Image(
            R.drawable.preview__gdsvectorimage,
            IMAGE_DESCRIPTION,
            Modifier
                .fillMaxWidth()
                .background(Color.Gray),
        ),
        LeftAlignedScreenBody.BulletList(
            persistentListOf(
                ListItem("Bullet 1"),
                ListItem("Bullet 2"),
                ListItem("textShort"),
            ),
        ),
        LeftAlignedScreenBody.SecondaryButton(
            "secondaryButtonText",
            {},
        ),
    )

    companion object {
        const val TITLE_TEXT = "Do you have a UK passport or passport with a biometric chip?"
        const val IMAGE_DESCRIPTION = "Image description"
        val BODY_TEXT = LoremIpsum(5).values.first()
    }
}

@ExcludeFromJacocoGeneratedReport
@PreviewLightDark
@Composable
private fun PreviewLightDarkMainContent() {
    GdsTheme {
        Surface {
            Column {
                MainContent(MainContentParametersProvider().allStandardParams)
            }
        }
    }
}

@ExcludeFromJacocoGeneratedReport
@PreviewFontScale
@Composable
private fun PreviewFontScaleMainContent() {
    GdsTheme {
        Surface {
            Column {
                MainContent(MainContentParametersProvider().allComplexParams)
            }
        }
    }
}

@ExcludeFromJacocoGeneratedReport
@Preview
@Composable
private fun PreviewMainContent(
    @PreviewParameter(MainContentParametersProvider::class)
    content: MainContentParameters,
) {
    GdsTheme { Surface { Column { MainContent(content) } } }
}
