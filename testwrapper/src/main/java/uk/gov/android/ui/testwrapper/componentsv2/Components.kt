package uk.gov.android.ui.testwrapper.componentsv2

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import uk.gov.android.ui.componentsv2.camera.ImageProxyConverter
import uk.gov.android.ui.componentsv2.camera.qr.CentrallyCroppedImageProxyConverter
import uk.gov.android.ui.componentsv2.heading.GdsHeading
import uk.gov.android.ui.componentsv2.heading.GdsHeadingAlignment
import uk.gov.android.ui.componentsv2.heading.GdsHeadingStyle
import uk.gov.android.ui.patterns.camera.qr.ModifierExtensions.CANVAS_WIDTH_MULTIPLIER
import uk.gov.android.ui.testwrapper.DetailItem
import uk.gov.android.ui.testwrapper.componentsv2.camera.CameraContentDemo
import uk.gov.android.ui.testwrapper.componentsv2.dialogue.GdsDialogueDemo
import uk.gov.android.ui.testwrapper.componentsv2.inputs.radio.GdsRadiosDemo
import uk.gov.android.ui.testwrapper.componentsv2.list.GdsBulletedListDemo
import uk.gov.android.ui.testwrapper.componentsv2.list.GdsNumberedListDemo
import uk.gov.android.ui.testwrapper.componentsv2.status.StatusOverlayDemo
import uk.gov.android.ui.testwrapper.componentsv2.topappbar.GdsTopAppBarDemo
import uk.gov.android.ui.testwrapper.patterns.camera.qr.QrScannerScreenDemo
import uk.gov.android.ui.theme.smallPadding
import uk.gov.android.ui.theme.util.UnstableDesignSystemAPI

@OptIn(UnstableDesignSystemAPI::class)
@Composable
fun Components(
    modifier: Modifier = Modifier,
    onNavigate: (Any) -> Unit = {},
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
    ) {
        items(ComponentsDestination.entries()) { destination: ComponentsDestination ->
            GdsHeading(
                text = destination.label,
                modifier =
                Modifier
                    .fillMaxWidth()
                    .clickable(onClick = {
                        onNavigate(destination)
                    })
                    .padding(smallPadding),
                textAlign = GdsHeadingAlignment.LeftAligned,
                style = GdsHeadingStyle.Title3,
            )
            HorizontalDivider(color = Color.Black)
        }
    }
}

// Add new demo composables here
@Composable
fun ComponentDetail(
    detailItem: DetailItem,
    onNavigate: (Any) -> Unit = {},
) {
    when (detailItem.label) {
        BULLETED_LIST -> GdsBulletedListDemo()
        NUMBERED_LIST -> GdsNumberedListDemo()
        RADIO -> GdsRadiosDemo()
        TOP_APP_BAR -> GdsTopAppBarDemo()
        DIALOGUE -> GdsDialogueDemo()
        STATUS_OVERLAY -> StatusOverlayDemo()
        CAMERA_CONTENT -> CameraContentDemo()
        QR_CODE_CENTRALISED_SCANNING ->
            QrScannerScreenDemo(
                converter =
                CentrallyCroppedImageProxyConverter(
                    relativeScanningWidth = CANVAS_WIDTH_MULTIPLIER,
                    relativeScanningHeight = CANVAS_WIDTH_MULTIPLIER,
                ),
                onNavigate = onNavigate,
            )
        QR_CODE_SCANNING ->
            QrScannerScreenDemo(
                converter = ImageProxyConverter.simple(),
                onNavigate = onNavigate,
            )
    }
}

const val NUMBERED_LIST = "numList"
const val BULLETED_LIST = "bulList"
const val RADIO = "radio"
const val TOP_APP_BAR = "topAppBar"
const val DIALOGUE = "dialogue"
const val STATUS_OVERLAY = "StatusOverlay"

const val CAMERA_CONTENT = "Camera Content"
const val QR_CODE_CENTRALISED_SCANNING = "QR Scanning: Centrally cropping"
const val QR_CODE_SCANNING = "QR Scanning: Full image scanning"
