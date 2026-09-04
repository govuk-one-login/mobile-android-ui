package uk.gov.android.ui.patterns.edgetoedgescreen

import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import uk.gov.android.ui.patterns.centrealignedscreen.CentreAlignedScreen
import uk.gov.android.ui.patterns.centrealignedscreen.CentreAlignedScreenButton

@RunWith(RobolectricTestRunner::class)
class EdgeToEdgeWrapperScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    private lateinit var title: SemanticsMatcher
    private lateinit var continueButton: SemanticsMatcher

    private val titleText = "Title"
    private val buttonText = "Continue"

    @Before
    fun setUp() {
        title = hasText(titleText)
        continueButton = hasText(buttonText)
    }

    @Test
    fun testWrapper() {
        composeTestRule.setContent {
            EdgeToEdgeWrapperScreen {
                CentreAlignedScreen(
                    title = titleText,
                    primaryButton = CentreAlignedScreenButton(
                        text = buttonText,
                        onClick = { },
                    ),
                )
            }
        }

        composeTestRule
            .onNode(title)
            .assertIsDisplayed()

        composeTestRule
            .onNode(continueButton)
            .assertIsDisplayed()
            .assertIsEnabled()
            .assertHasClickAction()
    }
}
