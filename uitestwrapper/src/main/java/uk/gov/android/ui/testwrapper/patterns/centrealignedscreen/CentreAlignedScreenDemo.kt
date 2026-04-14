package uk.gov.android.ui.testwrapper.patterns.centrealignedscreen

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf
import uk.gov.android.ui.componentsv2.list.ListTitle
import uk.gov.android.ui.componentsv2.list.TitleType
import uk.gov.android.ui.patterns.centrealignedscreen.CentreAlignedScreen
import uk.gov.android.ui.patterns.centrealignedscreen.CentreAlignedScreenBodyContent
import uk.gov.android.ui.patterns.centrealignedscreen.CentreAlignedScreenButton
import uk.gov.android.ui.patterns.centrealignedscreen.CentreAlignedScreenImage
import uk.gov.android.ui.componentsv2.R as componentsR

@SuppressLint("ComposeModifierMissing")
@Composable
fun CentreAlignedScreenDemo() {
    CentreAlignedScreen(
        title = "Centre Aligned Screen",
        image = CentreAlignedScreenImage(
            componentsR.drawable.ic_vector_image,
            "Description",
            tint = null,
        ),
        body = persistentListOf(
            CentreAlignedScreenBodyContent.Text(
                "Centre aligned screen allows you to display information with a centered image and text.",
            ),
            CentreAlignedScreenBodyContent.BulletList(
                title = ListTitle(
                    text = "Bullet list",
                    titleType = TitleType.Text,
                ),
                items = persistentListOf(
                    "This is a bullet point",
                    "This is another bullet point",
                ),
            ),
        ),
        supportingText = "This is supporting text",
        primaryButton = CentreAlignedScreenButton(
            text = "Primary Button",
            onClick = {},
        ),
        secondaryButton = CentreAlignedScreenButton(
            text = "Secondary Button",
            onClick = {},
        ),
    )
}

@SuppressLint("ComposeModifierMissing")
@Composable
fun CentreAlignedScrollableScreenDemo() {
    CentreAlignedScreen(
        title = "Centre Aligned Screen",
        image = CentreAlignedScreenImage(
            componentsR.drawable.ic_vector_image,
            "Description",
            null,
        ),
        body = persistentListOf(
            CentreAlignedScreenBodyContent.Text(
                "Centre aligned screen allows you to display information with a centered image and text.",
            ),
            CentreAlignedScreenBodyContent.BulletList(
                title = ListTitle(
                    text = "Bullet list",
                    titleType = TitleType.Text,
                ),
                items = listItems(),
            ),
        ),
        supportingText = "This is supporting text",
        primaryButton = CentreAlignedScreenButton(
            text = "Primary Button",
            onClick = {},
        ),
        secondaryButton = CentreAlignedScreenButton(
            text = "Secondary Button",
            onClick = {},
        ),
    )
}

private fun listItems(): PersistentList<String> {
    return persistentListOf(
        "Item one",
        "Item two",
        "Item three",
        "Item four",
        "Item five",
        "Item six",
        "Item seven",
        "Item eight",
        "Item nine",
        "Item ten",
        "Item eleven",
        "Item twelve",
        "Item thirteen",
        "Item fourteen",
        "Item fifteen",
        "Item sixteen",
        "Item seventeen",
        "Item eighteen",
        "Item nineteen",
        "Item twenty",
        "Item twenty one",
        "Item twenty two",
        "Item twenty three",
        "Item twenty four",
    )
}
