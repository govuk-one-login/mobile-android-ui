package uk.gov.android.ui.utils.extensions

import androidx.compose.ui.semantics.SemanticsActions
import androidx.compose.ui.semantics.getOrNull
import androidx.compose.ui.test.SemanticsNodeInteraction
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.unit.TextUnit

fun SemanticsNodeInteraction.assertFontSize(
    fontSize: TextUnit,
): SemanticsNodeInteraction {
    if (!checkFontSize(fontSize)) {
        throw AssertionError("Assert failed: The component's font size is incorrect!")
    }
    return this
}

internal fun SemanticsNodeInteraction.checkFontSize(
    fontSize: TextUnit,
): Boolean {
    val errorMessageOnFail = "Failed to perform fontSize check."
    val node = fetchSemanticsNode(errorMessageOnFail)
    val textLayoutResults = mutableListOf<TextLayoutResult>()

    node.config.getOrNull(SemanticsActions.GetTextLayoutResult)?.action?.invoke(textLayoutResults)

    return if (textLayoutResults.isEmpty()) {
        false
    } else {
        textLayoutResults.first().layoutInput.style.fontSize == fontSize
    }
}

fun SemanticsNodeInteraction.assertLineHeight(
    lineHeight: TextUnit,
): SemanticsNodeInteraction {
    if (!checkLineHeight(lineHeight)) {
        throw AssertionError("Assert failed: The component's line height is incorrect!")
    }
    return this
}

internal fun SemanticsNodeInteraction.checkLineHeight(
    lineHeight: TextUnit,
): Boolean {
    val errorMessageOnFail = "Failed to perform lineHeight check."
    val node = fetchSemanticsNode(errorMessageOnFail)
    val textLayoutResults = mutableListOf<TextLayoutResult>()

    node.config.getOrNull(SemanticsActions.GetTextLayoutResult)?.action?.invoke(textLayoutResults)

    return if (textLayoutResults.isEmpty()) {
        false
    } else {
        textLayoutResults.first().layoutInput.style.lineHeight == lineHeight
    }
}
