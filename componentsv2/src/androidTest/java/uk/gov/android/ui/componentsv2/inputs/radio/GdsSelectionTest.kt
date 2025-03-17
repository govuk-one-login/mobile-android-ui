package uk.gov.android.ui.componentsv2.inputs.radio

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.verify
import org.mockito.Mockito.mock

class GdsSelectionTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testOnItemSelectedCallbackIsCalled() {
        val items: ImmutableList<String> = persistentListOf("Option 1", "Option 2", "Option 3")
        val onItemSelected = mock<(Int) -> Unit>()

        composeTestRule.setContent {
            GdsSelection(
                items = items,
                selectedItem = null,
                onItemSelected = onItemSelected,
            )
        }

        composeTestRule.onNodeWithText("Option 2", useUnmergedTree = true).performClick()
        verify(onItemSelected).invoke(1)
    }
}
