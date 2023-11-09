package uk.gov.android.ui.pages.brp

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

@RunWith(Parameterized::class)
class BrpInstructionsTest(
    private val parameters: BrpInstructionsParameters
) {
    private val context: Context = ApplicationProvider.getApplicationContext()

    private val resources = context.resources

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun brpInstructionsTests() {
        composeTestRule.apply {
            setContent {
                BrpInstructions(brpInstructionsParameters = parameters)
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
                resources.getString(parameters.primaryButtonText)
            ).apply {
                performScrollTo()
                assertIsDisplayed()
            }

            onNodeWithText(
                resources.getString(parameters.secondaryButtonText)
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
        fun values() = BrpInstructionsProvider().values.toList()
    }
}
