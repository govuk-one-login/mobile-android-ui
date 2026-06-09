package uk.gov.android.ui.componentsv2.button

import androidx.compose.foundation.layout.Column
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.test.getBoundsInRoot
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import uk.gov.android.ui.componentsv2.R
import uk.gov.android.ui.theme.m3.ExtraTypography
import uk.gov.android.ui.theme.m3.GdsTheme

@RunWith(RobolectricTestRunner::class)
class GdsButtonSizeConsistencyTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun primaryButton_loadingPrimaryButton_andIconPrimaryButton_haveSameHeight() {
        composeTestRule.setContent {
            GdsTheme {
                Column {
                    GdsButton(
                        text = "Primary",
                        buttonType = ButtonTypeV2.Primary(),
                        onClick = {},
                        modifier = Modifier.testTag("primary"),
                    )
                    GdsButton(
                        text = "Primary Loading",
                        buttonType = ButtonTypeV2.Primary(),
                        onClick = {},
                        loading = true,
                        modifier = Modifier.testTag("primaryLoading"),
                    )
                    GdsButton(
                        text = "Icon Primary",
                        buttonType = ButtonTypeV2.Icon(
                            buttonColors = GdsButtonDefaults.defaultPrimaryColors(),
                            icon = ImageVector.vectorResource(R.drawable.ic_error_filled),
                            textStyle = ExtraTypography.bodyLargeBold,
                            contentDescription = "icon",
                            shadowColor = GdsButtonDefaults.defaultPrimaryColors().containerColor,
                        ),
                        onClick = {},
                        modifier = Modifier.testTag("iconPrimary"),
                    )
                }
            }
        }

        val primaryBounds = composeTestRule.onNodeWithTag("primary").getBoundsInRoot()
        val loadingBounds = composeTestRule.onNodeWithTag("primaryLoading").getBoundsInRoot()
        val iconPrimaryBounds = composeTestRule.onNodeWithTag("iconPrimary").getBoundsInRoot()

        val primaryHeight = primaryBounds.bottom - primaryBounds.top
        val loadingHeight = loadingBounds.bottom - loadingBounds.top
        val iconPrimaryHeight = iconPrimaryBounds.bottom - iconPrimaryBounds.top

        assertEquals(
            "Primary and Loading Primary buttons should have the same height",
            primaryHeight,
            loadingHeight,
        )
        assertEquals(
            "Primary and Icon Primary buttons should have the same height",
            primaryHeight,
            iconPrimaryHeight,
        )
    }
}
