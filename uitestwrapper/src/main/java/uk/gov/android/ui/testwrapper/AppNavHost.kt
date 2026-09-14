package uk.gov.android.ui.testwrapper

import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import kotlinx.coroutines.launch
import uk.gov.android.ui.testwrapper.TabDestination.Companion.applyTabDestinations
import uk.gov.android.ui.testwrapper.componentsv2.ComponentsDestination.Companion.applyComponentDestinations
import uk.gov.android.ui.testwrapper.patterns.PatternsDestination.Companion.applyPatternDestinations
import uk.gov.android.ui.testwrapper.theme.ThemeDestination.Companion.applyThemeDestinations

@Composable
fun AppNavHost(
    navController: NavHostController,
    startDestination: Any,
    modifier: Modifier = Modifier,
) {
    val scope = rememberCoroutineScope()
    NavHost(
        navController,
        modifier = modifier,
        startDestination = startDestination,
    ) {
        val modifier = Modifier.testTag("entries")

        applyTabDestinations(modifier = modifier, onNavigate = navController::navigate)
        applyComponentDestinations(
            modifier = modifier,
            onNavigate = {
                scope.launch {
                    navController.navigate(it)
                }
            },
        )
        applyPatternDestinations(modifier = modifier)
        applyThemeDestinations(modifier = modifier)
    }
}
