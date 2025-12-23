package uk.gov.android.ui.testwrapper

import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
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
    displayTabRow: (Boolean) -> Unit = {},
) {
    val tabPagesOffsetPadding = 50.dp
    val scope = rememberCoroutineScope()
    NavHost(
        navController,
        modifier = modifier,
        startDestination = startDestination,
    ) {
        val mod =
            Modifier
                .statusBarsPadding()
                .navigationBarsPadding()
                .padding(top = tabPagesOffsetPadding)
                .testTag("entries")

        applyTabDestinations(modifier = mod, onNavigate = navController::navigate)
        applyComponentDestinations(
            modifier = mod,
            onNavigate = {
                scope.launch {
                    navController.navigate(it)
                }
            },
        )
        applyPatternDestinations(modifier = mod, displayTabRow)
        applyThemeDestinations(modifier = mod)
    }
}
