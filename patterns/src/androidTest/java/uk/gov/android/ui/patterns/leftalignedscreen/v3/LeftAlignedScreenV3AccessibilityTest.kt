package uk.gov.android.ui.patterns.leftalignedscreen.v3

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toPersistentList
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import uk.gov.android.ui.accessibility.AccessibilityHelper.performAccessibilityCheck

@RunWith(AndroidJUnit4::class)
class LeftAlignedScreenV3AccessibilityTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    private lateinit var title: SemanticsMatcher
    private lateinit var continueButton: SemanticsMatcher
    private lateinit var supportingText: SemanticsMatcher

    private val titleText = "Title"
    private val buttonText = "Continue"
    private val extraText = "Extra text"

    @Before
    fun setUp() {
        title = hasText(titleText)
        continueButton = hasText(buttonText)
        supportingText = hasText(extraText)
    }

    @Test
    fun basicStructurePassesAccessibilityChecks() {
        composeTestRule.performAccessibilityCheck {
            LeftAlignedScreen(
                title = titleText,
                body = longListItems(),
                primaryButton = LeftAlignedScreenButton(
                    "This is a primary button",
                    onClick = {},
                ),
                secondaryButton = LeftAlignedScreenButton(
                    "This is a secondary button",
                    onClick = {},
                ),
            )
        }
    }

    private fun longListItems(): ImmutableList<LeftAlignedScreenBody> = (1..24).map {
        "Item $it"
    }.map {
        LeftAlignedScreenBody.Text(it)
    }.toPersistentList()
}
