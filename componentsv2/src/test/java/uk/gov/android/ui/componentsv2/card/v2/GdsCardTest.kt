package uk.gov.android.ui.componentsv2.card.v2

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
    fun `card accepts every parameter explicitly`() {
        composeTestRule.setContent {
            GdsTheme {
                GdsCard(
                    modifier = Modifier.testTag(CARD_TAG),
                    shape = RoundedCornerShape(CUSTOM_RADIUS),
                    colors = GdsCardDefaults.colors(containerColor = Color.Red),
                    elevation = GdsCardDefaults.elevatedElevation(),
                    border = GdsCardDefaults.border(),
                ) {
                    Text(CONTENT)
                }
            }
        }

        composeTestRule.onNodeWithText(CONTENT).assertIsDisplayed()
    }

    @Test
    fun `clickable card accepts every parameter explicitly`() {
        var clicks = 0
        composeTestRule.setContent {
            GdsTheme {
                GdsCard(
                    onClick = { clicks++ },
                    modifier = Modifier.testTag(CARD_TAG),
                    enabled = true,
                    shape = RoundedCornerShape(CUSTOM_RADIUS),
                    colors = GdsCardDefaults.colors(containerColor = Color.Red),
                    elevation = GdsCardDefaults.elevatedElevation(),
                    border = GdsCardDefaults.border(),
                    interactionSource = remember { MutableInteractionSource() },
                ) {
                    Text(CONTENT)
                }
            }
        }

        composeTestRule.onNodeWithTag(CARD_TAG).performClick()

        assertEquals(1, clicks)
    }

    @Test
    fun `card skips recomposition when its parameters are unchanged`() {
        var counter by mutableIntStateOf(0)
        composeTestRule.setContent {
            GdsTheme {
                Column {
                    Text("$counter")
                    GdsCard { Text(CONTENT) }
                    GdsCard(onClick = {}) { Text(CONTENT) }
                }
            }
        }

        counter++
        composeTestRule.waitForIdle()

        composeTestRule.onNodeWithText("1").assertIsDisplayed()
        composeTestRule.onAllNodesWithText(CONTENT).assertCountEquals(2)
    }

    @Test
    fun `card recomposes when its parameters change`() {
        var border: BorderStroke? by mutableStateOf(null)
        var elevation: Dp by mutableStateOf(0.dp)
        var enabled by mutableStateOf(true)
        composeTestRule.setContent {
            GdsTheme {
                Column {
                    GdsCard(
                        border = border,
                        elevation = CardDefaults.cardElevation(defaultElevation = elevation),
                    ) { Text(CONTENT) }
                    GdsCard(
                        onClick = {},
                        modifier = Modifier.testTag(CARD_TAG),
                        enabled = enabled,
                        border = border,
                        elevation = CardDefaults.cardElevation(defaultElevation = elevation),
                    ) { Text(CONTENT) }
                }
            }
        }

        border = BorderStroke(1.dp, Color.Black)
        elevation = 8.dp
        enabled = false
        composeTestRule.waitForIdle()

        composeTestRule.onNodeWithTag(CARD_TAG).assertIsNotEnabled()
        composeTestRule.onAllNodesWithText(CONTENT).assertCountEquals(2)
    }

    @Test
    fun `clickable preview handles clicks`() {
        composeTestRule.setContent { GdsCardClickablePreview() }

        composeTestRule.onNodeWithText("A card that handles taps.").performClick()
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
        val CUSTOM_RADIUS = 4.dp
    }
}
