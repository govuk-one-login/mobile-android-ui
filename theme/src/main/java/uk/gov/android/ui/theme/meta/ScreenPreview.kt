package uk.gov.android.ui.theme.meta

import androidx.compose.ui.tooling.preview.PreviewFontScale
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewScreenSizes

@Deprecated(
    message = "Use ScreenPreviews instead. Planned for removal on 2 October 2026.",
    replaceWith = ReplaceWith(
        "ScreenPreviews",
        "uk.gov.android.ui.theme.meta.ScreenPreviews",
    ),
)
@Suppress("ComposePreviewNaming")
@PreviewLightDark
@PreviewFontScale
@PreviewScreenSizes
annotation class ScreenPreview
