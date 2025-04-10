package uk.gov.android.ui.patterns.dialog

import android.content.Context
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.hasContentDescription
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.isNotDisplayed
import androidx.compose.ui.test.performClick
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import org.junit.Before
import org.junit.Test
import uk.gov.android.ui.componentsv2.GdsCard
import uk.gov.android.ui.componentsv2.R
import uk.gov.android.ui.patterns.utils.FragmentActivityTestCase

class FullScreenDialogueTest : FragmentActivityTestCase() {
    private lateinit var closeButton: SemanticsMatcher
    private lateinit var title: SemanticsMatcher
    private lateinit var content: SemanticsMatcher

    private val titleText = "Title"
    private val contentText = "Content"

    @Before
    fun setUp() {
        val context: Context = ApplicationProvider.getApplicationContext()
        closeButton = hasContentDescription(context.getString(R.string.close_button))
        title = hasText(titleText)
        content = hasText(contentText)
    }

    @Test
    fun verifyUI() {
        composeTestRule.setContent {
            FullScreenDialogue(
                onDismissRequest = { },
                title = titleText,
            ) {
                Text(contentText)
            }
        }
        composeTestRule.onNode(closeButton).assertIsDisplayed()
        composeTestRule.onNode(title).assertIsDisplayed()
        composeTestRule.onNode(content).assertIsDisplayed()
    }

    @Test
    fun verifyUIScrollableContent() {
        composeTestRule.setContent {
            FullScreenDialogue(
                onDismissRequest = { },
                title = titleText,
            ) {
                LazyColumn {
                    item {
                        Text(contentText)
                    }
                }
            }
        }
        composeTestRule.onNode(closeButton).assertIsDisplayed()
        composeTestRule.onNode(title).assertIsDisplayed()
        composeTestRule.onNode(content).assertIsDisplayed()
    }

    @Test
    fun verifyUIGdsCardDisplayed() {
        composeTestRule.setContent {
            FullScreenDialogue(
                onDismissRequest = { },
            ) {
                GdsCard(
                    titleText,
                    {},
                )
            }
        }

        composeTestRule.onNode(closeButton).assertIsDisplayed()
        composeTestRule.onNode(title).assertIsDisplayed()
    }

    @Test
    fun verifyClose() {
        var didClose = false

        composeTestRule.setContent {
            FullScreenDialogue(
                title = titleText,
                onDismissRequest = {
                    didClose = true
                },
            ) {
                Text(contentText)
            }
        }

        composeTestRule.onNode(closeButton).assertHasClickAction()
        composeTestRule.onNode(closeButton).assertIsEnabled()
        composeTestRule.onNode(closeButton).performClick()

        assertEquals(true, didClose)
    }

    @Test
    fun verifyOnBackPressSpecificBehaviour() {
        var backPress = false

        composeTestRule.setContent {
            FullScreenDialogue(
                title = titleText,
                onDismissRequest = {},
                onBack = {
                    backPress = true
                },
            ) {
                Text(contentText)
            }
        }

        Espresso.pressBack()

        assertTrue(backPress)
    }

    @Test
    fun verifyOnBackPressDefault() {
        composeTestRule.setContent {
            FullScreenDialogue(
                title = titleText,
                onDismissRequest = {},
            ) {
                Text(contentText)
            }
        }

        Espresso.pressBack()

        composeTestRule.onNode(title).isNotDisplayed()
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Test
    fun verifyUICustomTopAppBar() {
        composeTestRule.setContent {
            FullScreenDialogue(
                onDismissRequest = { },
                topAppBar = {
                    TopAppBar(
                        title = { Text(titleText) },
                    )
                },
            ) {
                Text(contentText)
            }
        }
        composeTestRule.onNode(title).assertIsDisplayed()
        composeTestRule.onNode(content).assertIsDisplayed()
    }
}
