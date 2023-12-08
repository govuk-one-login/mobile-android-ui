package uk.gov.android.ui.components

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
import uk.gov.android.ui.components.content.GdsContentText
import uk.gov.android.ui.theme.GdsTheme

@RunWith(AndroidJUnit4::class)
class BulletListTest {
    private val context: Context = ApplicationProvider.getApplicationContext()

    private val resources = context.resources

    private val expectedParameterSize = 3
    private val parameterList = BulletListProvider().values.toList()

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
    fun verifyFirstParameters() = bulletListTests(parameterList[0])

    @Test
    fun verifySecondParameters() = bulletListTests(parameterList[1])

    private fun bulletListTests(parameters: BulletListParameters) {
        composeTestRule.apply {
            setContent {
                GdsTheme {
                    GdsBulletList(
                        parameters
                    )
                }
            }

            val stringResources = when (val contentText = parameters.contentText) {
                is GdsContentText.GdsContentTextString ->
                    contentText.text.map(resources::getString).toTypedArray()

                is GdsContentText.GdsContentTextArray ->
                    resources.getStringArray(contentText.text)

                else ->
                    arrayOf()
            }

            stringResources.forEach {
                onNodeWithText(it).apply {
                    assertIsDisplayed()
                }
            }
        }
    }
}
