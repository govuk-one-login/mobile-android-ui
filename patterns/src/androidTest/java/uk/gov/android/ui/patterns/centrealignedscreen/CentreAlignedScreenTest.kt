package uk.gov.android.ui.patterns.centrealignedscreen

import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.assert
import androidx.compose.ui.test.assertContentDescriptionEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import org.junit.Rule
import org.junit.Test
import uk.gov.android.ui.componentsv2.heading.GdsHeading
import uk.gov.android.ui.theme.util.UnstableDesignSystemAPI

class CentreAlignedScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @OptIn(UnstableDesignSystemAPI::class)
    @Test
    fun titleHasContentDescriptionAndHeadingRole() {
        val title = "Test title"

        composeTestRule.setContent {
            CentreAlignedScreen(
                title = { GdsHeading(text = title) },
            )
        }

        composeTestRule
            .onNodeWithText(title)
            .assertContentDescriptionEquals(title)
            .assert(SemanticsMatcher.expectValue(SemanticsProperties.Heading, Unit))
    }
}
