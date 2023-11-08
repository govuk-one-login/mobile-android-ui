package uk.gov.android.ui.pages.calculator

import android.content.Context
import androidx.annotation.ArrayRes
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
class NumberInputQuestionTest(
    private val parameters: NumberInputQuestionParameters
) {
    private val context: Context = ApplicationProvider.getApplicationContext()

    private val resources = context.resources

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun numberInputTests() {
        composeTestRule.apply {
            setContent {
                NumberInputQuestion(parameters)
            }

            onNodeWithText(
                resources.getString(parameters.title)
            ).apply {
                assertIsDisplayed()
            }

            parameters.content?.let { checkContentSection(this, it) }

            onNodeWithTag(
                "numberInput"
            ).apply {
                performScrollTo()
                assertIsDisplayed()
            }

            parameters.hintText?.let {
                onNodeWithText(
                    resources.getString(it)
                ).apply {
                    performScrollTo()
                    assertIsDisplayed()
                }
            }
        }
    }

    private fun checkContentSection(
        tesRule: ComposeContentTestRule,
        @ArrayRes
        text: Int
    ) {
        tesRule.apply {
            resources.getStringArray(text).forEach {
                onNodeWithText(it).apply {
                    assertIsDisplayed()
                }
            }
        }
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun values() = NumberInputQuestionProvider().values.toList()
    }
}
