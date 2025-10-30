package uk.gov.android.ui.testwrapper

import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import uk.gov.android.ui.testwrapper.TabDestination.Companion.applyTabDestinations
import uk.gov.android.ui.testwrapper.componentsv2.ComponentsDestination.Companion.applyComponentDestinations
import uk.gov.android.ui.testwrapper.patterns.PatternsDestination
import uk.gov.android.ui.theme.smallPadding

@Composable
fun AppNavHost(
    navController: NavHostController,
    startDestination: Any,
    modifier: Modifier = Modifier,
) {
    val tabPagesOffsetPadding = 50.dp
    NavHost(
        navController,
        startDestination = startDestination
    ) {
        val mod = modifier
            .statusBarsPadding()
            .navigationBarsPadding()
            .padding(top = tabPagesOffsetPadding)

        applyTabDestinations(modifier = mod, onNavigate = navController::navigate)
        applyComponentDestinations(modifier = mod)

        PatternsDestination.entries.forEach { destination ->
            composable(destination.route) {
                Placeholder(
                    label = destination.label,
                    modifier = mod.padding(smallPadding)
                )
            }
        }
    }
}