package uk.gov.android.ui.patterns.leftalignedscreen

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasContentDescription
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.performKeyInput
import androidx.compose.ui.test.pressKey
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.dropUnlessResumed
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import uk.gov.android.ui.componentsv2.button.ButtonType
import uk.gov.android.ui.componentsv2.button.GdsButton
import uk.gov.android.ui.componentsv2.heading.GdsHeading
import uk.gov.android.ui.componentsv2.heading.GdsHeadingAlignment
import uk.gov.android.ui.componentsv2.inputs.radio.GdsSelection
import uk.gov.android.ui.componentsv2.inputs.radio.RadioSelectionTitle
import uk.gov.android.ui.componentsv2.inputs.radio.TitleType
import uk.gov.android.ui.componentsv2.topappbar.GdsTopAppBar
import uk.gov.android.ui.patterns.R
import uk.gov.android.ui.patterns.dialog.FullScreenDialogue
import uk.gov.android.ui.theme.m3.GdsTheme

class LeftAlignedScreenKeyboardNavTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    private val titleTag = "textItem1"
    private val firstButtonTag = "firstButton"
    private val firstButton = composeTestRule.onNode(hasTestTag(firstButtonTag))
    private val closeButton = composeTestRule.onNode(hasContentDescription("Back"))
    private val contentTag = "contentTag"
    private val content = composeTestRule.onNode(hasTestTag(contentTag))
    private val firstParaTag = "firstPara"

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun normalFontSize() {
        applyComposable(isLargeFont = false)

        content.performKeyInput {
            pressKey(Key.DirectionDown)
            pressKey(Key.DirectionDown)
        }

        firstButton.assertIsDisplayed()
        closeButton.assertIsDisplayed()
    }

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun largeFontSize() = runTest {
        applyComposable(isLargeFont = true)

        composeTestRule.waitUntilAtLeastOneExists(hasTestTag(titleTag))

        content.performKeyInput {
            pressKey(Key.DirectionDown)
            pressKey(Key.DirectionUp)
        }

        closeButton.assertIsDisplayed()
    }

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    private fun applyComposable(isLargeFont: Boolean) {
        composeTestRule.setContent {
            Content(isLargeFont)
        }
    }

    @SuppressLint("ComposeUnstableReceiver")
    @Suppress("LongMethod")
    @Composable
    @OptIn(ExperimentalMaterial3Api::class)
    private fun Content(isLargeFont: Boolean) {
        GdsTheme {
            FullScreenDialogue(
                topAppBar = {
                    GdsTopAppBar()
                },
                content = {
                    var selectedItem by rememberSaveable { mutableStateOf<Int?>(null) }
                    LeftAlignedScreen(
                        title = { horizontalPadding ->
                            GdsHeading(
                                text = "Do you have a passport with a biometric chip?",
                                textAlign = GdsHeadingAlignment.LeftAligned,
                                modifier = Modifier
                                    .padding(horizontal = horizontalPadding)
                                    .testTag(titleTag),
                            )
                        },
                        body = { horizontalPadding ->
                            item {
                                Text(
                                    text = "All UK passports have a biometric chip.\n\nIf you " +
                                        "have a non-UK passport, it must have the biometric " +
                                        "chip symbol on the cover.",
                                    modifier = if (isLargeFont) {
                                        Modifier
                                            .padding(horizontal = horizontalPadding)
                                            .sizeIn(minHeight = 720.dp)
                                    } else {
                                        Modifier.padding(horizontal = horizontalPadding)
                                    }.testTag(firstParaTag),
                                    color = MaterialTheme.colorScheme.onBackground,
                                )
                            }
                            item {
                                Image(
                                    painter = painterResource(R.drawable.preview__gdsvectorimage),
                                    contentDescription = "contentDescription",
                                    modifier = Modifier.fillMaxWidth(),
                                    contentScale = ContentScale.FillWidth,
                                )
                            }
                            item {
                                Text(
                                    text = "You cannot use your passport if it has expired.",
                                    modifier = Modifier.padding(horizontal = horizontalPadding),
                                    color = MaterialTheme.colorScheme.onBackground,
                                )
                            }
                            item {
                                GdsButton(
                                    text = "Read more about the types of photo ID you can use",
                                    buttonType = ButtonType.Secondary,
                                    onClick = dropUnlessResumed { },
                                    modifier = Modifier
                                        .padding(horizontal = horizontalPadding)
                                        .testTag(firstButtonTag),
                                    contentModifier = Modifier,
                                    textAlign = TextAlign.Left,
                                    contentPosition = Arrangement.Start,
                                )
                            }
                            item {
                                GdsSelection(
                                    title =
                                    RadioSelectionTitle(
                                        "Do you have a passport with a biometric chip?",
                                        TitleType.Heading,
                                    ),
                                    items =
                                    listOf("Yes", "No, use a different type of photo ID")
                                        .toPersistentList(),
                                    selectedItem = selectedItem,
                                    onItemSelected = {
                                        selectedItem = it
                                    },
                                )
                            }
                        },
                        primaryButton = {
                            GdsButton(
                                text = "Continue",
                                buttonType = ButtonType.Primary,
                                onClick =
                                dropUnlessResumed {
                                },
                                enabled = selectedItem != null,
                                modifier = Modifier.fillMaxWidth(),
                            )
                        },
                        modifier = Modifier
                            .padding(bottom = 60.dp)
                            .testTag(contentTag),
                        forceScroll = true,
                    )
                },
            )
        }
    }
}
