package uk.gov.android.ui.componentsv2.button

import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.assertHeightIsEqualTo
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(Parameterized::class)
class GdsButtonSizeTest(
    private val params: TestParams,
) {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun buttonHeight() = runTest {
        composeTestRule.setContent {
            GdsButton(
                text = "Button",
                icon = if (params.icon) ButtonIcon.opensInWebBrowser() else null,
                buttonType = params.buttonType,
                loading = params.loading,
                enabled = params.enabled,
                onClick = {},
                modifier = Modifier.testTag(TEST_TAG),
            )
        }

        composeTestRule.onNodeWithTag(TEST_TAG)
            .assertHeightIsEqualTo(buttonHeight)
    }

    data class TestParams(
        val name: String,
        val buttonType: ButtonTypeV2,
        val icon: Boolean = false,
        val loading: Boolean = false,
        val enabled: Boolean = true,
    ) {
        override fun toString(): String = name
    }

    companion object {
        private val baseHeight = 48.dp
        private val shadowHeight = 2.dp
        private val buttonHeight = baseHeight + shadowHeight

        private const val TEST_TAG = "Button"

        @JvmStatic
        @Parameterized.Parameters(name = "{0}")
        fun parameters(): List<TestParams> = listOf(
            TestParams(name = "primary", buttonType = ButtonTypeV2.Primary()),
            TestParams(name = "primary with icon", buttonType = ButtonTypeV2.Primary(), icon = true),
            TestParams(name = "primary loading", buttonType = ButtonTypeV2.Primary(), loading = true),
            TestParams(name = "primary disabled", buttonType = ButtonTypeV2.Primary(), enabled = false),
            TestParams(name = "secondary", buttonType = ButtonTypeV2.Secondary()),
            TestParams(name = "secondary with icon", buttonType = ButtonTypeV2.Secondary(), icon = true),
            TestParams(name = "secondary loading", buttonType = ButtonTypeV2.Secondary(), loading = true),
            TestParams(name = "secondary disabled", buttonType = ButtonTypeV2.Secondary(), enabled = false),
        )
    }
}
