package uk.gov.android.ui.components.content

import android.content.Context
import android.content.res.Resources
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import uk.gov.android.ui.theme.GdsTheme

@RunWith(AndroidJUnit4::class)
class ContentTest {
    private val context: Context = ApplicationProvider.getApplicationContext()

    private val resources = context.resources

    private val expectedParameterSize = 10
    private val parameterList = ContentProvider().values.toList()

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
    fun testFirstParameters() = contentTests(parameterList[0])

    @Test
    fun testSecondParameters() = contentTests(parameterList[1])

    @Test
    fun testThirdParameters() = contentTests(parameterList[2])

    @Test
    fun testFourthParameters() = contentTests(parameterList[3])

    @Test
    fun testFifthParameters() = contentTests(parameterList[4])

    @Test
    fun testSixthParameters() = contentTests(parameterList[5])

    @Test
    fun testSeventhParameters() = contentTests(parameterList[6])

    @Test
    fun testEighthParameters() = contentTests(parameterList[7])

    @Test
    fun testNinthParameters() = contentTests(parameterList[8])

    @Test
    fun testTenthParameters() = contentTests(parameterList[9])

    private fun contentTests(parameters: ContentParameters) {
        composeTestRule.apply {
            setContent {
                GdsTheme {
                    GdsContent(
                        parameters
                    )
                }
            }

            parameters.verifyComposable(this, resources)
        }
    }
}

@Suppress("NestedBlockDepth")
fun ContentParameters.verifyComposable(
    composeTestRule: ComposeContentTestRule,
    resources: Resources
) = composeTestRule.let { rule ->
    resource.forEach { contentText ->
        contentText.subTitle?.let {
            rule.onNodeWithText(resources.getString(it)).apply {
                assertIsDisplayed()
            }
        }

        val stringResources = when (contentText) {
            is GdsContentText.GdsContentTextString ->
                contentText.text.map(resources::getString).toTypedArray()

            is GdsContentText.GdsContentTextArray ->
                resources.getStringArray(contentText.text)
        }

        stringResources.forEach {
            rule.onNodeWithText(it).apply {
                assertIsDisplayed()
            }
        }
    }
}
