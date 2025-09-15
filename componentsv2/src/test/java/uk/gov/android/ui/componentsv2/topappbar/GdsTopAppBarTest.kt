package uk.gov.android.ui.componentsv2.topappbar

import android.content.Context
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.core.app.ApplicationProvider
import kotlinx.collections.immutable.persistentListOf
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import uk.gov.android.ui.componentsv2.R
import uk.gov.android.ui.componentsv2.button.GdsIconButtonDefaults
import uk.gov.android.ui.componentsv2.button.IconButtonContent

@RunWith(RobolectricTestRunner::class)
class GdsTopAppBarTest {
    private val context: Context = ApplicationProvider.getApplicationContext()
    private val parameters = GdsTopAppBarPreviewProvider().values.toList()[0]
    private var onNavIconClick = false
    private var onFirstActionClick = false
    private var onSecondActionClick = false

    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setup() {
        onNavIconClick = false
        onFirstActionClick = false
        onSecondActionClick = false
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Test
    fun testContent() {
        composeTestRule.setContent {
            GdsTopAppBar(
                title = stringResource(R.string.top_app_bar_title),
                navigationButton = GdsIconButtonDefaults.defaultCloseContent,
                onClick = { onNavIconClick = !onNavIconClick },
                actions = persistentListOf(
                    TopBarActionButton(
                        content = IconButtonContent(
                            Icons.Default.MoreVert,
                            R.string.more_vert_icon_button,
                        ),
                    ) { onFirstActionClick = !onFirstActionClick },
                    TopBarActionButton(
                        content = IconButtonContent(
                            Icons.AutoMirrored.Filled.ArrowBack,
                            R.string.back_icon_button,
                        ),
                    ) { onSecondActionClick = !onSecondActionClick },
                ),
            )
        }

        composeTestRule.apply {
            onNodeWithContentDescription(
                context.getString(GdsIconButtonDefaults.defaultCloseContent.contentDescription),
            ).assertIsDisplayed().performClick()

            onNodeWithText(
                context.getString(R.string.top_app_bar_title),
            ).assertIsDisplayed()

            composeTestRule.apply {
                onNodeWithContentDescription(
                    context.getString(R.string.more_vert_icon_button),
                ).assertIsDisplayed().performClick()
            }

            composeTestRule.apply {
                onNodeWithContentDescription(
                    context.getString(R.string.back_icon_button),
                ).assertIsDisplayed().performClick()
            }

            assertTrue(onNavIconClick)
            assertTrue(onFirstActionClick)
            assertTrue(onSecondActionClick)
        }
    }

    @Test
    fun testPreviewContent() {
        composeTestRule.setContent {
            GdsTopAppBarPreview(parameters)
        }

        composeTestRule.apply {
            onNodeWithContentDescription(
                context.getString(GdsIconButtonDefaults.defaultCloseContent.contentDescription),
            ).assertIsDisplayed().assertHasClickAction()

            onNodeWithText(
                context.getString(R.string.top_app_bar_title),
            ).assertIsDisplayed()

            composeTestRule.apply {
                onNodeWithContentDescription(
                    context.getString(R.string.more_vert_icon_button),
                ).assertIsDisplayed().assertHasClickAction()
            }
        }
    }
}
