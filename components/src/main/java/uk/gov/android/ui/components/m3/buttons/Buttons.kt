package uk.gov.android.ui.components.m3.buttons

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.InlineTextContent
import androidx.compose.foundation.text.appendInlineContent
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.material3.minimumInteractiveComponentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.text.PlaceholderVerticalAlign
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.em
import uk.gov.android.ui.components.R
import uk.gov.android.ui.components.m3.images.icon.GdsIcon
import uk.gov.android.ui.theme.buttonContentHorizontal
import uk.gov.android.ui.theme.buttonContentVertical
import uk.gov.android.ui.theme.m3.GdsTheme
import uk.gov.android.ui.theme.smallPadding
import uk.gov.android.ui.theme.xsmallPadding

@Composable
fun GdsButton(
    buttonParameters: ButtonParameters,
) {
    with(buttonParameters) {
        Button(
            colors = buttonType.buttonColour(),
            modifier = modifier.then(
                Modifier
                    .minimumInteractiveComponentSize()
                    .semantics(mergeDescendants = true) { },
            ),
            onClick = onClick,
            shape = RectangleShape,
            enabled = isEnabled,
            contentPadding = PaddingValues(
                horizontal = buttonContentHorizontal,
                vertical = buttonContentVertical,
            ),
        ) {
            buttonContent(buttonParameters).invoke(this)
        }
    }
}

private fun buttonContent(
    parameters: ButtonParameters,
): @Composable RowScope.() -> Unit = {
    val iconId = stringResource(id = R.string.inLine__IconId)
    val annotatedString = buildAnnotatedString {
        if (parameters.isPrependIcon) {
            appendInlineContent(iconId, "[icon]")
        }
        append(parameters.text)
        if (parameters.isAppendIcon) {
            appendInlineContent(iconId, "[icon]")
        }
    }
    val icon = parameters.inlineIconContent
    val inlineContent: Map<String, InlineTextContent> = if (icon != null) {
        mapOf(iconId to icon)
    } else {
        emptyMap()
    }
    Text(
        text = annotatedString,
        inlineContent = inlineContent,
        fontWeight = parameters.buttonType.fontWeight,
        style = parameters.textStyle ?: MaterialTheme.typography.labelLarge,
        textAlign = parameters.textAlign,
    )
}

private val ButtonType.ICON.isAtEnd: Boolean
    get() = iconParameters.imagePositionAtEnd

private val ButtonParameters.isPrependIcon: Boolean
    get() = buttonType is ButtonType.ICON && !buttonType.isAtEnd

private val ButtonParameters.isAppendIcon: Boolean
    get() = buttonType is ButtonType.ICON && buttonType.isAtEnd

private val ButtonParameters.inlineIconContent: InlineTextContent?
    get() = if (buttonType is ButtonType.ICON) {
        InlineTextContent(
            Placeholder(
                width = 2.em,
                height = 1.em,
                placeholderVerticalAlign = PlaceholderVerticalAlign.TextCenter,
            ),
        ) {
            DisplayIcon(buttonType)
        }
    } else {
        null
    }

@Composable
private fun DisplayIcon(iconButtonType: ButtonType.ICON) {
    val modifier = if (iconButtonType.isAtEnd) {
        Modifier.padding(start = xsmallPadding)
    } else {
        Modifier.padding(end = smallPadding)
    }
    val fgColor = when (iconButtonType.parentButtonType) {
        is ButtonType.PRIMARY -> colorScheme.onPrimary
        is ButtonType.SECONDARY -> colorScheme.onSecondary
        is ButtonType.TERTIARY -> colorScheme.onTertiary
        is ButtonType.ERROR -> colorScheme.onError
        else -> colorScheme.primary
    }

    GdsIcon(
        parameters = iconButtonType.iconParameters.copy(
            foreGroundColor = fgColor,
            modifier = modifier.then(
                iconButtonType.iconParameters.modifier,
            ),
        ),
        colors = colorScheme,
    )
}

@Preview(uiMode = UI_MODE_NIGHT_NO)
@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun ButtonM3Preview(
    @PreviewParameter(ButtonProvider::class)
    buttonParameters: List<ButtonParameters>,
) {
    GdsTheme {
        Column {
            buttonParameters.forEach { param ->
                GdsButton(param)
            }
        }
    }
}
