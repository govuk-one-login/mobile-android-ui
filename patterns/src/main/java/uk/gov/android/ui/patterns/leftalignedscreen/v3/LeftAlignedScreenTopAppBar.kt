package uk.gov.android.ui.patterns.leftalignedscreen.v3

import kotlinx.collections.immutable.ImmutableList
import uk.gov.android.ui.componentsv2.button.IconButtonContent
import uk.gov.android.ui.componentsv2.topappbar.GdsTopAppBar
import uk.gov.android.ui.componentsv2.topappbar.TopAppBarAlignment
import uk.gov.android.ui.componentsv2.topappbar.TopBarActionButton

/**
 * Describes the top app bar of [LeftAlignedScreenV3] for the simple-data entry point.
 *
 * This is the opinionated counterpart to the slot-based `topAppBar` slot: pass plain data and the
 * screen builds a [GdsTopAppBar] for you in the GDS style (see [TopAppBarContentV3]). For full
 * control over the composable, use the slot-based entry point instead.
 *
 * @param title the bar title (optional).
 * @param alignment whether the title is start- or centre-aligned; defaults to
 * [TopAppBarAlignment.Start] (optional).
 * @param navigationButton the navigation icon content shown on the left. When null and
 * [showDefaultNavigation] is true the GDS default back button is used; set [showDefaultNavigation]
 * to false to omit the navigation button entirely (optional).
 * @param showDefaultNavigation whether to fall back to the GDS default back button when
 * [navigationButton] is null; defaults to true (optional).
 * @param onNavigationClick invoked when the navigation icon is clicked (optional).
 * @param actions trailing action icon buttons shown on the end of the bar (optional).
 */
data class LeftAlignedScreenTopAppBar(
    val title: String? = null,
    val alignment: TopAppBarAlignment = TopAppBarAlignment.Start,
    val navigationButton: IconButtonContent? = null,
    val showDefaultNavigation: Boolean = true,
    val onNavigationClick: () -> Unit = {},
    val actions: ImmutableList<TopBarActionButton>? = null,
)
