package uk.gov.android.ui.componentsv2.inputs.radio

import androidx.compose.ui.input.key.Key
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsFocused
import androidx.compose.ui.test.hasContentDescription
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.requestFocus
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performKeyInput
import androidx.compose.ui.test.pressKey
import junit.framework.TestCase.assertEquals
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

class GdsRadiosTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testOnItemSelectedCallbackIsCalled() {
        val items: ImmutableList<String> = persistentListOf("Option 1", "Option 2", "Option 3")
        val onItemSelected = mock<(Int) -> Unit>()

        composeTestRule.setContent {
            GdsRadios(
                items = items,
                selectedItem = null,
                onItemSelected = onItemSelected,
            )
        }

        composeTestRule.onNode(hasContentDescription("Option 2", substring = true)).performClick()
        verify(onItemSelected).invoke(1)
    }

    @Test
    fun testSelectedItem() {
        val items: ImmutableList<String> = persistentListOf("Option 1", "Option 2", "Option 3")
        var selectedItem = 0

        composeTestRule.setContent {
            GdsRadios(
                items = items,
                selectedItem = 0,
                onItemSelected = { itemSelected -> selectedItem = itemSelected },
            )
        }

        composeTestRule.onNode(hasContentDescription("Option 2", substring = true)).performClick()
        assertEquals(1, selectedItem)
    }

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun testKeyboardSelectionWithSpace() {
        val items: ImmutableList<String> = persistentListOf("Option 1", "Option 2")
        val onItemSelected = mock<(Int) -> Unit>()

        composeTestRule.setContent {
            GdsRadios(
                items = items,
                selectedItem = null,
                onItemSelected = onItemSelected,
            )
        }

       
        
        composeTestRule.onNode(
            hasContentDescription("Option 1", substring = true),
        ).apply {
            requestFocus()
            performKeyInput {
                pressKey(Key.Spacebar)
            }
        }

        verify(onItemSelected).invoke(0)
    }

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun testKeyboardFocusMovement() {
        val items: ImmutableList<String> = persistentListOf("Option 1", "Option 2")

        composeTestRule.setContent {
            GdsRadios(
                items = items,
                selectedItem = null,
                onItemSelected = {},
            )
        }

        composeTestRule.onNode(hasContentDescription("Option 1", substring = true)).apply {
            requestFocus()
            assertIsFocused()
        }


        composeTestRule.onNode(hasContentDescription("Option 1", substring = true))
            .performKeyInput {
            pressKey(Key.DirectionDown)
        }

        composeTestRule.onNode(hasContentDescription("Option 2", substring = true)).assertIsFocused()
    }
}
