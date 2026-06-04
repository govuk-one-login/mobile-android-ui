package uk.gov.android.ui.patterns.utils.matchers

import androidx.compose.ui.test.SemanticsMatcher
import uk.gov.android.ui.patterns.utils.ModifierExtensions.HasKeyboardScrollKey
import uk.gov.android.ui.patterns.utils.ModifierExtensions.keyboardScroll

object ScrollableWithKeyboardMatchers {
    /**
     * Matches nodes that can be scrolled using the keyboard up/down arrows.
     *
     * @see [keyboardScroll].
     */
    fun hasKeyboardScroll(): SemanticsMatcher =
        SemanticsMatcher.expectValue(HasKeyboardScrollKey, true)
}
