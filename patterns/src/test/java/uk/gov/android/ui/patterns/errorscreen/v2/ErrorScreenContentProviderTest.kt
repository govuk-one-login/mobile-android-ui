package uk.gov.android.ui.patterns.errorscreen.v2

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import kotlinx.collections.immutable.toImmutableList
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import uk.gov.android.ui.componentsv2.heading.GdsHeading
import uk.gov.android.ui.componentsv2.heading.GdsHeadingAlignment
import uk.gov.android.ui.componentsv2.images.GdsIcon
import uk.gov.android.ui.theme.m3.GdsTheme

@RunWith(RobolectricTestRunner::class)
class ErrorScreenContentProviderTest {
    @get:Rule
    val composeTestRule = createComposeRule()
    private val contentList = ErrorScreenContentProvider().values.toImmutableList()

    @Test
    fun testFirstItem() {
        val content = contentList[0]
        setupScreen(content)
        val title = hasText(content.title)
        composeTestRule
            .onNode(title)
            .assertIsDisplayed()
    }

    private fun setupScreen(content: ErrorScreenContent) {
        composeTestRule.setContent {
            GdsTheme {
                ErrorScreen(
                    icon = { horizontalPadding ->
                        GdsIcon(
                            image = ImageVector.vectorResource(content.icon.icon),
                            contentDescription = stringResource(content.icon.description),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = horizontalPadding),
                            color = colorScheme.onBackground,
                        )
                    },
                    title = { horizontalPadding ->
                        GdsHeading(
                            text = content.title,
                            modifier = Modifier
                                .padding(horizontal = horizontalPadding),
                            textAlign = GdsHeadingAlignment.CenterAligned,
                        )
                    },
                )
            }
        }
    }
}
