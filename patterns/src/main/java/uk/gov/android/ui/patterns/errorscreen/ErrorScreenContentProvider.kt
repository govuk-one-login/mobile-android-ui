package uk.gov.android.ui.patterns.errorscreen

import androidx.compose.ui.tooling.preview.PreviewParameterProvider

internal class ErrorScreenContentProvider :
    PreviewParameterProvider<ErrorScreenContent> {
    private val errorTitle = "This is an Error View title"
    private val warningTitle = "This is an Warning View title"

    override val values: Sequence<ErrorScreenContent> = sequenceOf(
        ErrorScreenContent(
            configurationDescription = """
                Content 0 - Basic Error Screen with only an icon and title
            """.trimIndent(),
            title = errorTitle,
            icon = ErrorScreenIcon.ErrorIcon,
        ),
        ErrorScreenContent(
            configurationDescription = """
                Content 1 - Basic Warning Screen with only an icon and title
            """.trimIndent(),
            title = warningTitle,
            icon = ErrorScreenIcon.WarningIcon,
        ),
    )
}
