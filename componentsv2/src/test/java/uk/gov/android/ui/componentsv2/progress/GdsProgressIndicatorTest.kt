package uk.gov.android.ui.componentsv2.progress

import androidx.compose.ui.semantics.LiveRegionMode
import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.test.MainTestClock
import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.assert
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.StateRestorationTester
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlin.time.Duration.Companion.seconds
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class GdsProgressIndicatorTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val shortWaitLabel = "Loading"
    private val longWaitLabel = "Still loading"
    private val longerWaitLabel = "Still loading, keep waiting"

    @Before
    fun setUp() {
        composeTestRule.mainClock.autoAdvance = false
    }

    @Test
    fun `label advances through the sequence as time passes`() {
        composeTestRule.setContent {
            GdsProgressIndicator()
        }
        composeTestRule.onNodeWithText(shortWaitLabel).assertIsDisplayed()

        composeTestRule.mainClock.advanceTimeLong()
        composeTestRule.onNodeWithText(longWaitLabel).assertIsDisplayed()

        composeTestRule.mainClock.advanceTimeLonger()
        composeTestRule.onNodeWithText(longerWaitLabel).assertIsDisplayed()
    }

    @Test
    fun `given custom labels, label advances through the sequence as time passes`() {
        val customLabels = ProgressLabels("1", "2", "3")
        composeTestRule.setContent {
            GdsProgressIndicator(
                labels = customLabels,
            )
        }
        composeTestRule.onNodeWithText("1").assertIsDisplayed()

        composeTestRule.mainClock.advanceTimeLong()
        composeTestRule.onNodeWithText("2").assertIsDisplayed()

        composeTestRule.mainClock.advanceTimeLonger()
        composeTestRule.onNodeWithText("3").assertIsDisplayed()
    }

    @Test
    fun `when state is saved and restored, it resumes in the correct state`() {
        val restorationTester = StateRestorationTester(composeTestRule)
        restorationTester.setContent {
            GdsProgressIndicator()
        }

        composeTestRule.mainClock.advanceTimeLong()
        composeTestRule.onNodeWithText(longWaitLabel).assertIsDisplayed()

        restorationTester.emulateSavedInstanceStateRestore()

        composeTestRule.onNodeWithText(longWaitLabel).assertIsDisplayed()
    }

    @Test
    fun `when state is saved and restored, it continues to transition`() {
        val restorationTester = StateRestorationTester(composeTestRule)
        restorationTester.setContent {
            GdsProgressIndicator()
        }

        composeTestRule.mainClock.advanceTimeLong()
        composeTestRule.onNodeWithText(longWaitLabel).assertIsDisplayed()

        restorationTester.emulateSavedInstanceStateRestore()
        composeTestRule.mainClock.advanceTimeLonger()

        composeTestRule.onNodeWithText(longerWaitLabel).assertIsDisplayed()
    }

    @Test
    fun `it is a live region for TalkBack label updates`() {
        composeTestRule.setContent {
            GdsProgressIndicator()
        }

        composeTestRule.onNodeWithText(shortWaitLabel)
            .assert(
                SemanticsMatcher.expectValue(
                    SemanticsProperties.LiveRegion,
                    LiveRegionMode.Polite,
                ),
            )
    }

    @Test
    fun `it doesn't define the default progress bar range info semantics`() {
        composeTestRule.setContent {
            GdsProgressIndicator()
        }

        composeTestRule.onAllNodes(
            SemanticsMatcher.keyIsDefined(SemanticsProperties.ProgressBarRangeInfo),
        ).assertCountEquals(0)
    }

    private fun MainTestClock.advanceTimeLong() = advanceTimeBy(5.seconds.inWholeMilliseconds + 1)

    private fun MainTestClock.advanceTimeLonger() = advanceTimeBy(5.seconds.inWholeMilliseconds + 1)
}
