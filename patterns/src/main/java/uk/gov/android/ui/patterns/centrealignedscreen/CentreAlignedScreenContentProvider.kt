package uk.gov.android.ui.patterns.centrealignedscreen

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import kotlinx.collections.immutable.persistentListOf
import uk.gov.android.ui.componentsv2.list.ListItem
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
                CentreAlignedScreenBodyContent.Text(ONE_LINE),
                CentreAlignedScreenBodyContent.BulletList(
                    title = ListTitle(SUB1, Heading),
                    items = persistentListOf(
                        ONE_LINE,
                        ONE_LINE,
                        ONE_LINE,
                        ONE_LINE,
                    ),
                ),
                CentreAlignedScreenBodyContent.Text(content),
            ),
            SUPPORTING_TEXT,
            CentreAlignedScreenButton(text = PRIMARY_BUTTON, onClick = {}),
            CentreAlignedScreenButton(text = SECONDARY_BUTTON, onClick = {}),
        ),
        CentreAlignedScreenContent(
            title,
            image,
            persistentListOf(
                CentreAlignedScreenBodyContent.Text(content),
                CentreAlignedScreenBodyContent.Text(ONE_LINE),
                CentreAlignedScreenBodyContent.NumberedList(
                    title = ListTitle(SUB1, Heading),
                    items = persistentListOf(
                        ListItem(ONE_LINE),
                        ListItem(ONE_LINE),
                        ListItem(ONE_LINE),
                        ListItem(ONE_LINE),
                    ),
                ),
                CentreAlignedScreenBodyContent.Text(content),
            ),
            SUPPORTING_TEXT,
            CentreAlignedScreenButton(
                text = PRIMARY_BUTTON,
                onClick = {},
                showIcon = true,
            ),
            CentreAlignedScreenButton(
                text = SECONDARY_BUTTON,
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
            SUPPORTING_TEXT,
            CentreAlignedScreenButton(text = PRIMARY_BUTTON, onClick = {}),
            CentreAlignedScreenButton(text = SECONDARY_BUTTON, onClick = {}),
        ),
        CentreAlignedScreenContent(
            title,
            image,
            persistentListOf(
                CentreAlignedScreenBodyContent.Text(content),
                CentreAlignedScreenBodyContent.Text(ONE_LINE),
                CentreAlignedScreenBodyContent.BulletList(
                    title = ListTitle(SUB1, Text),
                    items = persistentListOf(
                        ONE_LINE,
                        ONE_LINE,
                        ONE_LINE,
                        ONE_LINE,
                    ),
                ),
                CentreAlignedScreenBodyContent.Text(content),
            ),
            SUPPORTING_TEXT,
            CentreAlignedScreenButton(text = PRIMARY_BUTTON, onClick = {}),
        ),
        CentreAlignedScreenContent(
            title,
            image,
            persistentListOf(
                CentreAlignedScreenBodyContent.Text(content),
                CentreAlignedScreenBodyContent.Text(ONE_LINE),
                CentreAlignedScreenBodyContent.BulletList(
                    title = ListTitle(SUB1, BoldText),
                    items = persistentListOf(
                        ONE_LINE,
                        ONE_LINE,
                        ONE_LINE,
                        ONE_LINE,
                    ),
                ),
                CentreAlignedScreenBodyContent.Text(content),
            ),
            secondaryButton = CentreAlignedScreenButton(text = SECONDARY_BUTTON, onClick = {}),
        ),
        CentreAlignedScreenContent(
            title,
            image,
            persistentListOf(
                CentreAlignedScreenBodyContent.Text(content),
                CentreAlignedScreenBodyContent.Text(ONE_LINE),
                CentreAlignedScreenBodyContent.BulletList(
                    items = persistentListOf(
                        ONE_LINE,
                        ONE_LINE,
                        ONE_LINE,
                        ONE_LINE,
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
                CentreAlignedScreenBodyContent.Text(ONE_LINE),
                CentreAlignedScreenBodyContent.BulletList(
                    items = persistentListOf(
                        ONE_LINE,
                        ONE_LINE,
                        ONE_LINE,
                        ONE_LINE,
                    ),
                ),
            ),
            SUPPORTING_TEXT,
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
                CentreAlignedScreenBodyContent.Text(ONE_LINE),
            ),
            supportingTextLong,
            CentreAlignedScreenButton(text = PRIMARY_BUTTON, onClick = {}),
            CentreAlignedScreenButton(text = SECONDARY_BUTTON, onClick = {}),
        ),
    )
}

private const val SUB1 = "Sub Title 1"
private const val PRIMARY_BUTTON = "Primary button"
private const val SECONDARY_BUTTON = "Secondary button"
internal const val ONE_LINE = "One line content resource component"
private const val SUPPORTING_TEXT =
    "Supporting Text - Lorem ipsum dolor sit amet, consectetur adipiscing elit,."
