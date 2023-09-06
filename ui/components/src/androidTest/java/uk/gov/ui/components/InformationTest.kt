package uk.gov.ui.components

import android.content.Context
import android.content.res.Resources
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import uk.gov.ui.components.information.Information
import uk.gov.ui.components.information.InformationParameters
import uk.gov.ui.components.information.InformationProvider
import uk.gov.ui.theme.GdsTheme

@RunWith(AndroidJUnit4::class) // Parameterized runner seems to crash
class InformationTest {
    private val context: Context = ApplicationProvider.getApplicationContext()
    private val resources = context.resources

    private val parameterList = InformationProvider().values.toList()

    private val expectedParameterListSize = 2

    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setUp() {
        assertEquals(
            "The expected parameter list size does not match the InformationProvider size!",
            expectedParameterListSize,
            parameterList.size
        )
    }

    private fun setupTest(parameters: InformationParameters) {
        composeTestRule.apply {
            setContent {
                GdsTheme {
                    Information(parameters)
                }
            }

            waitForIdle()

            parameters.verifyComposable(this, resources)
        }
    }

    @Test
    fun verifyFirstEntryComposable() = setupTest(parameterList[0])

    @Test
    fun verifySecondEntryComposable() = setupTest(parameterList[1])
}

fun InformationParameters.verifyComposable(
    composeTestRule: ComposeContentTestRule,
    resources: Resources
) = composeTestRule.let { rule ->
    iconParameters.verifyComposable(rule, resources)
    contentParameters.verifyComposable(rule, resources)
}
