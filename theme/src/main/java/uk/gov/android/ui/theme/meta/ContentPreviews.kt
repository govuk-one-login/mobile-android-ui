package uk.gov.android.ui.theme.meta

import androidx.compose.ui.tooling.preview.Preview

/**
 * Annotate previews containing text content with [ContentPreviews] to add previews with:
 * - Welsh language
 * - Large font scale
 */
@Preview(name = "Welsh", locale = "cy")
@Preview(name = "Large font", fontScale = 2.0f)
annotation class ContentPreviews
