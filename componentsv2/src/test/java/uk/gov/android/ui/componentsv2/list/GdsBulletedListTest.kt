package uk.gov.android.ui.componentsv2.list

import android.content.Context
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.core.app.ApplicationProvider
import kotlinx.collections.immutable.persistentListOf
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import uk.gov.android.ui.componentsv2.R

@RunWith(RobolectricTestRunner::class)
class GdsBulletedListTest {
    private val context: Context = ApplicationProvider.getApplicationContext()
    private val resources = context.resources
    private val expectedParameterSize = 4
    private val contentList = BulletedListProvider().values.toList()
    private val sampleContent =
        BulletedListProvider().values.first()

    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setUp() {
        Assert.assertEquals(
            "The expected size of the provider has changed!",
            expectedParameterSize,
            contentList.size,
        )
    }

    @Test
    fun testAllElementsAreDisplayedForDeprecatedComposable() {
        composeTestRule.setContent {
            GdsBulletedList(
                bulletListItems = sampleContent.items,
                title = sampleContent.title,
            )
        }

        composeTestRule.onNodeWithText(LINE).assertExists()

        composeTestRule.onNodeWithText(TITLE).assertExists()
    }

    @Test
    fun testAllElementsAreDisplayed() {
        composeTestRule.setContent {
            GdsBulletedListV2(
                bulletListItems = sampleContent.listItems,
                title = sampleContent.title,
            )
        }

        composeTestRule.onNodeWithText(LINE).assertExists()

        composeTestRule.onNodeWithText(TITLE).assertExists()
    }

    @Test
    fun testStyledElementsAreDisplayed() {
        val title = ListTitle(
            text = "Multi style list text",
            titleType = TitleType.Heading,
        )
        val lineOneText = resources.getText(R.string.numbered_list_bold_style_example).toString()
        val lineTwoText = resources.getText(R.string.numbered_list_multi_style_example).toString()
        val bulletedListItems = persistentListOf(
            ListItem(spannableText = R.string.numbered_list_bold_style_example),
            ListItem(spannableText = R.string.numbered_list_multi_style_example),
        )
        composeTestRule.setContent {
            GdsBulletedListV2(
                bulletListItems = bulletedListItems,
                title = title,
            )
        }

        composeTestRule.onNodeWithText(lineOneText)
            .assertExists()

        composeTestRule.onNodeWithText(lineTwoText)
            .assertExists()

        composeTestRule.onNodeWithText(title.text)
            .assertExists()
    }

    @Test
    fun runDeprecatedPreview() {
        composeTestRule.setContent {
            GdsBulletedListDeprecatedPreview(contentList[0])
        }
    }

    @Test
    fun runPreview() {
        composeTestRule.setContent {
            GdsBulletedListPreview(contentList[0])
        }
    }
}

private const val LINE = "Line one bullet list content"
private const val TITLE = "Example Heading"
