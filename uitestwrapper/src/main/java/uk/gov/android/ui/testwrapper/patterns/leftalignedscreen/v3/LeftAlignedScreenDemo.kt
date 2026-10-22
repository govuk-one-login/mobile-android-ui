package uk.gov.android.ui.testwrapper.patterns.leftalignedscreen.v3

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.Dp
import kotlinx.collections.immutable.persistentListOf
import uk.gov.android.ui.componentsv2.button.ButtonTypeV2
import uk.gov.android.ui.componentsv2.button.GdsButton
import uk.gov.android.ui.componentsv2.heading.GdsHeading
import uk.gov.android.ui.componentsv2.heading.GdsHeadingAlignment
import uk.gov.android.ui.componentsv2.heading.GdsHeadingStyle
import uk.gov.android.ui.componentsv2.supportingtext.GdsSupportingText
import uk.gov.android.ui.patterns.R
import uk.gov.android.ui.patterns.leftalignedscreen.v3.BottomContentBehaviour
import uk.gov.android.ui.patterns.leftalignedscreen.v3.BottomContentConfig
import uk.gov.android.ui.patterns.leftalignedscreen.v3.LeftAlignedScreenBody
import uk.gov.android.ui.patterns.leftalignedscreen.v3.LeftAlignedScreenV3
import uk.gov.android.ui.patterns.leftalignedscreen.v3.MainContentParameters.Companion.GenerateDefault
import uk.gov.android.ui.theme.m3.Backgrounds
import uk.gov.android.ui.theme.m3.ExtraTypography
import uk.gov.android.ui.theme.m3.toMappedColors
import uk.gov.android.ui.theme.spacingTriple

@SuppressLint("ComposeModifierMissing")
@Composable
fun LeftAlignedScreenFixedDemo() {
    LeftAlignedScreenV3(
        title = titleSlot,
        body = bodySlot,
        supportingText = supportingTextSlot,
        primaryButton = primaryButtonSlot,
        secondaryButton = secondaryButtonSlot,
        bottomContentBehaviour = BottomContentBehaviour.Fixed,
        bottomContentConfig = BottomContentConfig(
            backgroundColor = Backgrounds.card.toMappedColors(),
        ),
        backgroundColor = Backgrounds.screen.toMappedColors(),
    )
}

@SuppressLint("ComposeModifierMissing")
@Composable
fun LeftAlignedScreenAdaptiveDemo() {
    LeftAlignedScreenV3(
        title = titleSlot,
        body = bodySlot,
        supportingText = supportingTextSlot,
        primaryButton = primaryButtonSlot,
        secondaryButton = secondaryButtonSlot,
        bottomContentBehaviour = BottomContentBehaviour.Adaptive,
        bottomContentConfig = BottomContentConfig(
            backgroundColor = Backgrounds.card.toMappedColors(),
        ),
        backgroundColor = Backgrounds.screen.toMappedColors(),
    )
}

private val titleSlot: @Composable (horizontalPadding: Dp) -> Unit = { horizontalPadding ->
    GdsHeading(
        text = "Left Aligned Screen",
        modifier = Modifier.padding(horizontal = horizontalPadding),
        textAlign = GdsHeadingAlignment.LeftAligned,
    )
}

private val bodySlot: LazyListScope.(horizontalItemPadding: Dp) -> Unit = { horizontalItemPadding ->
    body.forEach { item { it.GenerateDefault(horizontalItemPadding) } }
}

private val supportingTextSlot: @Composable (horizontalPadding: Dp) -> Unit = { horizontalPadding ->
    GdsSupportingText(
        text = "Check if your passport has a biometric chip, look for the " +
            "rectangular biometric chip symbol on the front cover",
        modifier = Modifier.padding(horizontal = horizontalPadding),
    )
}

private val primaryButtonSlot: @Composable () -> Unit = {
    GdsButton(
        text = "Primary Button",
        onClick = {},
        buttonType = ButtonTypeV2.Primary(),
        modifier = Modifier.fillMaxWidth(),
    )
}

private val secondaryButtonSlot: @Composable () -> Unit = {
    GdsButton(
        text = "Secondary Button",
        onClick = {},
        buttonType = ButtonTypeV2.Secondary(),
        modifier = Modifier.fillMaxWidth(),
    )
}

private val body = persistentListOf(
    LeftAlignedScreenBody.Title(
        text = "Title2 - Left Aligned",
        style = GdsHeadingStyle.Title2,
        textAlign = GdsHeadingAlignment.LeftAligned,
    ),
    LeftAlignedScreenBody.AnnotatedText(
        buildAnnotatedString {
            append(LoremIpsum(5).values.first())
            withStyle(
                SpanStyle(fontFamily = ExtraTypography.bodyLargeBold.fontFamily),
            ) {
                append(" 26 June 2024 at 2:56pm (UK time)")
            }
            append(". ${LoremIpsum(5).values.first()}")
        },
    ),
    LeftAlignedScreenBody.Image(
        R.drawable.preview__gdsvectorimage,
        "Image description",
        Modifier
            .fillMaxWidth()
            .background(Color.Gray),
    ),
    LeftAlignedScreenBody.Text(
        LoremIpsum(5).values.first(),
        modifier = Modifier.padding(
            top = spacingTriple,
        ),
    ),
    LeftAlignedScreenBody.Divider(),
    LeftAlignedScreenBody.Warning(text = "Warning"),
    LeftAlignedScreenBody.SecondaryButton(
        text = "Secondary button",
        onClick = {},
    ),
    LeftAlignedScreenBody.SecondaryButton(
        text = "Yet another button",
        onClick = {},
    ),
)
