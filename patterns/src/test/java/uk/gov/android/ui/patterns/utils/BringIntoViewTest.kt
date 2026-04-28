package uk.gov.android.ui.patterns.utils

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.BasicText
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.input.InputMode
import androidx.compose.ui.input.InputModeManager
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.platform.LocalInputModeManager
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performKeyInput
import androidx.compose.ui.test.pressKey
import androidx.compose.ui.unit.dp
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import uk.gov.android.ui.patterns.utils.BringIntoViewTest.Companion.NUM_ITEMS
import uk.gov.android.ui.patterns.utils.BringIntoViewTest.Companion.containerHeight
import uk.gov.android.ui.patterns.utils.BringIntoViewTest.Companion.itemHeight
import uk.gov.android.ui.patterns.utils.ModifierExtensions.bringIntoView

@OptIn(ExperimentalTestApi::class)
@RunWith(RobolectricTestRunner::class)
class BringIntoViewTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    private val focusRequester = FocusRequester()

    @Test
    fun `keyboard can scroll down and up for non-lazy list`() {
        setUpColumn()

        composeTestRule.pressDirectionDown()
        composeTestRule.onNodeWithText(FIRST_ITEM).assertIsNotDisplayed()

        composeTestRule.pressDirectionUp()
        composeTestRule.onNodeWithText(FIRST_ITEM).assertIsDisplayed()
    }

    @Test
    fun `keyboard can scroll down then up for lazy list`() {
        setUpLazyColumn()

        composeTestRule.pressDirectionDown()
        composeTestRule.onNodeWithText(FIRST_ITEM).assertIsNotDisplayed()

        composeTestRule.pressDirectionUp()
        composeTestRule.onNodeWithText(FIRST_ITEM).assertIsDisplayed()
    }

    @Test
    fun `keyboard does not scroll down when content fits`() {
        setUpColumn(numItems = 1)

        composeTestRule.onNodeWithText(FIRST_ITEM).assertIsDisplayed()

        composeTestRule.pressDirectionDown()
        composeTestRule.onNodeWithText(FIRST_ITEM).assertIsDisplayed()
    }

    @Test
    fun `keyboard does not scroll up when already at top`() {
        setUpColumn()

        composeTestRule.onNodeWithText(FIRST_ITEM).assertIsDisplayed()

        composeTestRule.pressDirectionUp()
        composeTestRule.onNodeWithText(FIRST_ITEM).assertIsDisplayed()
    }

    private fun setUpColumn(
        numItems: Int = NUM_ITEMS,
    ) {
        setContent {
            val scrollState = rememberScrollState()
            TestColumn(
                scrollState = scrollState,
                numItems = numItems,
                modifier = Modifier
                    .focusRequester(focusRequester)
                    .bringIntoView(scrollState),
            )
        }
    }

    private fun setUpLazyColumn() {
        setContent {
            val listState = rememberLazyListState()
            TestLazyColumn(
                listState = listState,
                modifier = Modifier
                    .focusRequester(focusRequester)
                    .bringIntoView(listState),
            )
        }
    }

    private fun setContent(content: @Composable () -> Unit) {
        lateinit var inputModeManager: InputModeManager
        composeTestRule.setContent {
            inputModeManager = LocalInputModeManager.current
            content()
        }
        composeTestRule.runOnIdle {
            inputModeManager.requestInputMode(InputMode.Keyboard)
            focusRequester.requestFocus()
        }
    }

    private fun ComposeTestRule.pressDirectionUp() =
        onNodeWithTag(TEST_TAG)
            .performKeyInput { pressKey(Key.DirectionUp) }

    private fun ComposeTestRule.pressDirectionDown() =
        onNodeWithTag(TEST_TAG)
            .performKeyInput { pressKey(Key.DirectionDown) }

    companion object {
        const val TEST_TAG = "bringIntoViewTest"
        const val NUM_ITEMS = 50
        const val FIRST_ITEM = "Item 0"
        val containerHeight = 100.dp
        val itemHeight = 30.dp
    }
}

@Composable
private fun TestColumn(
    scrollState: androidx.compose.foundation.ScrollState,
    modifier: Modifier = Modifier,
    numItems: Int = NUM_ITEMS,
) =
    Column(
        modifier = modifier
            .height(containerHeight)
            .verticalScroll(scrollState)
            .testTag(BringIntoViewTest.TEST_TAG),
    ) {
        repeat(numItems) { index ->
            BasicText(
                text = "Item $index",
                modifier = Modifier.fillMaxWidth().height(itemHeight),
            )
        }
    }

@Composable
private fun TestLazyColumn(
    listState: LazyListState,
    modifier: Modifier = Modifier,
    numItems: Int = NUM_ITEMS,
) =
    LazyColumn(
        state = listState,
        modifier = modifier
            .height(containerHeight)
            .testTag(BringIntoViewTest.TEST_TAG),
    ) {
        items(numItems) { index ->
            BasicText(
                text = "Item $index",
                modifier = Modifier.fillMaxWidth().height(itemHeight),
            )
        }
    }
