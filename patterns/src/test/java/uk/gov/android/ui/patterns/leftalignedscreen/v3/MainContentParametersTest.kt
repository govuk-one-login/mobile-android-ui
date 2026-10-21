package uk.gov.android.ui.patterns.leftalignedscreen.v3

import androidx.compose.foundation.layout.Column
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.text.buildAnnotatedString
import kotlinx.collections.immutable.persistentListOf
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import uk.gov.android.ui.componentsv2.list.ListItem
import uk.gov.android.ui.componentsv2.row.RowData
import uk.gov.android.ui.patterns.leftalignedscreen.v3.MainContentParametersProvider.Companion.BODY_TEXT
import uk.gov.android.ui.patterns.leftalignedscreen.v3.MainContentParametersProvider.Companion.IMAGE_DESCRIPTION
import uk.gov.android.ui.patterns.testutils.TestUtils

@RunWith(RobolectricTestRunner::class)
class MainContentParametersTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    private val content = MainContentParametersProvider()

    @Test
    fun displayDefaultTitle() {
        with(composeTestRule) {
            setContent {
                Column {
                    MainContentV2(
                        parameters = MainContentParameters.generateDefault(
                            title = TITLE,
                            body = null,
                        ),
                    )
                }
            }

            onNodeWithText(TITLE).assertIsDisplayed()
            onNodeWithText(BODY_TEXT).assertIsNotDisplayed()
            onNodeWithContentDescription(IMAGE_DESCRIPTION).assertIsNotDisplayed()
        }
    }

    @Test
    fun displayDefaultBody() {
        with(composeTestRule) {
            setContent {
                Column {
                    MainContentV2(
                        parameters = MainContentParameters.generateDefault(
                            title = null,
                            body = content.simpleBody(),
                        ),
                    )
                }
            }

            onNodeWithText(TITLE).assertIsNotDisplayed()
            onNodeWithText(BODY_TEXT).assertIsDisplayed()
            onNodeWithContentDescription(IMAGE_DESCRIPTION).assertIsDisplayed()
        }
    }

    @Test
    fun displayDefaultTitleWithBody() {
        with(composeTestRule) {
            setContent {
                Column {
                    MainContentV2(
                        parameters = MainContentParameters.generateDefault(
                            title = TITLE,
                            body = content.simpleBody(),
                        ),
                    )
                }
            }

            onNodeWithText(TITLE).assertIsDisplayed()
            onNodeWithText(BODY_TEXT).assertIsDisplayed()
            onNodeWithContentDescription(IMAGE_DESCRIPTION).assertIsDisplayed()
        }
    }

    // Renders a single body element through generateDefault so each GenerateDefault branch runs.
    private fun renderBody(body: LeftAlignedScreenBody) = with(composeTestRule) {
        setContent {
            Column {
                MainContentV2(
                    parameters = MainContentParameters.generateDefault(
                        title = null,
                        body = persistentListOf(body),
                    ),
                )
            }
        }
    }

    // --- LeftAlignedScreenBody.GenerateDefault branch coverage ---

    @Test
    fun bodyTextIsDisplayed() {
        renderBody(LeftAlignedScreenBody.Text(BODY))
        composeTestRule.onNodeWithText(BODY).assertIsDisplayed()
    }

    @Test
    fun bodyAnnotatedTextIsDisplayed() {
        renderBody(
            LeftAlignedScreenBody.AnnotatedText(buildAnnotatedString { append(BODY) }),
        )
        composeTestRule.onNodeWithText(BODY).assertIsDisplayed()
    }

    @Test
    fun bodyTitleIsDisplayed() {
        renderBody(LeftAlignedScreenBody.Title(text = TITLE_ITEM))
        composeTestRule.onNodeWithText(TITLE_ITEM).assertIsDisplayed()
    }

    @Test
    fun bodyWarningIsDisplayed() {
        renderBody(LeftAlignedScreenBody.Warning(text = WARNING))
        composeTestRule.onNodeWithText(WARNING).assertIsDisplayed()
    }

    @Test
    fun bodyBulletListIsDisplayed() {
        renderBody(
            LeftAlignedScreenBody.BulletList(
                persistentListOf(ListItem(BULLET_ONE), ListItem(BULLET_TWO)),
            ),
        )
        with(composeTestRule) {
            onNodeWithText(BULLET_ONE).assertIsDisplayed()
            onNodeWithText(BULLET_TWO).assertIsDisplayed()
        }
    }

    @Test
    fun bodyNumberedListIsDisplayed() {
        renderBody(
            LeftAlignedScreenBody.NumberedList(
                persistentListOf(ListItem(BULLET_ONE), ListItem(BULLET_TWO)),
            ),
        )
        with(composeTestRule) {
            onNodeWithText(BULLET_ONE).assertIsDisplayed()
            onNodeWithText(BULLET_TWO).assertIsDisplayed()
        }
    }

    @Test
    fun bodySelectionIsDisplayed() {
        renderBody(
            LeftAlignedScreenBody.Selection(
                items = persistentListOf(RADIO_ONE, RADIO_TWO),
                selectedItem = null,
                onItemSelected = {},
            ),
        )
        with(composeTestRule) {
            onNodeWithText(RADIO_ONE, useUnmergedTree = true).assertIsDisplayed()
            onNodeWithText(RADIO_TWO, useUnmergedTree = true).assertIsDisplayed()
        }
    }

    @Test
    fun bodyRowListIsDisplayed() {
        renderBody(
            LeftAlignedScreenBody.RowList(
                persistentListOf(RowData(title = ROW_TITLE, onClick = {})),
            ),
        )
        composeTestRule.onNodeWithText(ROW_TITLE).assertIsDisplayed()
    }

    // Covers the Divider `takeUnless { it == Color.Unspecified }` fallback branch (default colour).
    @Test
    fun bodyDividerWithDefaultColorRenders() {
        renderBody(LeftAlignedScreenBody.Divider())
        // No text to assert on; verifies the branch renders without crashing.
    }

    // Covers the Divider branch where a specified colour is used instead of the fallback.
    @Test
    fun bodyDividerWithCustomColorRenders() {
        renderBody(LeftAlignedScreenBody.Divider(color = Color.Red))
    }

    @Test
    fun bodyImageIsDisplayed() {
        renderBody(
            LeftAlignedScreenBody.Image(
                image = uk.gov.android.ui.patterns.R.drawable.preview__gdsvectorimage,
                contentDescription = IMAGE_DESCRIPTION,
            ),
        )
        composeTestRule.onNodeWithContentDescription(IMAGE_DESCRIPTION).assertIsDisplayed()
    }

    @Test
    fun bodySecondaryButtonWithoutIconIsDisplayed() {
        renderBody(
            LeftAlignedScreenBody.SecondaryButton(text = SECONDARY_BUTTON, onClick = {}),
        )
        composeTestRule.onNodeWithText(SECONDARY_BUTTON)
            .assertIsDisplayed()
            .assertHasClickAction()
    }

    @Test
    fun bodySecondaryButtonWithIconIsDisplayed() {
        val iconDescription = TestUtils.getString(
            uk.gov.android.ui.componentsv2.R.string.opens_in_external_browser,
        )
        renderBody(
            LeftAlignedScreenBody.SecondaryButton(
                text = SECONDARY_BUTTON,
                onClick = {},
                showIcon = true,
            ),
        )
        // The icon branch adds a trailing "opens in external browser" icon; asserting its content
        // description verifies showIcon = true actually produced the icon.
        composeTestRule.onNodeWithContentDescription(iconDescription, useUnmergedTree = true)
            .assertExists()
    }

    @Test
    fun bodySecondaryButtonClickInvokesOnClick() {
        var clicked = false
        renderBody(
            LeftAlignedScreenBody.SecondaryButton(
                text = SECONDARY_BUTTON,
                onClick = { clicked = true },
            ),
        )
        composeTestRule.onNodeWithText(SECONDARY_BUTTON).performClick()
        assertTrue(clicked)
    }

    @Test
    fun bodySecondaryButtonDisabledIsNotEnabled() {
        renderBody(
            LeftAlignedScreenBody.SecondaryButton(
                text = SECONDARY_BUTTON,
                onClick = {},
                enabled = false,
            ),
        )
        composeTestRule.onNodeWithText(SECONDARY_BUTTON)
            .assertIsDisplayed()
            .assertIsNotEnabled()
    }

    companion object {
        private const val TITLE = "Do you have a UK passport or passport with a biometric chip?"
        private const val BODY = "Body text content"
        private const val TITLE_ITEM = "Title item"
        private const val WARNING = "Warning text"
        private const val BULLET_ONE = "Bullet one"
        private const val BULLET_TWO = "Bullet two"
        private const val RADIO_ONE = "Radio one"
        private const val RADIO_TWO = "Radio two"
        private const val ROW_TITLE = "Row title"
        private const val SECONDARY_BUTTON = "Secondary button"
    }
}
