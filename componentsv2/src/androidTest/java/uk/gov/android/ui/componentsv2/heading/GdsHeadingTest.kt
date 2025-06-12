package uk.gov.android.ui.componentsv2.heading

import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.assert
import androidx.compose.ui.test.assertContentDescriptionContains
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import org.junit.Rule
import org.junit.Test
import uk.gov.android.ui.theme.util.UnstableDesignSystemAPI

class GdsHeadingTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @OptIn(UnstableDesignSystemAPI::class)
    @Test
    fun titleHasContentDescriptionAndHeadingRole() {
        val title = "Title"
        composeTestRule.setContent {
            GdsHeading(
                text = title,
            )
        }

        composeTestRule
            .onNodeWithText(title)
            .assertContentDescriptionContains(title)
            .assert(SemanticsMatcher.expectValue(SemanticsProperties.Heading, Unit))
    }
}
