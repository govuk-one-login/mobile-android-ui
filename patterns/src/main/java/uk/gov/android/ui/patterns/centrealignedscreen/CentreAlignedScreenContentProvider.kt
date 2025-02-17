package uk.gov.android.ui.patterns.centrealignedscreen

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import kotlinx.collections.immutable.persistentListOf
import uk.gov.android.ui.componentsv2.bulletedlist.BulletedListTitle
import uk.gov.android.ui.componentsv2.bulletedlist.TitleType.BoldText
import uk.gov.android.ui.componentsv2.bulletedlist.TitleType.Heading
import uk.gov.android.ui.componentsv2.bulletedlist.TitleType.Text
import uk.gov.android.ui.patterns.R

internal class CentreAlignedScreenContentProvider :
    PreviewParameterProvider<CentreAlignedScreenContent> {
    private val title = "Information Banner Title"

    @Suppress("MaxLineLength")
    private val content =
        "Body content - Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."

    @Suppress("MaxLineLength")
    private val supportingText =
        "Supporting Text - Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."

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
                    title = BulletedListTitle("Sub Title 1", Heading),
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
            CentreAlignedScreenButton("Primary button", {}),
            CentreAlignedScreenButton("Secondary button", {}),
        ),
        CentreAlignedScreenContent(
            "Information Banner Title",
            image,
            persistentListOf(
                CentreAlignedScreenBodyContent.Text(content),
                CentreAlignedScreenBodyContent.Text("One line content resource component"),
                CentreAlignedScreenBodyContent.BulletList(
                    title = BulletedListTitle("Sub Title 1", BoldText),
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
            CentreAlignedScreenButton("Primary button", {}),
        ),
        CentreAlignedScreenContent(
            title,
            image,
            persistentListOf(
                CentreAlignedScreenBodyContent.Text(content),
                CentreAlignedScreenBodyContent.Text("One line content resource component"),
                CentreAlignedScreenBodyContent.BulletList(
                    title = BulletedListTitle("Sub Title 1", Text),
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
        ),
        CentreAlignedScreenContent(
            title,
            image,
            persistentListOf(
                CentreAlignedScreenBodyContent.Text(content),
                CentreAlignedScreenBodyContent.Text("One line content resource component"),
                CentreAlignedScreenBodyContent.BulletList(
                    title = BulletedListTitle("Sub Title 1", Heading),
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
        ),
        CentreAlignedScreenContent(
            title,
            image,
        ),
        CentreAlignedScreenContent(
            title,
        ),
    )
}
