package uk.gov.android.ui.patterns.leftalignedscreen.v3

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewFontScale
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.Dp
import kotlinx.collections.immutable.toImmutableList
import uk.gov.android.ui.componentsv2.heading.GdsHeading
import uk.gov.android.ui.componentsv2.heading.GdsHeadingAlignment
import uk.gov.android.ui.patterns.leftalignedscreen.LeftAlignedScreenTestTag.BODY_LAZY_COLUMN_TEST_TAG
import uk.gov.android.ui.patterns.leftalignedscreen.v3.MainContentParameters.Companion.GenerateDefault
import uk.gov.android.ui.patterns.utils.ModifierExtensions.keyboardScroll
import uk.gov.android.ui.patterns.utils.clearListSemanticsForTalkBack
import uk.gov.android.ui.theme.m3.GdsTheme
import uk.gov.android.ui.theme.meta.ExcludeFromJacocoGeneratedReport

/**
 * BottomContent to be used with the LeftAlignedScreen.
 *
 * The padding has been updated to match the Figma designs; a standardised 16.dp between components.
 * Paddings are configurable by using the modifiers for each individual composable block.
 *
 * @param parameters [MainContentParameters] the composables to be arranged and configured.
 * @param modifier A [Modifier] to be applied to the root layout of the screen (optional).
 * @param isSticky A [Boolean] flag for if this is used as a sticky footer.
 * Determines the top padding (optional).
 */
@Composable
internal fun MainContentV2(
    parameters: MainContentParameters,
    forceScroll: Boolean = false,
    bottomContent: @Composable (() -> Unit)? = null,
) {
    with(parameters) {
        val scrollState: LazyListState = rememberLazyListState()
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(verticalPadding),
            modifier = Modifier
                .fillMaxSize()
                .also { mod ->
                    if (forceScroll) {
                        mod.keyboardScroll(scrollState)
                    }
                }
                .testTag(BODY_LAZY_COLUMN_TEST_TAG)
                .clearListSemanticsForTalkBack(),
            state = scrollState,
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
            LeftAlignedScreenContentProvider().values.first().body?.forEach {
                item {
                    it.GenerateDefault(horizontalPadding)
                }
            }
        },
    )

    val allStandardParams = MainContentParameters(
        titleContent = ::Heading,
        bodyContent = { horizontalPadding ->
            LeftAlignedScreenContentProvider().values.toImmutableList()[1].body?.forEach {
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
        )

    @Composable
    private fun Heading(horizontalPadding: Dp) = GdsHeading(
        text = TITLE_TEXT,
        modifier = Modifier.padding(horizontal = horizontalPadding),
        textAlign = GdsHeadingAlignment.LeftAligned,
    )

    companion object {
        const val TITLE_TEXT = "Do you have a UK passport or passport with a biometric chip?"
    }
}

@ExcludeFromJacocoGeneratedReport
@PreviewLightDark
@Composable
private fun PreviewLightDarkMainContent() {
    GdsTheme {
        MainContentV2(MainContentParametersProvider().allStandardParams)
    }
}

@ExcludeFromJacocoGeneratedReport
@PreviewFontScale
@Composable
private fun PreviewFontScaleMainContent() {
    GdsTheme {
        MainContentV2(MainContentParametersProvider().allStandardParams)
    }
}

@ExcludeFromJacocoGeneratedReport
@Preview
@Composable
private fun PreviewMainContent(
    @PreviewParameter(MainContentParametersProvider::class)
    content: MainContentParameters,
) {
    GdsTheme { Surface { MainContentV2(content) } }
}
