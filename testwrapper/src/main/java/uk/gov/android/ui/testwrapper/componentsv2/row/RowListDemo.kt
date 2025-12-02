package uk.gov.android.ui.testwrapper.componentsv2.row

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import kotlinx.collections.immutable.persistentListOf
import kotlinx.coroutines.launch
import uk.gov.android.ui.componentsv2.R
import uk.gov.android.ui.componentsv2.images.Image
import uk.gov.android.ui.componentsv2.row.RowData
import uk.gov.android.ui.componentsv2.row.RowList
import uk.gov.android.ui.componentsv2.row.RowTrailingIcon
import uk.gov.android.ui.componentsv2.status.GdsStatusOverlay
import uk.gov.android.ui.theme.spacingDouble

@Suppress("LongMethod")
@Composable
fun RowListDemo(
    modifier: Modifier = Modifier,
) {
    val statusOverlayState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    fun onClick(message: String) = scope.launch {
        statusOverlayState.showSnackbar(message)
    }

    var toggleableRowEnabled by remember { mutableStateOf(false) }
    val toggleableRowButtonStateText = if (toggleableRowEnabled) "enabled" else "disabled"

    val rowDataList = persistentListOf(
        RowData(
            title = "Title 1",
            onClick = { onClick("Row 1 clicked") },
        ),
        RowData(
            title = "Title 2",
            leadingImage = Image(
                drawable = R.drawable.placeholder_leading_image_portrait,
                contentDescription = "",
            ),
            onClick = { onClick("Row 2 clicked") },
        ),
        RowData(
            title = "Title 3",
            leadingImage = Image(
                drawable = R.drawable.placeholder_leading_image,
                contentDescription = "",
            ),
            trailingIcon = RowTrailingIcon.NavigateNext(),
            trailingText = "100+",
            subtitle = "Enabled button, no leading image content description, leading image doesn't scale with " +
                "font size, no trailing icon content description for navigate_next icon",
            onClick = { onClick("Row 3 clicked") },
        ),
        RowData(
            title = "Title 4",
            leadingImage = Image(
                drawable = R.drawable.placeholder_leading_image,
                contentDescription = "",
            ),
            scaleLeadingImageWithFontSize = true,
            trailingIcon = RowTrailingIcon.NavigateNext(Alignment.Top),
            trailingText = "100+",
            subtitle = "Enabled button, no leading image content description, leading image does scale with " +
                "font size, no trailing icon content description for navigate_next icon",
            onClick = { onClick("Row 4 clicked") },
        ),

        RowData(
            title = "Title 5",
            leadingImage = Image(
                drawable = R.drawable.placeholder_leading_image_portrait,
                contentDescription = "Portrait leading image",
            ),
            scaleLeadingImageWithFontSize = true,
            trailingIcon = RowTrailingIcon.NavigateNext(Alignment.Bottom),
            subtitle = "Enabled button, with leading image content description, leading image does scale with " +
                "font size, no trailing icon content description for navigate_next icon",
            onClick = { onClick("Row 5 clicked") },
        ),
        RowData(
            title = "Title 6",
            leadingImage = Image(
                drawable = R.drawable.placeholder_leading_image,
                contentDescription = "Leading image",
            ),
            scaleLeadingImageWithFontSize = true,
            trailingIcon = RowTrailingIcon.OpenInNew(),
            trailingText = "1000000",
            subtitle = "Enabled button, with leading image content description, leading image does scale with " +
                "font size, with trailing icon content description for open_in_new icon",
            onClick = { onClick("Row 6 clicked") },
        ),
        RowData(
            title = "Title 7",
            leadingImage = Image(
                drawable = R.drawable.placeholder_leading_image_portrait,
                contentDescription = "Portrait leading image",
            ),
            trailingIcon = RowTrailingIcon.OpenInNew(Alignment.Top),
            subtitle = "Enabled button, with leading image content description, leading image doesn't scale with " +
                "font size, with trailing icon content description for open_in_new icon. \n Click to toggle " +
                "Row 8 button state.",
            clickEnabled = true,
            onClick = {
                toggleableRowEnabled = !toggleableRowEnabled
                onClick("Row 7 clicked - Row 8 button state changed to enabled = $toggleableRowEnabled")
            },
        ),
        RowData(
            title = "Title 8",
            leadingImage = Image(
                drawable = R.drawable.placeholder_leading_image_portrait,
                contentDescription = "",
            ),
            scaleLeadingImageWithFontSize = true,
            trailingIcon = RowTrailingIcon.OpenInNew(Alignment.Bottom),
            subtitle = "Toggleable $toggleableRowButtonStateText button, no leading image content description, " +
                "leading image does scale with font size, with trailing icon content description " +
                "for open_in_new icon",
            clickEnabled = toggleableRowEnabled,
            onClick = { onClick("Row 8 clicked") },
        ),

    )
    Scaffold(
        snackbarHost = {
            GdsStatusOverlay(
                hostState = statusOverlayState,
                modifier = Modifier.padding(horizontal = spacingDouble),
            )
        },
    ) { paddingValues ->
        RowList(
            rows = rowDataList,
            modifier = modifier
                .padding(paddingValues = paddingValues),
        )
    }
}
