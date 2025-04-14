package uk.gov.android.ui.componentsv2.numberedlist

import androidx.compose.ui.Modifier
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import junit.framework.TestCase.assertEquals
import kotlinx.collections.immutable.ImmutableList
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import uk.gov.android.ui.componentsv2.bulletedlist.BulletedListTitle

@RunWith(RobolectricTestRunner::class)
class GdsNumberedListTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    private val expectedParamSize = 6
    private val itemList = NumberedListProvider().values.toList()

    @Before
    fun setup() {
        assertEquals(expectedParamSize, itemList.size)
    }

    @Test
    fun verifyHeaderTitleDisplayed() {
        val item = itemList[0]
        setupComposable(item.items, item.title)

        item.items.forEach {
            composeTestRule.onNodeWithText(it).assertExists()
        }
        composeTestRule.onNodeWithTag(TAG_TITLE_HEADING).assertExists()
    }

    @Test
    fun verifyHeaderTitleContentDescription() {
        val item = itemList[0]
        setupComposable(item.items, item.title)

        composeTestRule
            .onNodeWithContentDescription(item.title!!.text)
            .assertExists()
        composeTestRule
            .onNodeWithContentDescription("numbered list 1 item 1 ${item.items[0]}")
            .assertExists()
    }

    @Test
    fun verifyRegularTitleDisplayed() {
        val item = itemList[1]
        setupComposable(item.items, item.title)

        item.items.forEach {
            composeTestRule.onNodeWithText(it).assertExists()
        }
        composeTestRule.onNodeWithText(item.title!!.text).assertExists()
    }

    @Test
    fun verifyRegularTitleContentDescription() {
        val item = itemList[1]
        setupComposable(item.items, item.title)

        composeTestRule
            .onNodeWithContentDescription(item.title!!.text)
            .assertExists()
        item.items.forEachIndexed { index, text ->
            val expectedContentDescription = if (index == 0) {
                "numbered list ${item.items.size} items 1 $text"
            } else {
                "${index+1} $text"
            }
            composeTestRule.onNodeWithContentDescription(expectedContentDescription).assertExists()
        }
    }

    @Test
    fun verifyNoTitleDisplayed() {
        val item = itemList[2]
        setupComposable(item.items, item.title)

        item.items.forEach {
            composeTestRule.onNodeWithText(it).assertExists()
        }
        composeTestRule.onNodeWithTag(TAG_TITLE_REGULAR).assertDoesNotExist()
        composeTestRule.onNodeWithTag(TAG_TITLE_BOLD).assertDoesNotExist()
        composeTestRule.onNodeWithTag(TAG_TITLE_HEADING).assertDoesNotExist()
    }

    @Test
    fun verifyNoTitleContentDescription() {
        val item = itemList[2]
        setupComposable(item.items, item.title)

        item.items.forEachIndexed { index, text ->
            val expectedContentDescription = if (index == 0) {
                "numbered list ${item.items.size} items 1 $text"
            } else {
                "${index+1} $text"
            }
            composeTestRule.onNodeWithContentDescription(expectedContentDescription).assertExists()
        }
    }

    @Test
    fun verifyBoldTitleDisplayed() {
        val item = itemList[3]
        setupComposable(item.items, item.title)

        item.items.forEach {
            composeTestRule.onNodeWithText(it).assertExists()
        }
        composeTestRule.onNodeWithText(item.title!!.text).assertExists()
    }

    @Test
    fun verifyDoubleDigitList() {
        val item = itemList[4]
        setupComposable(item.items, item.title)

        item.items.forEach {
            composeTestRule.onNodeWithText(it).assertExists()
        }
        composeTestRule.onNodeWithText(item.title!!.text).assertExists()
    }

    @Test
    fun runPreview() {
        composeTestRule.setContent {
            GdsNumberedListPreview(itemList[1])
        }
    }

    private fun setupComposable(
        items: ImmutableList<String>,
        title: BulletedListTitle?,
        modifier: Modifier = Modifier
    ) {
        composeTestRule.setContent {
            GdsNumberedList(
                numberedListItems = items,
                title = title,
                modifier = modifier
            )
        }
    }
}