package uk.gov.android.ui.patterns.leftalignedscreen.v3

import androidx.compose.foundation.layout.Column
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import uk.gov.android.ui.patterns.leftalignedscreen.v3.MainContentParametersProvider.Companion.BODY_TEXT
import uk.gov.android.ui.patterns.leftalignedscreen.v3.MainContentParametersProvider.Companion.IMAGE_DESCRIPTION

@RunWith(RobolectricTestRunner::class)
class MainContentParametersTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    private val content = MainContentParametersProvider()

    @Test
    fun displayDefaultTitle() {
        with(composeTestRule) {
            setContent {
                Column {
                    MainContentV2(
                        parameters = MainContentParameters.generateDefault(
                            title = TITLE,
                            body = null,
                        ),
                    )
                }
            }

            onNodeWithText(TITLE).assertIsDisplayed()
            onNodeWithText(BODY_TEXT).assertIsNotDisplayed()
            onNodeWithContentDescription(IMAGE_DESCRIPTION).assertIsNotDisplayed()
        }
    }

    @Test
    fun displayDefaultBody() {
        with(composeTestRule) {
            setContent {
                Column {
                    MainContentV2(
                        parameters = MainContentParameters.generateDefault(
                            title = null,
                            body = content.simpleBody(),
                        ),
                    )
                }
            }

            onNodeWithText(TITLE).assertIsNotDisplayed()
            onNodeWithText(BODY_TEXT).assertIsDisplayed()
            onNodeWithContentDescription(IMAGE_DESCRIPTION).assertIsDisplayed()
        }
    }

    @Test
    fun displayDefaultTitleWithBody() {
        with(composeTestRule) {
            setContent {
                Column {
                    MainContentV2(
                        parameters = MainContentParameters.generateDefault(
                            title = TITLE,
                            body = content.simpleBody(),
                        ),
                    )
                }
            }

            onNodeWithText(TITLE).assertIsDisplayed()
            onNodeWithText(BODY_TEXT).assertIsDisplayed()
            onNodeWithContentDescription(IMAGE_DESCRIPTION).assertIsDisplayed()
        }
    }

    companion object {
        private const val TITLE = "Do you have a UK passport or passport with a biometric chip?"
    }
}
