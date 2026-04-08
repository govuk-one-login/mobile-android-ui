package uk.gov.android.ui.testwrapper.patterns.error.v2

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import kotlinx.collections.immutable.persistentListOf
import uk.gov.android.ui.componentsv2.button.ButtonTypeV2
import uk.gov.android.ui.componentsv2.button.GdsButton
import uk.gov.android.ui.componentsv2.heading.GdsHeading
import uk.gov.android.ui.componentsv2.heading.GdsHeadingAlignment
import uk.gov.android.ui.componentsv2.images.GdsIcon
import uk.gov.android.ui.patterns.errorscreen.v2.ErrorScreen
import uk.gov.android.ui.componentsv2.R as componentsR
import uk.gov.android.ui.patterns.R as patternsR

@SuppressLint("ComposeModifierMissing")
@Composable
@Suppress("MagicNumber")
fun ErrorScreenDemo() {
    ErrorScreen(
        icon = { padding ->
            GdsIcon(
                image = ImageVector.vectorResource(patternsR.drawable.ic_warning_error),
                contentDescription = stringResource(patternsR.string.error_icon_description),
                modifier = Modifier.errorScreenDemo(padding),
            )
        },
        title = { padding ->
            GdsHeading(
                text = "Error Screen",
                modifier = Modifier.padding(padding),
                textAlign = GdsHeadingAlignment.CenterAligned,
            )
        },
        body = { padding ->
            items(bodyContent.slice(IntRange(0, 3)).size) { index ->
                Text(
                    text = bodyContent[index],
                    textAlign = TextAlign.Center,
                    modifier = Modifier.errorScreenDemo(padding),
                    color = MaterialTheme.colorScheme.onBackground,
                )
            }
            item {
                GdsHeading(
                    text = "Error Screen",
                    modifier = Modifier.errorScreenDemo(padding),
                    textAlign = GdsHeadingAlignment.CenterAligned,
                )
            }
            item {
                GdsButton(
                    text = "Content Button",
                    buttonType = ButtonTypeV2.Primary(),
                    onClick = {},
                    modifier = Modifier.fillMaxWidth(),
                )
            }
        },
        primaryButton = {
            val text = stringResource(componentsR.string.primary_button)
            GdsButton(
                text = text,
                buttonType = ButtonTypeV2.Primary(),
                onClick = {},
                modifier = Modifier.fillMaxWidth(),
            )
        },
    )
}

@SuppressLint("ComposeModifierMissing")
@Composable
fun ErrorScrollableScreenDemo() {
    ErrorScreen(
        icon = { padding ->
            GdsIcon(
                image = ImageVector.vectorResource(patternsR.drawable.ic_warning_error),
                contentDescription = stringResource(patternsR.string.error_icon_description),
                modifier = Modifier.errorScreenDemo(padding),
            )
        },
        title = { padding ->
            GdsHeading(
                text = "Error Screen",
                modifier = Modifier.errorScreenDemo(padding),
                textAlign = GdsHeadingAlignment.CenterAligned,
            )
        },
        body = { padding ->
            items(bodyContent.size) { index ->
                Text(
                    text = bodyContent[index],
                    textAlign = TextAlign.Center,
                    modifier = Modifier.errorScreenDemo(padding),
                    color = MaterialTheme.colorScheme.onBackground,
                )
            }
        },
        primaryButton = {
            val text = stringResource(componentsR.string.primary_button)
            GdsButton(
                text = text,
                buttonType = ButtonTypeV2.Primary(),
                onClick = {},
                modifier = Modifier.fillMaxWidth(),
            )
        },
    )
}

@SuppressLint("ComposeModifierMissing")
@Composable
fun ErrorBottomContentLargeScreenDemo() {
    ErrorScreen(
        icon = { padding ->
            GdsIcon(
                image = ImageVector.vectorResource(patternsR.drawable.ic_warning_error),
                contentDescription = stringResource(patternsR.string.error_icon_description),
                modifier = Modifier.errorScreenDemo(padding),
            )
        },
        title = { padding ->
            GdsHeading(
                text = "Error Screen",
                modifier = Modifier.errorScreenDemo(padding),
                textAlign = GdsHeadingAlignment.CenterAligned,
            )
        },
        body = { padding ->
            items(bodyContent.size) { index ->
                Text(
                    text = bodyContent[index],
                    textAlign = TextAlign.Center,
                    modifier = Modifier.errorScreenDemo(padding),
                    color = MaterialTheme.colorScheme.onBackground,
                )
            }
        },
        primaryButton = {
            val text = stringResource(componentsR.string.primary_button)
            GdsButton(
                text = text,
                buttonType = ButtonTypeV2.Primary(),
                onClick = {},
                modifier = Modifier.fillMaxWidth(),
            )
        },
        secondaryButton = {
            val text = stringResource(componentsR.string.secondary_button)
            GdsButton(
                text = text,
                buttonType = ButtonTypeV2.Secondary(),
                onClick = {},
                modifier = Modifier.fillMaxWidth(),
            )
        },
        tertiaryButton = {
            val text = stringResource(componentsR.string.tertiary_button)
            GdsButton(
                text = text,
                buttonType = ButtonTypeV2.Tertiary(),
                onClick = {},
                modifier = Modifier.fillMaxWidth(),
            )
        },
    )
}

private fun Modifier.errorScreenDemo(paddingValues: Dp) = this
    .padding(paddingValues)
    .fillMaxWidth()

private val bodyContent = persistentListOf(
    "Item 1",
    "This is a slightly longer description to test wrapping behaviour",
    "Short",
    "OK",
    "A medium length string here",
    "This is an even longer string that should definitely wrap across multiple lines on most screens",
    "Hello",
    "Something went wrong, please try again later",
    "Hi",
    "We could not verify your identity at this time",
    "Error",
    "Please check your internet connection and try again. If the problem persists, contact support",
    "Try again",
    "Unexpected failure",
    "A",
    "Your session has expired. Please sign in again to continue",
    "Not found",
    "This service is temporarily unavailable due to scheduled maintenance. We apologise for any inconvenience",
    "Retry",
    "An unknown error occurred while processing your request",
    "No connection",
    "We were unable to complete your request because the server did not respond in time. " +
        "Please check your network settings and try again",
    "Access denied",
)
