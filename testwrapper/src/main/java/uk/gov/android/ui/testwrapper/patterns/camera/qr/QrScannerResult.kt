package uk.gov.android.ui.testwrapper.patterns.camera.qr

import kotlinx.serialization.Serializable

@Serializable
data class QrScannerResult(
    val url: String,
)
