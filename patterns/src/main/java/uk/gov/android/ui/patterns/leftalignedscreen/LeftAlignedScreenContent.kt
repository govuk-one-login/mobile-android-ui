package uk.gov.android.ui.patterns.leftalignedscreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import kotlinx.collections.immutable.ImmutableList
import uk.gov.android.ui.componentsv2.bulletedlist.GdsBulletedList
import uk.gov.android.ui.componentsv2.button.ButtonType
import uk.gov.android.ui.componentsv2.button.GdsButton
import uk.gov.android.ui.componentsv2.supportingtext.GdsSupportingText
import uk.gov.android.ui.componentsv2.title.GdsTitle
import uk.gov.android.ui.componentsv2.warning.GdsWarning

internal data class LeftAlignedContent(
    val body: List<LeftAlignedBody>? = null,
    val supportingText: String? = null,
    val primaryButton: String? = null,
    val secondaryButton: String? = null,
)

sealed class LeftAlignedBody {
    data class Text(
        val text: String,
        val modifier: Modifier = Modifier,
    ) : LeftAlignedBody()

    data class Title(
        val text: String,
        val modifier: Modifier = Modifier,
    ) : LeftAlignedBody()

    data class Warning(
        val text: String,
        val modifier: Modifier = Modifier,
    ) : LeftAlignedBody()

    data class BulletList(val bullets: ImmutableList<String>) : LeftAlignedBody()

    data class Image(
        val image: Int,
        val contentDescription: String,
        val modifier: Modifier = Modifier,
    ) : LeftAlignedBody()
}

data class LeftAlignedScreenButton(
    val text: String,
    val onClick: () -> Unit,
)

@Composable
internal fun LeftAlignedScreenFromContentParams(content: LeftAlignedContent) {
    LeftAlignedScreen(
        body = {
            toBodyContent(content.body)
        },
        supportingText = content.supportingText?.let {
            { GdsSupportingText(it) }
        },
        primaryButton = content.primaryButton?.let {
            {
                GdsButton(
                    text = it,
                    onClick = {},
                    buttonType = ButtonType.Primary,
                    modifier = Modifier
                        .fillMaxWidth(),
                )
            }
        },
        secondaryButton = content.secondaryButton?.let {
            {
                GdsButton(
                    text = it,
                    onClick = {},
                    buttonType = ButtonType.Secondary,
                    modifier = Modifier
                        .fillMaxWidth(),
                )
            }
        },
    )
}

internal fun LazyListScope.toBodyContent(body: List<LeftAlignedBody>?) {
    body?.forEach {
        when (it) {
            is LeftAlignedBody.BulletList -> {
                item { GdsBulletedList(it.bullets) }
            }

            is LeftAlignedBody.Image -> {
                item {
                    Image(
                        painter = painterResource(it.image),
                        contentDescription = "",
                        modifier = it.modifier,
                    )
                }
            }

            is LeftAlignedBody.Text -> {
                item {
                    Text(
                        it.text,
                        it.modifier,
                        color = MaterialTheme.colorScheme.onBackground,
                        style = MaterialTheme.typography.bodyLarge,
                    )
                }
            }

            is LeftAlignedBody.Title -> {
                item {
                    GdsTitle(
                        it.text,
                        it.modifier,
                    )
                }
            }

            is LeftAlignedBody.Warning -> {
                item {
                    GdsWarning(
                        it.text,
                        it.modifier,
                    )
                }
            }
        }
    }
}
