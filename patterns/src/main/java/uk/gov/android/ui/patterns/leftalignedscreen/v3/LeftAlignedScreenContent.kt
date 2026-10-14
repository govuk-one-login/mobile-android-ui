package uk.gov.android.ui.patterns.leftalignedscreen.v3

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.Dp
import kotlinx.collections.immutable.ImmutableList
import uk.gov.android.ui.componentsv2.heading.GdsHeadingAlignment
import uk.gov.android.ui.componentsv2.heading.GdsHeadingStyle
import uk.gov.android.ui.componentsv2.inputs.radio.GdsRadiosTitle
import uk.gov.android.ui.componentsv2.list.ListItem
import uk.gov.android.ui.componentsv2.list.ListTitle
import uk.gov.android.ui.componentsv2.row.RowData
import uk.gov.android.ui.theme.dividerThickness

internal data class LeftAlignedScreenContent(
    val title: String,
    val body: List<LeftAlignedScreenBody>? = null,
    val supportingText: String? = null,
    val primaryButton: String? = null,
    val secondaryButton: String? = null,
)

@Immutable
sealed class LeftAlignedScreenBody {
    data class SecondaryButton(
        val text: String,
        val onClick: () -> Unit,
        val showIcon: Boolean = false,
        val enabled: Boolean = true,
        val modifier: Modifier = Modifier,
    ) : LeftAlignedScreenBody()

    data class AnnotatedText(val text: AnnotatedString, val modifier: Modifier = Modifier) :
        LeftAlignedScreenBody()

    data class Text(val text: String, val modifier: Modifier = Modifier) :
        LeftAlignedScreenBody()

    data class Title(
        val text: String,
        val modifier: Modifier = Modifier,
        val style: GdsHeadingStyle = GdsHeadingStyle.LargeTitle,
        val textAlign: GdsHeadingAlignment = GdsHeadingAlignment.CenterAligned,
    ) : LeftAlignedScreenBody()

    data class Warning(val text: String, val modifier: Modifier = Modifier) :
        LeftAlignedScreenBody()

    data class BulletList(val items: ImmutableList<ListItem>, val title: ListTitle? = null) :
        LeftAlignedScreenBody()

    data class NumberedList(val list: ImmutableList<ListItem>, val title: ListTitle? = null) :
        LeftAlignedScreenBody()

    data class Image(
        val image: Int,
        val contentDescription: String,
        val modifier: Modifier = Modifier,
        val contentScale: ContentScale = ContentScale.FillWidth,
    ) : LeftAlignedScreenBody()

    data class Selection(
        val items: ImmutableList<String>,
        val selectedItem: Int?,
        val onItemSelected: (Int) -> Unit,
        val modifier: Modifier = Modifier,
        val title: GdsRadiosTitle? = null,
    ) : LeftAlignedScreenBody()

    data class Divider(
        val thickness: Dp = dividerThickness,
        val color: Color = Color.Unspecified,
        val modifier: Modifier = Modifier,
    ) : LeftAlignedScreenBody()

    data class RowList(val rowData: ImmutableList<RowData>) : LeftAlignedScreenBody()
}

data class LeftAlignedScreenButton(
    val text: String,
    val onClick: () -> Unit,
    val modifier: Modifier = Modifier.fillMaxWidth(),
    val enabled: Boolean = true,
)
