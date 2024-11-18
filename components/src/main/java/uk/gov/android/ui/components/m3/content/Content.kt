package uk.gov.android.ui.components.m3.content

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import uk.gov.android.ui.components.content.GdsContentText
import uk.gov.android.ui.components.m3.Heading
import uk.gov.android.ui.theme.inverseOnSurface
import uk.gov.android.ui.theme.m3.GdsTheme
import uk.gov.android.ui.theme.mc_theme_dark_inverseOnSurface

@Composable
@Suppress("LongMethod")
fun GdsContent(
    contentParameters: ContentParameters,
    colors: ColorScheme = MaterialTheme.colorScheme,
    changeBackgroundDefault: Boolean = false,
) {
    Column(
        modifier = Modifier
            .background(
                color = if (changeBackgroundDefault) {
                    inverseOnSurface(
                        darkMode = mc_theme_dark_inverseOnSurface,
                        lightMode = colors.background,
                    )
                } else {
                    colors.background
                },
            )
            .then(
                contentParameters.modifier,
            ),

    ) {
        contentParameters.apply {
            this.resource.forEach { contentText ->
                Column(
                    modifier = internalColumnModifier,
                ) {
                    contentText.subTitle?.let { subTitle ->
                        Heading(
                            modifier = headingModifier,
                            padding = headingPadding,
                            size = headingSize,
                            text = subTitle,
                            textAlign = textAlign,
                        ).generate()
                    }
                    contentText.subTitle2?.let { subTitle ->
                        Heading(
                            modifier = headingModifier,
                            padding = headingPadding,
                            size = subHeadingSize,
                            text = subTitle,
                            textVar = contentText.subTitle2Var,
                            textAlign = textAlign,
                        ).generate()
                    }
                    when (contentText) {
                        is GdsContentText.GdsContentTextString ->
                            contentText.text.map {
                                contentText.textVar?.let { arg ->
                                    stringResource(id = it, arg)
                                } ?: run {
                                    stringResource(id = it)
                                }
                            }.toTypedArray()

                        is GdsContentText.GdsContentTextArray ->
                            stringArrayResource(id = contentText.text)
                    }.forEach {
                        Text(
                            color = color ?: colors.contentColorFor(colors.background),
                            modifier = textModifier.padding(textPadding),
                            style = textStyle ?: MaterialTheme.typography.bodyLarge,
                            text = it,
                            textAlign = textAlign,
                        )
                    }
                }
            }
        }
    }
}

@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
)
@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Composable
private fun Preview(
    @PreviewParameter(ContentProvider::class)
    contentParameters: ContentParameters,
) {
    GdsTheme {
        GdsContent(
            contentParameters,
        )
    }
}
