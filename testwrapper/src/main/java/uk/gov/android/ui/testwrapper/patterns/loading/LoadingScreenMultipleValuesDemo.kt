package uk.gov.android.ui.testwrapper.patterns.loading

import androidx.activity.compose.BackHandler
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import kotlinx.coroutines.android.awaitFrame
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import uk.gov.android.ui.patterns.loadingscreen.LoadingScreenDynamic
import uk.gov.android.ui.theme.util.UnstableDesignSystemAPI

@OptIn(UnstableDesignSystemAPI::class)
@Composable
fun LoadingScreenMultipleValuesDemo(displayTabRow: (Boolean) -> Unit) {
    val onBackPressedDispatcher = LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher
    var backPressHandled by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()

    BackHandler(enabled = !backPressHandled) {
        backPressHandled = true
        displayTabRow(true)
        coroutineScope.launch {
            awaitFrame()
            onBackPressedDispatcher?.onBackPressed()
            backPressHandled = false
        }
    }

    // This code demonstrates it is possible to dynamically change the loading spinner text
    // and announce it when it changes.
    // In production any code that changes the text will obviously need to be outside of the
    // composable
    var dynamicText by remember { mutableStateOf("") }
    LaunchedEffect(dynamicText) {
        coroutineScope.launch {
            dynamicText = "First message"
            delay(DELAY)
            dynamicText = "Second message"
            delay(DELAY)
            dynamicText = "Third message"
        }
    }
    LoadingScreenDynamic(text = dynamicText)
}

const val DELAY = 4000L
