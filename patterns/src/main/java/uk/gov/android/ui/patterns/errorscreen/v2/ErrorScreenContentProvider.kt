package uk.gov.android.ui.patterns.errorscreen.v2

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import kotlinx.collections.immutable.persistentListOf
import uk.gov.android.ui.componentsv2.list.ListItem
import uk.gov.android.ui.componentsv2.list.ListTitle
import uk.gov.android.ui.componentsv2.list.TitleType

internal class ErrorScreenContentProvider :
    PreviewParameterProvider<ErrorScreenContent> {
    private val errorTitle = "This is an Error View title"
    private val warningTitle = "This is an Warning View title"
    private val bodyContentSingleLine = "Body single line (regular)"
    private val bodyContentBoldLine = "Body single line (bold)"
    private val primaryButtonText = "Primary button"
    private val secondaryButtonText = "Secondary button"
    private val bodyButtonText = "Text Button"

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
        ErrorScreenContent(
            configurationDescription = """
                Content 2 - Basic Error Screen with no buttons and left aligned Icon button
            """.trimIndent(),
            title = errorTitle,
            icon = ErrorScreenIcon.ErrorIcon,
            body = persistentListOf(
                ErrorScreenBodyContent.Text(bodyContentSingleLine),
                ErrorScreenBodyContent.Text(bodyContentBoldLine, true),
                ErrorScreenBodyContent.Text(bodyContentSingleLine),
                ErrorScreenBodyContent.Text("Body single paragraph - ${loremIpsum(18)}"),
                ErrorScreenBodyContent.BulletList(
                    title = ListTitle("This is the bullet view", TitleType.Heading),
                    items = persistentListOf(
                        ListItem("Here we can list things we want the user to know"),
                        ListItem("we can use this as a way to step them through an action"),
                        ListItem("or give details of a process"),
                    ),
                ),
                ErrorScreenBodyContent.Button(
                    text = bodyButtonText,
                    onClick = {},
                    leftAligned = true,
                    showIcon = true,
                ),
            ),
        ),
        ErrorScreenContent(
            configurationDescription = """
                Content 3 - Basic Error Screen with one button, multiple single lines and center
                            aligned secondary button
            """.trimIndent(),
            title = errorTitle,
            icon = ErrorScreenIcon.ErrorIcon,
            body = persistentListOf(
                ErrorScreenBodyContent.Text(bodyContentSingleLine),
                ErrorScreenBodyContent.Text(bodyContentSingleLine),
                ErrorScreenBodyContent.Text(bodyContentSingleLine),
                ErrorScreenBodyContent.Text(bodyContentSingleLine),
                ErrorScreenBodyContent.Button(
                    text = bodyButtonText,
                    onClick = {},
                    showIcon = true,
                ),
            ),
            primaryButton = ErrorScreenButton(
                text = primaryButtonText,
                onClick = {},
                showIcon = false,
            ),
        ),
        ErrorScreenContent(
            configurationDescription = """
                Content 4 - Basic Error Screen with two buttons, multiple paragraphs and center
                            aligned secondary button
            """.trimIndent(),
            title = errorTitle,
            icon = ErrorScreenIcon.ErrorIcon,
            body = persistentListOf(
                ErrorScreenBodyContent.Text(bodyContentSingleLine),
                ErrorScreenBodyContent.Text(bodyContentBoldLine, true),
                ErrorScreenBodyContent.Text("Body multiple paragraph - ${loremIpsum(18)}"),
                ErrorScreenBodyContent.Text("Body multiple paragraph - ${loremIpsum(3)}"),
                ErrorScreenBodyContent.Text(
                    "Body multiple paragraph - ${loremIpsum(7)}",
                    true,
                ),
                ErrorScreenBodyContent.Button(
                    text = bodyButtonText,
                    onClick = {},
                    showIcon = true,
                ),
            ),
            primaryButton = ErrorScreenButton(
                text = primaryButtonText,
                onClick = {},
                showIcon = false,
            ),
            secondaryButton = ErrorScreenButton(
                text = secondaryButtonText,
                onClick = {},
                showIcon = false,
            ),
        ),
        ErrorScreenContent(
            configurationDescription = """
                Content 5 - Basic Error Screen with three buttons
            """.trimIndent(),
            title = warningTitle,
            icon = ErrorScreenIcon.WarningIcon,
            body = persistentListOf(
                ErrorScreenBodyContent.Text(bodyContentSingleLine),
                ErrorScreenBodyContent.Text("Body single paragraph - ${loremIpsum(18)}"),
                ErrorScreenBodyContent.Button(
                    text = bodyButtonText,
                    onClick = {},
                    showIcon = true,
                ),
            ),
            primaryButton = ErrorScreenButton(
                text = primaryButtonText,
                onClick = {},
                showIcon = true,
            ),
            secondaryButton = ErrorScreenButton(
                text = secondaryButtonText,
                onClick = {},
                showIcon = true,
            ),
            tertiaryButton = ErrorScreenButton(
                text = secondaryButtonText,
                onClick = {},
                showIcon = true,
            ),
        ),
    )

    private fun loremIpsum(words: Int): String = LoremIpsum(words).values.joinToString(" ")
}
