package uk.gov.android.ui.patterns.errorscreen

import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.assert
import androidx.compose.ui.test.assertContentDescriptionContains
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import org.junit.Rule
import org.junit.Test

class ErrorScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun titleContentDescriptionAndHeadingRole() {
        val title = "Test title"
        composeTestRule.setContent {
            ErrorScreen(
                title = title,
                icon = ErrorScreenIcon.ErrorIcon,
            )
        }

        composeTestRule
            .onNodeWithTag("ErrorScreenTitle")
            .assertContentDescriptionContains(title)
            .assert(SemanticsMatcher.expectValue(SemanticsProperties.Heading, Unit))
    }
}
