package uk.gov.android.ui.testwrapper.componentsv2.text

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import uk.gov.android.ui.componentsv2.text.GdsAnnotatedString
import uk.gov.android.ui.componentsv2.text.previewparameterprovider.GdsAnnotatedStringPreviewDataProvider
import uk.gov.android.ui.testwrapper.DemoTemplate
import uk.gov.android.ui.theme.spacingDouble

@Composable
fun GdsAnnotatedStringDemo(modifier: Modifier = Modifier) {
    val demoData = GdsAnnotatedStringPreviewDataProvider().values.toList()

    DemoTemplate {
        Column(
            verticalArrangement = Arrangement.spacedBy(spacingDouble),
            modifier = modifier.verticalScroll(rememberScrollState()),
        ) {
            demoData.forEach { parameters ->
                GdsAnnotatedString(
                    text = stringResource(parameters.text),
                    fontWeight = parameters.fontWeight,
                    icon = ImageVector.vectorResource(parameters.icon),
                    iconContentDescription = stringResource(parameters.iconContentDescription),
                    iconId = stringResource(parameters.iconId),
                    iconColor = if (parameters.iconColor !=
                        Color.Unspecified
                    ) {
                        parameters.iconColor
                    } else {
                        null
                    },
                    iconBackgroundColor = parameters.iconBackgroundColor,
                    isIconTrailing = parameters.isIconTrailing,
                    textStyle = parameters.textStyle,
                    color = if (parameters.color !=
                        Color.Unspecified
                    ) {
                        parameters.color
                    } else {
                        MaterialTheme.colorScheme.onBackground
                    },
                )
            }
        }
    }
}
