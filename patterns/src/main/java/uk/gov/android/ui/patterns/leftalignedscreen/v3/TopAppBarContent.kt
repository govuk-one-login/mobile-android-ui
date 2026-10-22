package uk.gov.android.ui.patterns.leftalignedscreen.v3

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import uk.gov.android.ui.componentsv2.topappbar.GdsTopAppBar
import uk.gov.android.ui.componentsv2.topappbar.GdsTopAppBarDefaults
import uk.gov.android.ui.componentsv2.topappbar.TopAppBarAlignment
import uk.gov.android.ui.theme.m3.GdsTheme
import uk.gov.android.ui.theme.meta.ExcludeFromJacocoGeneratedReport

/**
 * The top app bar of [LeftAlignedScreenV3].
 *
 * Mirrors [MainContentV2] and [BottomContentV2]: it renders the [TopAppBarContentParameters] slot,
 * forwarding the screen-owned [TopAppBarScrollBehavior] so the bar can react to the body scrolling.
 *
 * @param parameters the top app bar slot.
 * @param scrollBehaviour the [TopAppBarScrollBehavior] owned by the screen, forwarded to the slot
 * (and from there to [GdsTopAppBar]) so the bar reacts to the body scrolling.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun TopAppBarContentV2(
    parameters: TopAppBarContentParameters,
    scrollBehaviour: TopAppBarScrollBehavior,
) {
    parameters.topAppBar?.invoke(scrollBehaviour)
}

@OptIn(ExperimentalMaterial3Api::class)
@Immutable
internal class TopAppBarContentParametersProvider :
    PreviewParameterProvider<TopAppBarContentParameters> {
    val onlyTitle = TopAppBarContentParameters(
        topAppBar = { scrollBehaviour ->
            GdsTopAppBar(title = TITLE, scrollBehaviour = scrollBehaviour)
        },
    )
    val noNavigation = TopAppBarContentParameters(
        topAppBar = { scrollBehaviour ->
            GdsTopAppBar(
                title = TITLE,
                navigationButton = null,
                scrollBehaviour = scrollBehaviour,
            )
        },
    )
    val centreAligned = TopAppBarContentParameters(
        topAppBar = { scrollBehaviour ->
            GdsTopAppBar(
                title = TITLE,
                alignment = TopAppBarAlignment.Centre,
                scrollBehaviour = scrollBehaviour,
            )
        },
    )

    override val values: Sequence<TopAppBarContentParameters> =
        sequenceOf(
            onlyTitle,
            noNavigation,
            centreAligned,
        )

    companion object {
        const val TITLE = "Top app bar"
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@ExcludeFromJacocoGeneratedReport
@PreviewLightDark
@Composable
private fun PreviewLightDarkTopAppBarContent() {
    GdsTheme {
        TopAppBarContentV2(
            parameters = TopAppBarContentParametersProvider().onlyTitle,
            scrollBehaviour = GdsTopAppBarDefaults.scrollBehaviour(),
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@ExcludeFromJacocoGeneratedReport
@Preview
@Composable
private fun PreviewTopAppBarContent(
    @PreviewParameter(TopAppBarContentParametersProvider::class)
    content: TopAppBarContentParameters,
) {
    GdsTheme {
        TopAppBarContentV2(
            parameters = content,
            scrollBehaviour = GdsTopAppBarDefaults.scrollBehaviour(),
        )
    }
}
