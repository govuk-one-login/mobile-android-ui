package uk.gov.android.ui.componentsv2.dialogue

import android.content.Context
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.core.app.ApplicationProvider
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import uk.gov.android.ui.componentsv2.R
import uk.gov.android.ui.componentsv2.button.ButtonTypeV2
import uk.gov.android.ui.theme.m3.GdsTheme

@RunWith(RobolectricTestRunner::class)
class GdsDialogueTest {
    private val context: Context = ApplicationProvider.getApplicationContext()
    private val resources = context.resources
    private var onDismissButtonTapped = 0

    private val oneButtonSetup = persistentListOf(
        DialogueButtonParameters(
            buttonType = ButtonTypeV2.Primary(),
            text = BUTTON_TEXT_YES,
            onClick = { onDismissButtonTapped++ },
        ),
    )

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun textDisplayed() {
        setupDialog(oneButtonSetup)
        composeTestRule.apply {
            onNodeWithText(
                TITLE1,
            ).apply {
                assertIsDisplayed()
            }

            onNodeWithText(
                resources.getString(R.string.dialog_example_content),
            ).apply {
                assertIsDisplayed()
            }
        }
    }

    @Test
    fun verifyOnButtonTappedInvoked() {
        setupDialog(oneButtonSetup)
        composeTestRule.onNodeWithText(
            BUTTON_TEXT_YES,
            useUnmergedTree = true,
        ).performClick()
        assertEquals(1, onDismissButtonTapped)
    }

    @Test
    fun testPreview() {
        composeTestRule.setContent {
            GdsDialoguePreview(
                DialogueParameters(
                    headingText = TITLE2,
                    contentText = R.string.dialog_example_content,
                    buttonParameters = persistentListOf(
                        DialogueButtonParameters(
                            buttonType = ButtonTypeV2.Secondary(),
                            text = BUTTON_TEXT_CONFIRM,
                        ),
                        DialogueButtonParameters(
                            buttonType = ButtonTypeV2.Secondary(),
                            text = BUTTON_TEXT_DISMISS,
                        ),
                    ),
                ),
            )
        }
        composeTestRule.apply {
            onNodeWithText(
                TITLE2,
            ).apply {
                assertIsDisplayed()
            }

            onNodeWithText(
                resources.getString(R.string.dialog_example_content),
            ).apply {
                assertIsDisplayed()
            }
        }
    }

    private fun setupDialog(
        buttonList: ImmutableList<DialogueButtonParameters>,
    ) {
        composeTestRule.setContent {
            GdsTheme {
                GdsDialogue(
                    headingText = TITLE1,
                    contentText = R.string.dialog_example_content,
                    onDismissRequest = { onDismissButtonTapped++ },
                    buttonParameters = buttonList,
                )
            }
        }
    }
}
