package uk.gov.android.ui.componentsv2.inputs.radio

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class GdsSelectionTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testOnItemSelectedCallbackIsCalled() {
        val items: ImmutableList<String> = persistentListOf("Option 1", "Option 2", "Option 3")
        val fakeCallback = FakeItemSelectedCallback()

        composeTestRule.setContent {
            GdsSelection(
                items = items,
                selectedItem = null,
                onItemSelected = fakeCallback::invoke
            )
        }

        composeTestRule.onNodeWithText("Option 2", useUnmergedTree = true).performClick()
        assertEquals(1, fakeCallback.itemIndex)
    }
}

class FakeItemSelectedCallback {
    var itemIndex: Int? = null
        private set

    fun invoke(index: Int) {
        itemIndex = index
    }
}
