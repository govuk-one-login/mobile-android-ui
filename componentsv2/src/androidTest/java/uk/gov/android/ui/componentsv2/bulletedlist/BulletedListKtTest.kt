package uk.gov.android.ui.componentsv2.bulletedlist

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class BulletedListKtTest {
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
    fun testAllElementsAreDisplayed() {
        composeTestRule.setContent {
            GdsBulletedList(
                bulletListItems = sampleContent.items,
                title = sampleContent.title,
            )
        }

        composeTestRule.onNodeWithText("Line one bullet list content")
            .assertExists()

        composeTestRule.onNodeWithText("Example Heading")
            .assertExists()
    }
}
