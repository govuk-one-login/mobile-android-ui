package uk.gov.android.ui.patterns.centrealigned

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import uk.gov.android.ui.componentsv2.bulletedlist.BulletedListItem
import uk.gov.android.ui.componentsv2.bulletedlist.BulletedListTitle.Bold
import uk.gov.android.ui.componentsv2.bulletedlist.BulletedListTitle.Heading
import uk.gov.android.ui.componentsv2.bulletedlist.BulletedListTitle.Normal
import uk.gov.android.ui.patterns.R

class ContentProvider : PreviewParameterProvider<Content> {
    private val title = "Information Banner Title"
    private val content = "Information Banner content - Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. "
    private val image = ImageResource(
        R.drawable.preview__gdsvectorimage,
        R.string.preview__GdsVectorImage__description,
    )

    override val values: Sequence<Content> = sequenceOf(
        Content(
            title,
            image,
            Body(
                listOf(
                    BodyContent.Text(content),
                    BodyContent.Text("One line content resource component"),
                    BodyContent.BulletList(
                        BulletedListItem(
                            title = Heading("Sub Title 1"),
                            items = listOf(
                                "One line content resource component",
                                "One line content resource component",
                                "One line content resource component",
                                "One line content resource component",
                            ),
                        ),
                    ),
                    BodyContent.Text(content),
                ),
            ),
            "Two line",
            "Primary button",
            "Secondary button",
        ),
        Content(
            "Information Banner Title",
            image,
            Body(
                listOf(
                    BodyContent.Text(content),
                    BodyContent.Text("One line content resource component"),
                    BodyContent.BulletList(
                        BulletedListItem(
                            title = Bold("Sub Title 1"),
                            items = listOf(
                                "One line content resource component",
                                "One line content resource component",
                                "One line content resource component",
                                "One line content resource component",
                            ),
                        ),
                    ),
                    BodyContent.Text(content),
                ),
            ),
            "Two line",
            "Primary button",
        ),
        Content(
            title,
            image,
            Body(
                listOf(
                    BodyContent.Text(content),
                    BodyContent.Text("One line content resource component"),
                    BodyContent.BulletList(
                        BulletedListItem(
                            title = Normal("Sub Title 1"),
                            items = listOf(
                                "One line content resource component",
                                "One line content resource component",
                                "One line content resource component",
                                "One line content resource component",
                            ),
                        ),
                    ),
                    BodyContent.Text(content),
                ),
            ),
            "Two line",
        ),
        Content(
            title,
            image,
            Body(
                listOf(
                    BodyContent.Text(content),
                    BodyContent.Text("One line content resource component"),
                    BodyContent.BulletList(
                        BulletedListItem(
                            title = Heading("Sub Title 1"),
                            items = listOf(
                                "One line content resource component",
                                "One line content resource component",
                                "One line content resource component",
                                "One line content resource component",
                            ),
                        ),
                    ),
                    BodyContent.Text(content),
                ),
            ),
        ),
        Content(
            title,
            image,
            Body(
                listOf(
                    BodyContent.Text(content),
                    BodyContent.Text("One line content resource component"),
                    BodyContent.BulletList(
                        BulletedListItem(
                            items = listOf(
                                "One line content resource component",
                                "One line content resource component",
                                "One line content resource component",
                                "One line content resource component",
                            ),
                        ),
                    ),
                    BodyContent.Text(content),
                ),
            ),
        ),
        Content(
            title,
            image,
            Body(
                listOf(
                    BodyContent.Text(content),
                    BodyContent.Text("One line content resource component"),
                    BodyContent.BulletList(
                        BulletedListItem(
                            items = listOf(
                                "One line content resource component",
                                "One line content resource component",
                                "One line content resource component",
                                "One line content resource component",
                            ),
                        ),
                    ),
                ),
            ),
        ),
        Content(
            title,
            image,
        ),
        Content(
            title,
        ),
    )
}
