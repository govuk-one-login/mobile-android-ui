package uk.gov.android.ui.componentsv2.permission.camera

import android.Manifest
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.junit4.createComposeRule
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertTrue
import org.junit.Assert.fail
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import uk.gov.android.ui.componentsv2.PermissionStateStubs
import uk.gov.android.ui.componentsv2.permission.PermissionLogic
import uk.gov.android.ui.componentsv2.permission.PermissionScreen

@OptIn(ExperimentalPermissionsApi::class)
@RunWith(RobolectricTestRunner::class)
class PermissionScreenTest {
    @get:Rule
    val composeTestRule: ComposeContentTestRule = createComposeRule()

    private val deniedUnhappyPathFailure = "The permission shouldn't be required!"
    private val grantedUnhappyPathFailure = "The permission shouldn't have been granted!"
    private val permanentlyDeniedUnhappyPathFailure =
        "The permission shouldn't be permanently denied!"
    private val permission = Manifest.permission.CAMERA
    private val rationaleUnhappyPathFailure = "The rationale shouldn't have been shown!"
    private var hasReachedHappyPath = false

    @Test
    fun grantedPermissionsDeferToOnGrantPermission() = performLogicFlow(
        logic = PermissionLogic(
            onGrantPermission = {
                hasReachedHappyPath = true
            },
            onPermissionPermanentlyDenied = { fail(permanentlyDeniedUnhappyPathFailure) },
            onRequirePermission = { _, launchPermission -> fail(deniedUnhappyPathFailure) },
            onShowRationale = { _, launchPermission -> fail(rationaleUnhappyPathFailure) },
        ),
        state = PermissionStateStubs.granted(permission),
    )

    @Test
    fun secondarilyRequestedPermissionsDeferToShowingTheRationale() = performLogicFlow(
        logic = PermissionLogic(
            onGrantPermission = { fail(grantedUnhappyPathFailure) },
            onPermissionPermanentlyDenied = { fail(permanentlyDeniedUnhappyPathFailure) },
            onRequirePermission = { _, launchPermission -> fail(deniedUnhappyPathFailure) },
            onShowRationale = { _, launchPermission ->
                hasReachedHappyPath = true
            },
        ),
        state = PermissionStateStubs.rationale(permission),
    )

    @Test
    fun ungrantedPermissionsDeferToOnRequirePermission() = performLogicFlow(
        logic = PermissionLogic(
            onGrantPermission = { fail(grantedUnhappyPathFailure) },
            onPermissionPermanentlyDenied = { fail(permanentlyDeniedUnhappyPathFailure) },
            onRequirePermission = { _, launchPermission ->
                hasReachedHappyPath = true
            },
            onShowRationale = { _, launchPermission -> fail(rationaleUnhappyPathFailure) },
        ),
        state = PermissionStateStubs.denied(permission),
    )

    @Test
    fun permanentlyDeniedPermissionsRelyOnOuterBooleanFlagAndDeniedStatus() = performLogicFlow(
        logic = PermissionLogic(
            onGrantPermission = { fail(grantedUnhappyPathFailure) },
            onPermissionPermanentlyDenied = {
                hasReachedHappyPath = true
            },
            onRequirePermission = { _, launchPermission -> fail(deniedUnhappyPathFailure) },
            onShowRationale = { _, launchPermission -> fail(rationaleUnhappyPathFailure) },

        ),
        hasPreviouslyDeniedPermission = true,
        state = PermissionStateStubs.denied(permission),
    )

    @Test
    fun callingLaunchPermissionViaRequiredPermission() = performLogicFlow(
        logic = PermissionLogic(
            onGrantPermission = { fail(grantedUnhappyPathFailure) },
            onPermissionPermanentlyDenied = { fail(permanentlyDeniedUnhappyPathFailure) },
            onRequirePermission = { _, launchPermission ->
                launchPermission()
            },
            onShowRationale = { _, launchPermission -> fail(rationaleUnhappyPathFailure) },
        ),
        state = PermissionStateStubs.denied(permission) { hasReachedHappyPath = true },
    )

    @Test
    fun callingLaunchPermissionViaPermissionRationale() = performLogicFlow(
        logic = PermissionLogic(
            onGrantPermission = { fail(grantedUnhappyPathFailure) },
            onPermissionPermanentlyDenied = { fail(permanentlyDeniedUnhappyPathFailure) },
            onRequirePermission = { _, launchPermission -> fail(deniedUnhappyPathFailure) },
            onShowRationale = { _, launchPermission ->
                launchPermission()
            },
        ),
        state = PermissionStateStubs.rationale(permission) { hasReachedHappyPath = true },
    )

    private fun performLogicFlow(
        logic: PermissionLogic,
        state: PermissionState,
        hasPreviouslyDeniedPermission: Boolean = false,
    ) = runTest {
        composeTestRule.setContent {
            PermissionScreen(
                permissionState = state,
                hasPreviouslyDeniedPermission = hasPreviouslyDeniedPermission,
                logic = logic,
            )
        }

        assertTrue(hasReachedHappyPath)
    }
}
