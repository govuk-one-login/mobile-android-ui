package uk.gov.android.ui.componentsv2.camera

import android.Manifest
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.junit4.StateRestorationTester
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.rule.GrantPermissionRule
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.withContext
import org.junit.Rule
import org.junit.Test
import uk.gov.android.ui.componentsv2.camera.usecase.CameraUseCaseProvider
import uk.gov.android.ui.componentsv2.camera.usecase.CameraUseCaseProvider.Companion.preview

class CameraContentTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @get:Rule
    val permissionsRule: GrantPermissionRule = GrantPermissionRule.grant(
        Manifest.permission.CAMERA,
    )

    private val model = CameraContentViewModel()

    @Test
    fun configuresCameraUseCases() = runTest {
        val state = StateRestorationTester(composeTestRule)
        val useCases = withContext(Dispatchers.Main) {
            listOf(
                preview(model::update),
            ).map(
                CameraUseCaseProvider::provide,
            )
        }
        state.setContent {
            model.addAll(useCases)
            CameraContent(
                viewModel = model,
                modifier = Modifier.fillMaxSize(),
            )
        }

        state.emulateSavedInstanceStateRestore()
    }
}
