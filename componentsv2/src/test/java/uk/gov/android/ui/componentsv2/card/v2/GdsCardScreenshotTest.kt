package uk.gov.android.ui.componentsv2.card.v2

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.android.resources.NightMode
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import uk.gov.android.ui.componentsv2.BaseScreenshotTest
import uk.gov.android.ui.componentsv2.R
import uk.gov.android.ui.componentsv2.button.ButtonIcon
import uk.gov.android.ui.componentsv2.button.ButtonTypeV2
import uk.gov.android.ui.componentsv2.button.GdsButton
import uk.gov.android.ui.theme.dividerThickness
import uk.gov.android.ui.theme.m3.GdsLocalColorScheme
import uk.gov.android.ui.theme.m3.Typography
import uk.gov.android.ui.theme.smallPadding
import uk.gov.android.ui.theme.spacingDouble
import uk.gov.android.ui.theme.xsmallPadding

@RunWith(Parameterized::class)
internal class GdsCardScreenshotTest(private val parameters: Pair<CardPreview, NightMode>) :
    BaseScreenshotTest(parameters.second) {

    override val generateComposeLayout: @Composable () -> Unit = {
        when (parameters.first) {
            CardPreview.Default -> GdsCardPreview()

            CardPreview.Border -> GdsCardWithBorderPreview()

            CardPreview.Shadow -> LargeShadowCard()

            CardPreview.ImageDismissBodyPrimary -> VariantCard(
                showImage = true,
                showDismiss = true,
                caption = true,
                body = true,
                button = VariantButton.Primary,
            )

            CardPreview.ImageDividerSecondary -> VariantCard(
                showImage = true,
                divider = true,
                button = VariantButton.Secondary,
            )

            CardPreview.CaptionBodyPrimary -> VariantCard(
                caption = true,
                body = true,
                button = VariantButton.Primary,
            )

            CardPreview.CaptionPrimary -> VariantCard(
                caption = true,
                button = VariantButton.Primary,
            )

            CardPreview.CaptionOnly -> VariantCard(caption = true)

            CardPreview.DismissBodySecondaryNoIcon -> VariantCard(
                showDismiss = true,
                caption = true,
                body = true,
                button = VariantButton.SecondaryNoIcon,
            )

            CardPreview.SmallTitleShadowPrimary -> VariantCard(
                showDismiss = true,
                caption = true,
                body = true,
                titleStyle = Typography.titleSmall,
                button = VariantButton.Primary,
                modifier = Modifier.gdsCardShadow(),
            )

            CardPreview.DismissBodyPrimary -> VariantCard(
                showDismiss = true,
                caption = true,
                body = true,
                button = VariantButton.Primary,
            )

            CardPreview.LargeTitleDividerArrowSecondary -> VariantCard(
                showDismiss = true,
                caption = true,
                body = true,
                titleStyle = Typography.headlineLarge,
                divider = true,
                button = VariantButton.SecondaryArrow,
            )

            CardPreview.CaptionSecondary -> VariantCard(
                caption = true,
                button = VariantButton.Secondary,
            )
        }
    }

    /**
     * Composes the content of a [GdsCard] to match the example variants in the Figma design. The
     * card applies no padding of its own, so every variant is built here from the slot, which shows
     * the card can support all of them.
     */
    @Composable
    @Suppress("LongParameterList", "LongMethod")
    private fun VariantCard(
        modifier: Modifier = Modifier,
        showImage: Boolean = false,
        showDismiss: Boolean = false,
        caption: Boolean = false,
        body: Boolean = false,
        titleStyle: TextStyle = Typography.headlineMedium,
        divider: Boolean = false,
        button: VariantButton? = null,
    ) {
        GdsCard(modifier = Modifier.padding(spacingDouble).then(modifier)) {
            Box {
                Column {
                    if (showImage) {
                        // Edge-to-edge image above the padded content
                        Image(
                            painter = painterResource(R.drawable.ic_tile_image),
                            contentDescription = stringResource(
                                R.string.vector_image_content_description,
                            ),
                            contentScale = ContentScale.FillWidth,
                            modifier = Modifier.fillMaxWidth(),
                        )
                    }
                    Column(modifier = Modifier.padding(horizontal = smallPadding)) {
                        if (caption) {
                            Text(
                                text = stringResource(R.string.caption),
                                style = Typography.bodySmall,
                                modifier = Modifier.padding(top = xsmallPadding),
                            )
                        }
                        Text(
                            text = stringResource(R.string.title),
                            style = titleStyle,
                            modifier = Modifier.padding(vertical = xsmallPadding),
                        )
                        if (body) {
                            Text(
                                text = stringResource(R.string.body),
                                style = Typography.bodyLarge,
                                modifier = Modifier.padding(vertical = xsmallPadding),
                            )
                        }
                        if (divider) {
                            HorizontalDivider(
                                thickness = dividerThickness,
                                color = GdsLocalColorScheme.current.dividerCard,
                            )
                        }
                        button?.let { VariantButtonContent(it) }
                    }
                }
                if (showDismiss) {
                    IconButton(
                        onClick = {},
                        modifier = Modifier.align(Alignment.TopEnd),
                    ) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = stringResource(R.string.close_icon_button),
                            tint = GdsLocalColorScheme.current.topBarIcon,
                        )
                    }
                }
            }
        }
    }

    @Composable
    private fun VariantButtonContent(button: VariantButton) {
        when (button) {
            VariantButton.Primary -> GdsButton(
                text = stringResource(R.string.primary_button),
                buttonType = ButtonTypeV2.Primary(),
                onClick = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = xsmallPadding, bottom = smallPadding),
                contentModifier = Modifier.fillMaxWidth(),
            )

            else -> GdsButton(
                text = stringResource(R.string.secondary_button),
                buttonType = ButtonTypeV2.Secondary(),
                onClick = {},
                modifier = Modifier.fillMaxWidth(),
                contentPosition = Arrangement.Start,
                contentModifier = Modifier.fillMaxWidth(),
                icon = when (button) {
                    VariantButton.Secondary -> ButtonIcon(
                        icon = ImageVector.vectorResource(R.drawable.ic_external_site),
                        contentDescription = stringResource(R.string.opens_in_external_browser),
                    )

                    VariantButton.SecondaryArrow -> ButtonIcon(
                        icon = ImageVector.vectorResource(R.drawable.arrow_forward),
                        contentDescription = stringResource(R.string.icon_content_desc),
                    )

                    else -> null
                },
            )
        }
    }

    private enum class VariantButton {
        Primary,
        Secondary,
        SecondaryNoIcon,
        SecondaryArrow,
    }

    /**
     * The card's real shadow is only 1.dp, which is barely visible in a snapshot. This uses a
     * much larger elevation purely so the shadow is clearly visible - it is not a design value.
     */
    @Composable
    private fun LargeShadowCard() {
        // A fixed light backdrop is used in light and dark mode as a shadow is invisible on the
        // dark snapshot background
        Box(modifier = Modifier.background(Color.White)) {
            GdsCard(
                // Padding must exceed the shadow's reach so it isn't clipped when the layout shrinks
                modifier = Modifier.padding(SHADOW_PADDING),
                elevation = CardDefaults.cardElevation(defaultElevation = LARGE_ELEVATION),
            ) {
                Text(
                    text = "A card with a large shadow.",
                    modifier = Modifier.padding(CONTENT_PADDING),
                )
            }
        }
    }

    internal enum class CardPreview {
        Default,
        Border,
        Shadow,
        ImageDismissBodyPrimary,
        ImageDividerSecondary,
        CaptionBodyPrimary,
        CaptionPrimary,
        CaptionOnly,
        DismissBodySecondaryNoIcon,
        SmallTitleShadowPrimary,
        DismissBodyPrimary,
        LargeTitleDividerArrowSecondary,
        CaptionSecondary,
    }

    companion object {
        private val LARGE_ELEVATION = 40.dp
        private val SHADOW_PADDING = 80.dp
        private val CONTENT_PADDING = 32.dp

        @JvmStatic
        @Parameterized.Parameters(name = "{index}GdsCard")
        fun values(): List<Pair<CardPreview, NightMode>> {
            val result: MutableList<Pair<CardPreview, NightMode>> = mutableListOf()

            CardPreview.entries.forEach(applyNightMode(result))

            return result
        }
    }
}
