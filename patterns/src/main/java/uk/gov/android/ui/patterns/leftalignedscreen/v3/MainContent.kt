package uk.gov.android.ui.patterns.leftalignedscreen.v3

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
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
import uk.gov.android.ui.patterns.utils.clearListSemanticsForTalkBack
import uk.gov.android.ui.theme.m3.ExtraTypography
import uk.gov.android.ui.theme.m3.GdsTheme
import uk.gov.android.ui.theme.meta.ExcludeFromJacocoGeneratedReport
import uk.gov.android.ui.theme.spacingTriple

private const val AS_MUCH_AS_POSSIBLE = 1f

/**
 * MainContent to be used with the LeftAlignedScreen.
 *
 * The padding has been updated to match the Figma designs; a standardised 16.dp between components.
 * Paddings are configurable by using the modifiers for each individual composable block.
 *
 * @param parameters [MainContentParameters] the composables to be arranged and configured.
 * @param scrollState expose the [LazyListState] to allow for legacy compatibility.
 * @param bottomContent The [Composable] to be used as the last element when it is too large to
 * appear by itself as a sticky footer (optional).
 */
@Composable
internal fun ColumnScope.MainContentV2(
    parameters: MainContentParameters,
    scrollState: LazyListState = rememberLazyListState(),
    bottomContent: @Composable (() -> Unit)? = null,
) {
    with(parameters) {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(verticalPadding),
            state = scrollState,
            modifier = modifier
                .weight(AS_MUCH_AS_POSSIBLE)
                .fillMaxWidth()
                .testTag(BODY_LAZY_COLUMN_TEST_TAG)
                .clearListSemanticsForTalkBack(),
            contentPadding = PaddingValues(vertical = verticalPadding),
        ) {
            titleContent?.let { title -> item { title(horizontalPadding) } }
            bodyContent?.let { body -> body(horizontalPadding) }
            bottomContent?.let { bottom -> item { bottom() } }
        }
    }
}

@Immutable
internal class MainContentParametersProvider : PreviewParameterProvider<MainContentParameters> {
    val onlyTitleContent = MainContentParameters(
        titleContent = ::Heading,
    )

    val onlyBodyContent = MainContentParameters(
        titleContent = null,
        bodyContent = { horizontalPadding ->
            simpleBody().forEach {
                item {
                    it.GenerateDefault(horizontalPadding)
                }
            }
        },
    )

    val allStandardParams = MainContentParameters(
        titleContent = ::Heading,
        bodyContent = { horizontalPadding ->
            simpleBody().forEach {
                item {
                    it.GenerateDefault(horizontalPadding)
                }
            }
        },
    )

    val allComplexParams = MainContentParameters(
        titleContent = ::Heading,
        bodyContent = { horizontalPadding ->
            complexBody().forEach {
                item {
                    it.GenerateDefault(horizontalPadding)
                }
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
                MainContentV2(MainContentParametersProvider().allStandardParams)
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
                MainContentV2(MainContentParametersProvider().allComplexParams)
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
    GdsTheme { Surface { Column { MainContentV2(content) } } }
}
