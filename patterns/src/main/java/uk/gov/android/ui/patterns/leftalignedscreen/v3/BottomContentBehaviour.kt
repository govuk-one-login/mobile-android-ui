package uk.gov.android.ui.patterns.leftalignedscreen.v3

/**
 * Controls how the bottom content (supporting text and buttons) is positioned in
 * [LeftAlignedScreenV3].
 *
 * The screen shows its main content in a scrollable container with the bottom content beneath it.
 * This enum decides whether that bottom content is pinned to the bottom of the screen or allowed to
 * scroll with the main content when it grows too large.
 */
enum class BottomContentBehaviour {
    /** The bottom content is always pinned to the bottom of the screen as a sticky footer. */
    Fixed,

    /**
     * The bottom content is a sticky footer while it fits, but once it exceeds one third of the
     * screen height it is moved into the scrollable main content so the screen stays usable.
     */
    Adaptive,
}
