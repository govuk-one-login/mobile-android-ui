package uk.gov.android.ui.componentsv2.list

import android.content.Context
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.test.core.app.ApplicationProvider
import junit.framework.TestCase.assertEquals
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import uk.gov.android.ui.componentsv2.R
import uk.gov.android.ui.theme.m3.GdsTheme

@RunWith(RobolectricTestRunner::class)
class GdsNumberedListTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    private val context: Context = ApplicationProvider.getApplicationContext()
    private val resources = context.resources

    private val expectedParamSize = 9
    private val itemList = NumberedListProvider().values.toList()

    @Before
    fun setup() {
        assertEquals(expectedParamSize, itemList.size)
    }

    @Test
    fun verifyHeaderTitleDisplayed() {
        val item = itemList[0]
        setupComposable(item.listItems, item.title)

        item.listItems.forEach {
            composeTestRule.onNodeWithText(it.text).assertExists()
        }
        composeTestRule.onNodeWithTag(TAG_TITLE_HEADING).assertExists()
    }

    @Test
    fun verifyHeaderTitleContentDescription() {
        val item = itemList[0]
        val one = 1
        setupComposable(item.listItems, item.title)

        composeTestRule
            .onNodeWithContentDescription(item.title!!.text)
            .assertExists()
        composeTestRule
            .onNodeWithContentDescription(
                "numbered list ${one.convertToWord(context)} " +
                    "item ${one.convertToWord(context)} ${item.listItems[0].text}",
            )
            .assertExists()
    }

    @Test
    fun verifyRegularTitleDisplayed() {
        val item = itemList[1]
        setupComposable(item.listItems, item.title)

        item.listItems.forEach {
            composeTestRule.onNodeWithText(it.text).assertExists()
        }
        composeTestRule.onNodeWithText(item.title!!.text).assertExists()
    }

    @Test
    fun verifyRegularTitleContentDescription() {
        val item = itemList[1]
        val one = 1
        setupComposable(item.listItems, item.title)

        composeTestRule
            .onNodeWithContentDescription(item.title!!.text)
            .assertExists()
        item.listItems.forEachIndexed { index, listItem ->
            val expectedContentDescription = if (index == 0) {
                "numbered list ${(item.listItems.size).convertToWord(context)} items " +
                    "${one.convertToWord(context)} ${listItem.text}"
            } else {
                "${(index + 1).convertToWord(context)} ${listItem.text}"
            }
            composeTestRule.onNodeWithContentDescription(expectedContentDescription).assertExists()
        }
    }

    @Test
    fun verifyNoTitleDisplayed() {
        val item = itemList[2]
        setupComposable(item.listItems, item.title)

        item.listItems.forEach {
            composeTestRule.onNodeWithText(it.text).assertExists()
        }
        composeTestRule.onNodeWithTag(TAG_TITLE_REGULAR).assertDoesNotExist()
        composeTestRule.onNodeWithTag(TAG_TITLE_BOLD).assertDoesNotExist()
        composeTestRule.onNodeWithTag(TAG_TITLE_HEADING).assertDoesNotExist()
    }

    @Test
    fun verifyNoTitleContentDescription() {
        val item = itemList[2]
        val one = 1
        setupComposable(item.listItems, item.title)

        item.listItems.forEachIndexed { index, listItem ->
            val expectedContentDescription = if (index == 0) {
                "numbered list ${(item.listItems.size).convertToWord(context)} " +
                    "items ${one.convertToWord(context)} ${listItem.text}"
            } else {
                "${(index + 1).convertToWord(context)} ${listItem.text}"
            }
            composeTestRule.onNodeWithContentDescription(expectedContentDescription).assertExists()
        }
    }

    @Test
    fun verifyBoldTitleDisplayed() {
        val item = itemList[3]
        setupComposable(item.listItems, item.title)

        item.listItems.forEach {
            composeTestRule.onNodeWithText(it.text).assertExists()
        }
        composeTestRule.onNodeWithText(item.title!!.text).assertExists()
    }

    @Test
    fun verifyDoubleDigitList() {
        val item = itemList[4]
        setupComposable(item.listItems, item.title)

        item.listItems.forEach {
            composeTestRule.onNodeWithText(it.text).assertExists()
        }
        composeTestRule.onNodeWithText(item.title!!.text).assertExists()
    }

    @Test
    fun verifyStyledElements() {
        val title = ListTitle(
            text = "Multi style list text",
            titleType = TitleType.Heading,
        )
        val lineOneText = "Line one"
        val lineTwoText = resources.getText(R.string.numbered_list_bold_style_example).toString()
        val lineThreeText = "Line three"
        val lineFourText = resources.getText(R.string.numbered_list_multi_style_example).toString()
        val numberedListItems = persistentListOf(
            ListItem(lineOneText),
            ListItem(spannableText = R.string.numbered_list_bold_style_example),
            ListItem(lineThreeText),
            ListItem(spannableText = R.string.numbered_list_multi_style_example),
        )
        setupComposable(numberedListItems, title)

        composeTestRule.onNodeWithText(lineOneText).assertExists()
        composeTestRule.onNodeWithText(lineTwoText).assertExists()
        composeTestRule.onNodeWithText(lineThreeText).assertExists()
        composeTestRule.onNodeWithText(lineFourText).assertExists()

        composeTestRule.onNodeWithText(title.text).assertExists()
    }

    @Test
    fun testStyledElementsAreDisplayed() {
        val title = ListTitle(
            text = "Multi style list text",
            titleType = TitleType.Heading,
        )
        val lineOneText = resources.getText(R.string.numbered_list_bold_style_example).toString()
        val lineTwoText = resources.getText(R.string.numbered_list_multi_style_example).toString()
        val numberedListItems = persistentListOf(
            ListItem(spannableText = R.string.numbered_list_bold_style_example),
            ListItem(spannableText = R.string.numbered_list_multi_style_example),
        )
        setupComposable(numberedListItems, title)

        composeTestRule.onNodeWithText(lineOneText)
            .assertExists()

        composeTestRule.onNodeWithText(lineTwoText)
            .assertExists()

        composeTestRule.onNodeWithText(title.text)
            .assertExists()
    }

    @Test
    fun testLinkElementsAreDisplayed() {
        val title = ListTitle(
            text = "Numbered Link list text",
            titleType = TitleType.Heading,
        )
        val lineOneText = resources.getText(R.string.bulleted_list_link_example).toString()
        val numberedListItems = persistentListOf(
            ListItem(
                spannableText = R.string.bulleted_list_link_example,
                onLinkTapped = {},
            ),
        )
        composeTestRule.setContent {
            GdsNumberedList(
                numberedListItems = numberedListItems,
                title = title,
            )
        }

        composeTestRule.onNodeWithText(lineOneText)
            .assertExists()

        composeTestRule.onNodeWithText(title.text)
            .assertExists()

        // Tap on link at end of the line
        composeTestRule.onNodeWithText("Jetpack Compose", substring = true).assertExists()
    }

    @Test
    fun testLinkIconElementsAreDisplayed() {
        val title = ListTitle(
            text = "Link list text",
            titleType = TitleType.Heading,
        )
        val numberedListItems = persistentListOf(
            ListItem(
                spannableText = R.string.bulleted_list_link_example,
                icon = R.drawable.ic_external_site,
            ),
        )
        setupComposable(numberedListItems, title)

        composeTestRule.onNodeWithText(title.text).assertExists()

        composeTestRule.onNodeWithTag(ICON_TAG, useUnmergedTree = true).assertExists()
    }

    @Test
    fun runPreview() {
        composeTestRule.setContent {
            GdsNumberedListPreview(itemList[1])
        }
    }

    private fun setupComposable(
        items: ImmutableList<ListItem>,
        title: ListTitle?,
        modifier: Modifier = Modifier,
    ) {
        composeTestRule.setContent {
            GdsTheme {
                GdsNumberedList(
                    numberedListItems = items,
                    title = title,
                    modifier = modifier,
                )
            }
        }
    }
}
