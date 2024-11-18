package uk.gov.android.ui.components.m3.content

import android.content.Context
import android.content.res.Resources
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.core.app.ApplicationProvider
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import uk.gov.android.ui.components.content.GdsContentText
import uk.gov.android.ui.theme.GdsTheme

@RunWith(Parameterized::class)
class ContentM3Test(
    private val parameters: ContentParameters,
) {
    private val context: Context = ApplicationProvider.getApplicationContext()

    private val resources = context.resources

    private val expectedParameterSize = 11

    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setUp() {
        Assert.assertEquals(
            "The expected size of the provider has changed!",
            expectedParameterSize,
            values().size,
        )
    }

    @Test
    fun contentTests() {
        composeTestRule.apply {
            setContent {
                GdsTheme {
                    GdsContent(
                        parameters,
                    )
                }
            }

            parameters.verifyComposable(this, resources)
        }
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters(name = "{index}: {0}")
        fun values() = ContentProvider().values.toList()
    }
}

@Suppress("NestedBlockDepth")
fun ContentParameters.verifyComposable(
    composeTestRule: ComposeContentTestRule,
    resources: Resources,
) = composeTestRule.let { rule ->
    resource.forEach { contentText ->
        contentText.subTitle?.let {
            rule.onNodeWithText(resources.getString(it)).apply {
                assertIsDisplayed()
            }
        }

        val stringResources = when (contentText) {
            is GdsContentText.GdsContentTextString ->
                contentText.textVar?.let {
                    contentText.text.map { text ->
                        resources.getString(text, contentText.textVar)
                    }.toTypedArray()
                } ?: run {
                    contentText.text.map(resources::getString).toTypedArray()
                }

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
