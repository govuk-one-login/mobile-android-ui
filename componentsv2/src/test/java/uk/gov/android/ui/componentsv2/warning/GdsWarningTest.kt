package uk.gov.android.ui.componentsv2.warning

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertContentDescriptionEquals
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onChild
import androidx.compose.ui.test.onChildren
import androidx.compose.ui.test.onFirst
import androidx.compose.ui.test.onLast
import androidx.compose.ui.test.onRoot
import org.junit.Assert.assertTrue
import org.junit.Ignore
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class GdsWarningTest {
    private val text = "Lorem ipsum dolor sit amet."

    @Rule
    @JvmField
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun test() {
        composeTestRule.setContent {
            GdsWarningText(text)
        }
        val warningNode = composeTestRule
            .onRoot(true)
            .onChild()

        assertTrue(
            warningNode
                .fetchSemanticsNode()
                .config
                .isMergingSemanticsOfDescendants,
        )
        warningNode
            .onChildren()
            .onFirst()
            .assertContentDescriptionEquals("warning\n")
        warningNode
            .onChildren()
            .onLast()
            .assertTextContains(text)
    }

    @Test
    @Ignore("Fix failing test")
    fun testPreview() {
        composeTestRule.setContent {
            WarningPreview(text)
        }
        val warningNode = composeTestRule
            .onRoot(true)
            .onChild()

        assertTrue(
            warningNode
                .fetchSemanticsNode()
                .config
                .isMergingSemanticsOfDescendants,
        )
        warningNode
            .onChildren()
            .onFirst()
            .assertContentDescriptionEquals("warning\n")
        warningNode
            .onChildren()
            .onLast()
            .assertTextContains(text)
    }
}
