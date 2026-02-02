package uk.gov.android.ui.testwrapper

import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollTo
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import androidx.navigation.toRoute
import org.junit.Assert.assertNotNull
import uk.gov.android.ui.componentsv2.Renderer

class AppNavHostRule(
    composeTestRule: ComposeContentTestRule,
) : ComposeContentTestRule by composeTestRule,
    Renderer<Any> {
    lateinit var navController: TestNavHostController

    inline fun <reified T : Any> currentBackStackRoute() = navController.currentBackStackEntry?.toRoute<T>()

    inline fun <reified T : Any> hasCurrentBackStackRoute() = assertNotNull(currentBackStackRoute<T>())

    override fun render(input: Any) {
        setContent {
            navController = TestNavHostController(LocalContext.current)
            navController.navigatorProvider.addNavigator(ComposeNavigator())
            AppNavHost(
                navController = navController,
                startDestination = input,
            )
        }
    }

    fun tapMenuItem(menuText: String) {
        onNodeWithText(menuText)
            .assertExists("Cannot find node with text: $menuText")
            .performScrollTo()
            .performClick()

        waitForIdle()
    }
}
