package uk.gov.android.ui.componentsv2.card.v2

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertHasNoClickAction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.getUnclippedBoundsInRoot
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import uk.gov.android.ui.theme.m3.GdsTheme

@RunWith(RobolectricTestRunner::class)
class GdsCardTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun `card displays its content`() {
        composeTestRule.setContent {
            GdsTheme {
                GdsCard {
                    Text(CONTENT)
                }
            }
        }

        composeTestRule.onNodeWithText(CONTENT).assertIsDisplayed()
    }

    @Test
    fun `card without onClick has no click action`() {
        composeTestRule.setContent {
            GdsTheme {
                GdsCard(modifier = Modifier.testTag(CARD_TAG)) {
                    Text(CONTENT)
                }
            }
        }

        composeTestRule.onNodeWithTag(CARD_TAG).assertHasNoClickAction()
    }

    @Test
    fun `clickable card has a click action and invokes onClick`() {
        var clicks = 0
        composeTestRule.setContent {
            GdsTheme {
                GdsCard(
                    onClick = { clicks++ },
                    modifier = Modifier.testTag(CARD_TAG),
                ) {
                    Text(CONTENT)
                }
            }
        }

        composeTestRule.onNodeWithTag(CARD_TAG)
            .assertHasClickAction()
            .assertIsEnabled()
            .performClick()

        assertEquals(1, clicks)
    }

    @Test
    fun `clicking the content of a clickable card invokes onClick`() {
        var clicks = 0
        composeTestRule.setContent {
            GdsTheme {
                GdsCard(onClick = { clicks++ }) {
                    Text(CONTENT)
                }
            }
        }

        composeTestRule.onNodeWithText(CONTENT).performClick()

        assertEquals(1, clicks)
    }

    @Test
    fun `disabled clickable card is not enabled`() {
        composeTestRule.setContent {
            GdsTheme {
                GdsCard(
                    onClick = {},
                    modifier = Modifier.testTag(CARD_TAG),
                    enabled = false,
                ) {
                    Text(CONTENT)
                }
            }
        }

        composeTestRule.onNodeWithTag(CARD_TAG).assertIsNotEnabled()
    }

    @Test
    fun `disabled clickable card does not invoke onClick`() {
        var clicks = 0
        composeTestRule.setContent {
            GdsTheme {
                GdsCard(
                    onClick = { clicks++ },
                    modifier = Modifier.testTag(CARD_TAG),
                    enabled = false,
                ) {
                    Text(CONTENT)
                }
            }
        }

        composeTestRule.onNodeWithTag(CARD_TAG).performClick()

        assertEquals(0, clicks)
    }

    @Test
    fun `card applies no padding to its content`() {
        composeTestRule.setContent {
            GdsTheme {
                GdsCard(modifier = Modifier.testTag(CARD_TAG)) {
                    Box(modifier = Modifier.size(CONTENT_SIZE).testTag(CONTENT_TAG))
                }
            }
        }

        assertEquals(boundsOf(CARD_TAG), boundsOf(CONTENT_TAG))
    }

    @Test
    fun `clickable card applies no padding to its content`() {
        composeTestRule.setContent {
            GdsTheme {
                GdsCard(onClick = {}, modifier = Modifier.testTag(CARD_TAG)) {
                    Box(modifier = Modifier.size(CONTENT_SIZE).testTag(CONTENT_TAG))
                }
            }
        }

        assertEquals(boundsOf(CARD_TAG), boundsOf(CONTENT_TAG))
    }

    @Test
    fun `previews render`() {
        composeTestRule.setContent {
            GdsCardPreview()
            GdsCardWithBorderPreview()
            GdsCardWithShadowPreview()
            GdsCardClickablePreview()
        }

        composeTestRule.onAllNodesWithText("Card").assertCountEquals(4)
    }

    private fun boundsOf(tag: String): List<Dp> = composeTestRule
        .onNodeWithTag(tag, useUnmergedTree = true)
        .getUnclippedBoundsInRoot()
        .let { listOf(it.left, it.top, it.right, it.bottom) }

    private companion object {
        const val CONTENT = "Card content"
        const val CARD_TAG = "card"
        const val CONTENT_TAG = "content"
        val CONTENT_SIZE = 100.dp
    }
}
