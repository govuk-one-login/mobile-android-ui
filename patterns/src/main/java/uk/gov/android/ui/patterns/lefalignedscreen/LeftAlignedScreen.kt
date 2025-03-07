package uk.gov.android.ui.patterns.lefalignedscreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.SubcomposeLayout
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import uk.gov.android.ui.componentsv2.body.GdsBody
import uk.gov.android.ui.componentsv2.bulletedlist.GdsBulletedList
import uk.gov.android.ui.componentsv2.button.ButtonType
import uk.gov.android.ui.componentsv2.button.GdsButton
import uk.gov.android.ui.componentsv2.supportingtext.GdsSupportingText
import uk.gov.android.ui.componentsv2.title.GdsTitle
import uk.gov.android.ui.componentsv2.warning.GdsWarning
import uk.gov.android.ui.theme.m3.GdsTheme
import uk.gov.android.ui.theme.spacingDouble

private const val ONE_THIRD = 1f / 3f

/**
 * Left Aligned Screen
 *
 * This pattern displays the main content (title and body) which is scrollable.
 * The bottom content (supporting text, primary and secondary button) is fixed.
 * When the bottom content takes up more than 1/3 of the screen, the supporting text is moved into the body
 *
 * @param title The main title displayed at the top of the screen. Use of [GdsTitle] composable is recommended
 * @param modifier A [Modifier] to be applied to the root layout of the screen (optional).
 * @param body representing the main content Use of [GdsBody] composable is recommended (optional).
 * @param supportingText additional text displayed below in the bottom content. Use of [GdsSupportingText] composable is recommended (optional).
 * @param primaryButton primary action button. Use of [GdsButton] composable is recommended (optional).
 * @param secondaryButton secondary action button. Use of [GdsButton] composable is recommended (optional).
 */

@Composable
fun LeftAlignedScreen(
    title: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    body: (@Composable () -> Unit)? = null,
    supportingText: (@Composable () -> Unit)? = null,
    primaryButton: (@Composable () -> Unit)? = null,
    secondaryButton: (@Composable () -> Unit)? = null,
    arrangement: Arrangement.Vertical = Arrangement.spacedBy(
        spacingDouble,
        Alignment.CenterVertically,
    ),
) {
    val screenHeight = LocalConfiguration.current.screenHeightDp.dp
    val thresholdHeight = screenHeight * ONE_THIRD
    val density = LocalDensity.current

    Column(
        modifier,
        verticalArrangement = arrangement,
    ) {
        SubcomposeLayout { constraints ->
            // Measure BottomContent
            val bottomPlaceables = subcompose("bottom") {
                BottomContent(
                    modifier = Modifier.fillMaxWidth(),
                    primaryButton = primaryButton,
                    secondaryButton = secondaryButton,
                )
            }.map { it.measure(constraints) }
            val bottomContentHeight = bottomPlaceables.maxOfOrNull { it.height } ?: 0

            // Measure SupportingText
            val supportingTextPlaceables = subcompose("supportingText") {
                SupportingTextContainer { supportingText?.invoke() }
            }.map { it.measure(constraints) }
            val supportingTextHeight = supportingTextPlaceables.maxOfOrNull { it.height } ?: 0

            // Check if BottomContent + Supporting text exceeds 1/3 of the screen threshold
            val totalBottomContentHeight = bottomContentHeight + supportingTextHeight
            val bottomContentOverThreshold =
                with(density) { totalBottomContentHeight.toDp() } > thresholdHeight

            // Measure MainContent. Add SupportingText to MainContent if above threshold
            val mainPlaceables = subcompose("main") {
                MainContent(
                    title = title,
                    body = body,
                    supportingText = if (bottomContentOverThreshold) {
                        supportingText
                    } else {
                        null
                    },
                )
            }.map {
                val bottomContentSupportingTextHeight =
                    if (!bottomContentOverThreshold) supportingTextHeight else 0
                it.measure(
                    constraints.copy(
                        maxHeight = constraints.maxHeight - bottomContentHeight - bottomContentSupportingTextHeight,
                    ),
                )
            }

            layout(constraints.maxWidth, constraints.maxHeight) {
                var yPosition = 0

                mainPlaceables.forEach {
                    it.placeRelative(0, yPosition)
                    yPosition += it.height
                }

                if (!bottomContentOverThreshold) {
                    supportingTextPlaceables.forEach {
                        it.placeRelative(
                            0,
                            constraints.maxHeight - bottomContentHeight - supportingTextHeight,
                        )
                    }
                }

                bottomPlaceables.forEach {
                    it.placeRelative(0, constraints.maxHeight - bottomContentHeight)
                }
            }
        }
    }
}

@Composable
private fun MainContent(
    title: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    body: (@Composable () -> Unit)? = null,
    supportingText: (@Composable () -> Unit)? = null,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(
            spacingDouble,
            Alignment.CenterVertically,
        ),
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
    ) {
        title()

        body?.invoke()

        supportingText?.invoke()
    }
}

@Composable
private fun BottomContent(
    modifier: Modifier = Modifier,
    primaryButton: (@Composable () -> Unit)? = null,
    secondaryButton: (@Composable () -> Unit)? = null,
) {
    Column(
        modifier,
        verticalArrangement = Arrangement.spacedBy(spacingDouble),
    ) {
        primaryButton?.let {
            primaryButton()
        }

        secondaryButton?.let {
            secondaryButton()
        }
    }
}

@Composable
private fun SupportingTextContainer(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    Row(modifier = modifier.padding(vertical = spacingDouble)) {
        content()
    }
}

@PreviewLightDark
@Composable
internal fun PreviewLeftAlignedScreen(
    @PreviewParameter(LeftAlignedContentProvider::class)
    content: LeftAlignedContent,
) {
    GdsTheme {
        LeftAlignedScreen(
            title = { GdsTitle(content.title) },

            body = {
                GdsBody(
                    arrangement = Arrangement.spacedBy(spacingDouble),
                ) {
                    content.body?.forEach {
                        when (it) {
                            is Body.Image -> {
                                Image(
                                    painter = painterResource(it.image),
                                    contentDescription = "",
                                    modifier = it.modifier,
                                )
                            }

                            is Body.Text -> {
                                Text(
                                    it.text,
                                    it.modifier,
                                    color = MaterialTheme.colorScheme.onBackground,
                                    style = MaterialTheme.typography.bodyLarge,
                                )
                            }

                            is Body.BulletList -> {
                                GdsBulletedList(it.bullets)
                            }

                            is Body.Warning -> {
                                GdsWarning(it.text, modifier = it.modifier)
                            }
                        }
                    }
                }
            },

            supportingText = {
                content.supportingText?.let {
                    GdsSupportingText(
                        content.supportingText,
                        modifier = Modifier.padding(horizontal = spacingDouble),
                    )
                }
            },
            primaryButton = {
                content.primaryButton?.let {
                    GdsButton(
                        content.primaryButton,
                        ButtonType.Primary,
                        {},
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                    )
                }
            },
            secondaryButton = {
                content.primaryButton?.let {
                    GdsButton(
                        content.primaryButton,
                        ButtonType.Secondary,
                        {},
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                    )
                }
            },
        )
    }
}
