package uk.gov.android.ui.components.m3

import android.content.Context
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import uk.gov.android.ui.theme.lineHeightH1
import uk.gov.android.ui.theme.lineHeightH2
import uk.gov.android.ui.theme.lineHeightH3
import uk.gov.android.ui.theme.lineHeightH4
import uk.gov.android.ui.theme.m3.GdsTheme
import uk.gov.android.ui.theme.textSizeH1
import uk.gov.android.ui.theme.textSizeH2
import uk.gov.android.ui.theme.textSizeH3
import uk.gov.android.ui.theme.textSizeH4
import uk.gov.android.ui.utils.extensions.assertFontSize
import uk.gov.android.ui.utils.extensions.assertLineHeight

@RunWith(AndroidJUnit4::class)
class HeadingsM3Test {
    private val context: Context = ApplicationProvider.getApplicationContext()
    private val resources = context.resources
    private val expectedParameterSize = 4
    private val parameterList = HeadingsM3Provider().values.toList()

    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setUp() {
        Assert.assertEquals(
            "The expected size of the provider has changed!",
            expectedParameterSize,
            parameterList.size
        )
    }

    @Test
    fun verifyFirstHeading() = headingTests(parameterList[0])

    @Test
    fun verifySecondHeading() = headingTests(parameterList[1])

    @Test
    fun verifyThirdHeading() = headingTests(parameterList[2])

    @Test
    fun verifyFourthHeading() = headingTests(parameterList[3])

    private fun headingTests(parameters: HeadingM3) {
        composeTestRule.apply {
            setContent {
                GdsTheme {
                    parameters.generate()
                }
            }

            onNodeWithText(resources.getString(parameters.text)).apply {
                assertIsDisplayed()
                assertFontSize(
                    when (parameters.size) {
                        is HeadingSizeM3.H4 -> textSizeH4
                        is HeadingSizeM3.H3 -> textSizeH3
                        is HeadingSizeM3.H2 -> textSizeH2
                        is HeadingSizeM3.H1 -> textSizeH1
                    }
                )
                assertLineHeight(
                    when (parameters.size) {
                        is HeadingSizeM3.H4 -> lineHeightH4
                        is HeadingSizeM3.H3 -> lineHeightH3
                        is HeadingSizeM3.H2 -> lineHeightH2
                        is HeadingSizeM3.H1 -> lineHeightH1
                    }
                )
            }
        }
    }
}
