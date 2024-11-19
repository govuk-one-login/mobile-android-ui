package uk.gov.android.ui.pages.modal

import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.text.buildAnnotatedString
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class BulletedTextTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    private lateinit var header: SemanticsMatcher
    private lateinit var bullets: SemanticsMatcher
    private lateinit var footer: SemanticsMatcher

    @Before
    fun setUp() {
        header = hasText(parameters.header.text)
        bullets = hasText(parameters.bullets.first(), substring = true)
        footer = hasText(parameters.footer.text)
    }

    @Test
    fun verifyFullUI() {
        composeTestRule.setContent {
            BulletedText(parameters)
        }
        composeTestRule.onNode(header).assertIsDisplayed()
        composeTestRule.onNode(bullets).assertIsDisplayed()
        composeTestRule.onNode(footer).assertIsDisplayed()
    }

    @Test
    fun verifyPartialUI() {
        composeTestRule.setContent {
            BulletedText(partialParameters)
        }
        composeTestRule.onNode(header).assertDoesNotExist()
        composeTestRule.onNode(bullets).assertIsDisplayed()
        composeTestRule.onNode(footer).assertDoesNotExist()
    }

    @Test
    fun previewTest() {
        composeTestRule.setContent {
            BulletedTextPreview()
        }
    }

    companion object {
        private val parameters = bulletedTextPreviewParams.copy()
        private val partialParameters = bulletedTextPreviewParams.copy(
            header = buildAnnotatedString {},
            footer = buildAnnotatedString {}
        )
    }
}
