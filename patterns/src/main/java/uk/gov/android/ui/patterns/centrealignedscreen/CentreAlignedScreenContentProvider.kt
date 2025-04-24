package uk.gov.android.ui.patterns.centrealignedscreen

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import kotlinx.collections.immutable.persistentListOf
import uk.gov.android.ui.componentsv2.list.ListTitle
import uk.gov.android.ui.componentsv2.list.TitleType.BoldText
import uk.gov.android.ui.componentsv2.list.TitleType.Heading
import uk.gov.android.ui.componentsv2.list.TitleType.Text
import uk.gov.android.ui.patterns.R

internal class CentreAlignedScreenContentProvider :
    PreviewParameterProvider<CentreAlignedScreenContent> {
    private val title = "Information Banner Title"

    @Suppress("MaxLineLength")
    private val content =
        "Body content - Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."

    private val supportingText =
        "Supporting Text - Lorem ipsum dolor sit amet, consectetur adipiscing elit,."

    @Suppress("MaxLineLength")
    private val supportingTextLong =
        "Supporting Text Long - Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Body content - Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Body content - Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."

    private val image = CentreAlignedScreenImage(
        R.drawable.preview__gdsvectorimage,
        "Image description",
    )

    override val values: Sequence<CentreAlignedScreenContent> = sequenceOf(
        CentreAlignedScreenContent(
            title,
            image,
            persistentListOf(
                CentreAlignedScreenBodyContent.Text(content),
                CentreAlignedScreenBodyContent.Text("One line content resource component"),
                CentreAlignedScreenBodyContent.BulletList(
                    title = ListTitle("Sub Title 1", Heading),
                    items = persistentListOf(
                        "One line content resource component",
                        "One line content resource component",
                        "One line content resource component",
                        "One line content resource component",
                    ),
                ),
                CentreAlignedScreenBodyContent.Text(content),
            ),
            supportingText,
            CentreAlignedScreenButton(text = "Primary button", onClick = {}),
            CentreAlignedScreenButton(text = "Secondary button", onClick = {}),
        ),
        CentreAlignedScreenContent(
            title,
            image,
            persistentListOf(
                CentreAlignedScreenBodyContent.Text(content),
                CentreAlignedScreenBodyContent.Text("One line content resource component"),
                CentreAlignedScreenBodyContent.NumberedList(
                    title = ListTitle("Sub Title 1", Heading),
                    items = persistentListOf(
                        "One line content resource component",
                        "One line content resource component",
                        "One line content resource component",
                        "One line content resource component",
                    ),
                ),
                CentreAlignedScreenBodyContent.Text(content),
            ),
            supportingText,
            CentreAlignedScreenButton(
                text = "Primary button",
                onClick = {},
                showIcon = true,
            ),
            CentreAlignedScreenButton(
                text = "Secondary button",
                onClick = {},
                showIcon = true,
            ),
        ),
        CentreAlignedScreenContent(
            "Information Banner Title",
            image,
            persistentListOf(
                CentreAlignedScreenBodyContent.Text(content),
            ),
            supportingText,
            CentreAlignedScreenButton(text = "Primary button", onClick = {}),
            CentreAlignedScreenButton(text = "Secondary button", onClick = {}),
        ),
        CentreAlignedScreenContent(
            title,
            image,
            persistentListOf(
                CentreAlignedScreenBodyContent.Text(content),
                CentreAlignedScreenBodyContent.Text("One line content resource component"),
                CentreAlignedScreenBodyContent.BulletList(
                    title = ListTitle("Sub Title 1", Text),
                    items = persistentListOf(
                        "One line content resource component",
                        "One line content resource component",
                        "One line content resource component",
                        "One line content resource component",
                    ),
                ),
                CentreAlignedScreenBodyContent.Text(content),
            ),
            supportingText,
            CentreAlignedScreenButton(text = "Primary button", onClick = {}),
        ),
        CentreAlignedScreenContent(
            title,
            image,
            persistentListOf(
                CentreAlignedScreenBodyContent.Text(content),
                CentreAlignedScreenBodyContent.Text("One line content resource component"),
                CentreAlignedScreenBodyContent.BulletList(
                    title = ListTitle("Sub Title 1", BoldText),
                    items = persistentListOf(
                        "One line content resource component",
                        "One line content resource component",
                        "One line content resource component",
                        "One line content resource component",
                    ),
                ),
                CentreAlignedScreenBodyContent.Text(content),
            ),
            secondaryButton = CentreAlignedScreenButton(text = "Secondary button", onClick = {}),
        ),
        CentreAlignedScreenContent(
            title,
            image,
            persistentListOf(
                CentreAlignedScreenBodyContent.Text(content),
                CentreAlignedScreenBodyContent.Text("One line content resource component"),
                CentreAlignedScreenBodyContent.BulletList(
                    items = persistentListOf(
                        "One line content resource component",
                        "One line content resource component",
                        "One line content resource component",
                        "One line content resource component",
                    ),
                ),
                CentreAlignedScreenBodyContent.Text(content),
            ),
        ),
        CentreAlignedScreenContent(
            title,
            image,
            persistentListOf(
                CentreAlignedScreenBodyContent.Text(content),
                CentreAlignedScreenBodyContent.Text("One line content resource component"),
                CentreAlignedScreenBodyContent.BulletList(
                    items = persistentListOf(
                        "One line content resource component",
                        "One line content resource component",
                        "One line content resource component",
                        "One line content resource component",
                    ),
                ),
            ),
            supportingText,
        ),
        CentreAlignedScreenContent(
            title,
            image,
        ),
        CentreAlignedScreenContent(
            title,
        ),
        CentreAlignedScreenContent(
            title,
            image,
            persistentListOf(
                CentreAlignedScreenBodyContent.Text("One line content resource component"),
            ),
            supportingTextLong,
            CentreAlignedScreenButton(text = "Primary button", onClick = {}),
            CentreAlignedScreenButton(text = "Secondary button", onClick = {}),
        ),
    )
}
