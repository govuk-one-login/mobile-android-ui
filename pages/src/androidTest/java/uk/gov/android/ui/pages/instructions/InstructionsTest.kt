package uk.gov.android.ui.pages.instructions

import android.content.Context
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performScrollTo
import androidx.test.core.app.ApplicationProvider
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import uk.gov.android.ui.pages.brp.BrpInstructionsContentSection

@RunWith(Parameterized::class)
class InstructionsTest(
    private val parameters: Instructions
) {
    private val context: Context = ApplicationProvider.getApplicationContext()

    private val resources = context.resources

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun instructionsTests() {
        composeTestRule.apply {
            setContent {
                parameters.generate()
            }

            onNodeWithText(
                resources.getString(parameters.title)
            ).apply {
                assertIsDisplayed()
            }

            parameters.content.forEach {
                checkContentSection(this, it)
            }

            parameters.image?.let {
                onNodeWithTag(
                    it.toString()
                ).apply {
                    assertIsDisplayed()
                }
            }

            parameters.topIcon?.let {
                onNodeWithTag(
                    it.toString()
                ).apply {
                    assertIsDisplayed()
                }
            }

            parameters.helpTextParameters?.let {
                onNodeWithText(
                    resources.getString(it.text)
                ).apply {
                    performScrollTo()
                    assertIsDisplayed()
                }

                onNodeWithTag(
                    it.iconParameters.image.toString(),
                    useUnmergedTree = true
                ).apply {
                    performScrollTo()
                    assertIsDisplayed()
                }
            }

            onNodeWithText(
                resources.getString(parameters.buttonParameters!![0].text)
            ).apply {
                performScrollTo()
                assertIsDisplayed()
            }

            onNodeWithText(
                resources.getString(parameters.buttonParameters!![1].text)
            ).apply {
                performScrollTo()
                assertIsDisplayed()
            }
        }
    }

    private fun checkContentSection(
        tesRule: ComposeContentTestRule,
        contentSection: BrpInstructionsContentSection
    ) {
        tesRule.apply {
            contentSection.subTitle?.let {
                onNodeWithText(
                    resources.getString(it)
                ).apply {
                    assertIsDisplayed()
                }
            }
            resources.getStringArray(contentSection.text).forEach {
                onNodeWithText(it).apply {
                    assertIsDisplayed()
                }
            }
        }
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun values() = InstructionsProvider().values.toList()
    }
}
