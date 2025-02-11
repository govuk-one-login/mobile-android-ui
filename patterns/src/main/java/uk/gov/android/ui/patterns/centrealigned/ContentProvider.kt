package uk.gov.android.ui.patterns.centrealigned

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import kotlinx.collections.immutable.persistentListOf
import uk.gov.android.ui.componentsv2.bulletedlist.BulletedListTitle
import uk.gov.android.ui.componentsv2.bulletedlist.TitleType.BoldText
import uk.gov.android.ui.componentsv2.bulletedlist.TitleType.Heading
import uk.gov.android.ui.componentsv2.bulletedlist.TitleType.Text
import uk.gov.android.ui.patterns.R

internal class ContentProvider : PreviewParameterProvider<CentreAlignedContent> {
    private val title = "Information Banner Title"

    @Suppress("MaxLineLength")
    private val content = "Information Banner content - Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. "
    private val image = ImageResource(
        R.drawable.preview__gdsvectorimage,
        R.string.preview__GdsVectorImage__description,
    )

    override val values: Sequence<CentreAlignedContent> = sequenceOf(
        CentreAlignedContent(
            title,
            image,
            Body(
                listOf(
                    BodyContent.Text(content),
                    BodyContent.Text("One line content resource component"),
                    BodyContent.BulletList(
                        title = BulletedListTitle("Sub Title 1", Heading),
                        items = persistentListOf(
                            "One line content resource component",
                            "One line content resource component",
                            "One line content resource component",
                            "One line content resource component",
                        ),
                    ),
                    BodyContent.Text(content),
                ),
            ),
            "Two line",
            "Primary button",
            "Secondary button",
        ),
        CentreAlignedContent(
            "Information Banner Title",
            image,
            Body(
                listOf(
                    BodyContent.Text(content),
                    BodyContent.Text("One line content resource component"),
                    BodyContent.BulletList(
                        title = BulletedListTitle("Sub Title 1", BoldText),
                        items = persistentListOf(
                            "One line content resource component",
                            "One line content resource component",
                            "One line content resource component",
                            "One line content resource component",
                        ),
                    ),
                    BodyContent.Text(content),
                ),
            ),
            "Two line",
            "Primary button",
        ),
        CentreAlignedContent(
            title,
            image,
            Body(
                listOf(
                    BodyContent.Text(content),
                    BodyContent.Text("One line content resource component"),
                    BodyContent.BulletList(
                        title = BulletedListTitle("Sub Title 1", Text),
                        items = persistentListOf(
                            "One line content resource component",
                            "One line content resource component",
                            "One line content resource component",
                            "One line content resource component",
                        ),
                    ),
                    BodyContent.Text(content),
                ),
            ),
            "Two line",
        ),
        CentreAlignedContent(
            title,
            image,
            Body(
                listOf(
                    BodyContent.Text(content),
                    BodyContent.Text("One line content resource component"),
                    BodyContent.BulletList(
                        title = BulletedListTitle("Sub Title 1", Heading),
                        items = persistentListOf(
                            "One line content resource component",
                            "One line content resource component",
                            "One line content resource component",
                            "One line content resource component",
                        ),
                    ),
                    BodyContent.Text(content),
                ),
            ),
        ),
        CentreAlignedContent(
            title,
            image,
            Body(
                listOf(
                    BodyContent.Text(content),
                    BodyContent.Text("One line content resource component"),
                    BodyContent.BulletList(
                        items = persistentListOf(
                            "One line content resource component",
                            "One line content resource component",
                            "One line content resource component",
                            "One line content resource component",
                        ),
                    ),
                    BodyContent.Text(content),
                ),
            ),
        ),
        CentreAlignedContent(
            title,
            image,
            Body(
                listOf(
                    BodyContent.Text(content),
                    BodyContent.Text("One line content resource component"),
                    BodyContent.BulletList(
                        items = persistentListOf(
                            "One line content resource component",
                            "One line content resource component",
                            "One line content resource component",
                            "One line content resource component",
                        ),
                    ),
                ),
            ),
        ),
        CentreAlignedContent(
            title,
            image,
        ),
        CentreAlignedContent(
            title,
        ),
    )
}
