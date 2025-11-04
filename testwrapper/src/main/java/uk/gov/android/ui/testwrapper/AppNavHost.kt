package uk.gov.android.ui.testwrapper

import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import uk.gov.android.ui.testwrapper.TabDestination.Companion.applyTabDestinations
import uk.gov.android.ui.testwrapper.componentsv2.ComponentsDestination.Companion.applyComponentDestinations
import uk.gov.android.ui.testwrapper.patterns.PatternsDestination.Companion.applyPatternDestinations

@Composable
fun AppNavHost(
    navController: NavHostController,
    startDestination: Any,
    modifier: Modifier = Modifier,
) {
    val tabPagesOffsetPadding = 50.dp
    NavHost(
        navController,
        modifier = modifier,
        startDestination = startDestination
    ) {
        val mod = Modifier
            .statusBarsPadding()
            .navigationBarsPadding()
            .padding(top = tabPagesOffsetPadding)

        applyTabDestinations(modifier = mod, onNavigate = navController::navigate)
        applyComponentDestinations(modifier = mod)
        applyPatternDestinations(modifier = mod)
    }
}