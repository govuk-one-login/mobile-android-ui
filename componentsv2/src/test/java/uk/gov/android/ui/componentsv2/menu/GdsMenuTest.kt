package uk.gov.android.ui.componentsv2.menu

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import junit.framework.TestCase.assertEquals
import kotlinx.collections.immutable.persistentListOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import uk.gov.android.ui.theme.m3.GdsTheme

@RunWith(RobolectricTestRunner::class)
class GdsMenuTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    private var menuTapped: String = ""
    private val menuContent = persistentListOf(
        GdsMenuContent(TITLE_ITEM_ONE) {
            menuTapped = TITLE_ITEM_ONE
        },
        GdsMenuContent(TITLE_ITEM_TWO) {
            menuTapped = TITLE_ITEM_TWO
        },
    )

    @Test
    fun testMenuOpen() {
        composeTestRule.setContent {
            GdsTheme {
                GdsMenu(expanded = true, content = menuContent)
            }
        }

        composeTestRule.onNodeWithText(TITLE_ITEM_ONE).assertIsDisplayed()
        composeTestRule.onNodeWithText(TITLE_ITEM_TWO).assertIsDisplayed()
        composeTestRule.onNodeWithText(TITLE_ITEM_ONE).performClick()

        assertEquals(TITLE_ITEM_ONE, menuTapped)
    }

    companion object {
        private const val TITLE_ITEM_ONE = "Item One"
        private const val TITLE_ITEM_TWO = "Item Two"
    }
}
