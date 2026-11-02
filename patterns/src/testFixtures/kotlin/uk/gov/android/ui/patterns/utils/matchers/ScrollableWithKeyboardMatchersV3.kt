package uk.gov.android.ui.patterns.utils.matchers

import androidx.compose.ui.test.SemanticsMatcher
import uk.gov.android.ui.patterns.utils.ModifierExtensionsV3.HasKeyboardScrollKey
import uk.gov.android.ui.patterns.utils.ModifierExtensionsV3.keyboardScroll

object ScrollableWithKeyboardMatchersV3 {
    /**
     * Matches nodes that can be scrolled using the keyboard up/down arrows.
     *
     * @see [keyboardScroll].
     */
    fun hasKeyboardScroll(): SemanticsMatcher =
        SemanticsMatcher.expectValue(HasKeyboardScrollKey, true)
}
