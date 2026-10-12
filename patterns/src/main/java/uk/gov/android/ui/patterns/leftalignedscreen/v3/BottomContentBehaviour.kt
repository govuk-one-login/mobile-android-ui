package uk.gov.android.ui.patterns.leftalignedscreen.v3

/**
 * Enum for determining the behaviour of the [BottomContent] composable in the [LeftAlignedScreen].
 *
 * The LeftAlignedScreen displays a main content in a scrollable container and a bottom content.
 * The bottom content can be fixed or adaptive. When fixed the bottom content remains at the bottom
 * of the screen no matter what, whereas when adaptive if the bottom content takes up more than 1/3
 * of the screen, it is moved into the main scrolling body.
 */
enum class BottomContentBehaviour {
    Fixed,
    Adaptive,
}
