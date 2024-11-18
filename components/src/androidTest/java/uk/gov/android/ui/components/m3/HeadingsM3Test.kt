package uk.gov.android.ui.components.m3

import android.content.Context
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.core.app.ApplicationProvider
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
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

class HeadingsM3Test {
    private val context: Context = ApplicationProvider.getApplicationContext()
    private val resources = context.resources
    private val expectedParameterSize = 4
    private val parameterList = HeadingsProvider().values.toList()

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

    private fun headingTests(parameters: Heading) {
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
                        is HeadingSize.HeadlineSmall -> textSizeH4
                        is HeadingSize.HeadlineMedium -> textSizeH3
                        is HeadingSize.HeadlineLarge -> textSizeH2
                        is HeadingSize.DisplaySmall -> textSizeH1
                    }
                )
                assertLineHeight(
                    when (parameters.size) {
                        is HeadingSize.HeadlineSmall -> lineHeightH4
                        is HeadingSize.HeadlineMedium -> lineHeightH3
                        is HeadingSize.HeadlineLarge -> lineHeightH2
                        is HeadingSize.DisplaySmall -> lineHeightH1
                    }
                )
            }
        }
    }
}
