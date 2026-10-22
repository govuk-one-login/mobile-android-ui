package uk.gov.android.ui.patterns.leftalignedscreen.v3

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import uk.gov.android.ui.componentsv2.button.GdsIconButtonDefaults
import uk.gov.android.ui.componentsv2.topappbar.GdsTopAppBar

/**
 * Holds the top app bar slot of [LeftAlignedScreenV3].
 *
 * Mirrors [MainContentParameters] and [BottomContentParameters]: the slot is a composable that the
 * screen renders through [TopAppBarContentV2]. The screen owns a [TopAppBarScrollBehavior] and
 * passes it to the slot so the bar can react to the body scrolling; forward it to
 * [GdsTopAppBar]'s `scrollBehaviour`.
 *
 * @param topAppBar the top app bar slot. Use of [GdsTopAppBar] is recommended (optional).
 */
@OptIn(ExperimentalMaterial3Api::class)
data class TopAppBarContentParameters(
    val topAppBar: (@Composable (scrollBehaviour: TopAppBarScrollBehavior) -> Unit)? = null,
) {
    companion object {
        /**
         * Builds the opinionated [TopAppBarContentParameters] from the [LeftAlignedScreenTopAppBar]
         * data, rendering a [GdsTopAppBar] in the GDS style. Resolves the GDS default back button
         * when [LeftAlignedScreenTopAppBar.navigationButton] is null and
         * [LeftAlignedScreenTopAppBar.showDefaultNavigation] is true.
         */
        @OptIn(ExperimentalMaterial3Api::class)
        @Composable
        fun generateDefault(topAppBar: LeftAlignedScreenTopAppBar?): TopAppBarContentParameters {
            if (topAppBar == null) return TopAppBarContentParameters()

            val resolvedNavigation = topAppBar.navigationButton
                ?: GdsIconButtonDefaults.defaultBackContent()
                    .takeIf { topAppBar.showDefaultNavigation }

            return TopAppBarContentParameters(
                topAppBar = { scrollBehaviour ->
                    GdsTopAppBar(
                        title = topAppBar.title,
                        navigationButton = resolvedNavigation,
                        onClick = topAppBar.onNavigationClick,
                        actions = topAppBar.actions,
                        alignment = topAppBar.alignment,
                        scrollBehaviour = scrollBehaviour,
                    )
                },
            )
        }
    }
}
