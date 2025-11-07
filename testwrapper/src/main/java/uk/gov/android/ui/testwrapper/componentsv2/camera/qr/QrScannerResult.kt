package uk.gov.android.ui.testwrapper.componentsv2.camera.qr

import kotlinx.serialization.Serializable

@Serializable
data class QrScannerResult(
    val url: String,
)
