package uk.gov.android.ui.testwrapper

import androidx.compose.ui.test.junit4.createComposeRule
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import uk.gov.android.ui.testwrapper.componentsv2.ComponentsDestination

@RunWith(RobolectricTestRunner::class)
class AppNavHostTest {
    @get:Rule
    val appHostRule =
        AppNavHostRule(
            composeTestRule = createComposeRule(),
        )

    @Test
    fun defaultSetup() =
        runTest {
            appHostRule.run {
                render(TabDestination.Components)
                hasCurrentBackStackRoute<TabDestination.Components>()
            }
        }

    @Test
    fun startDestinationIsConfigurable() =
        runTest {
            appHostRule.run {
                render(TabDestination.Patterns)
                hasCurrentBackStackRoute<TabDestination.Patterns>()
            }
        }

    @Test
    fun navigatesViaSerializableObjects() =
        runTest {
            appHostRule.run {
                render(TabDestination.Components)
                tapMenuItem("Camera")
                hasCurrentBackStackRoute<ComponentsDestination.DetailedItem>()
            }
        }
}
