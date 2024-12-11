package uk.gov.android.ui.pages.modal

import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.buildAnnotatedString

data class BulletedTextParameters(
    val modifier: Modifier = Modifier,
    val header: AnnotatedString = buildAnnotatedString {},
    val bullets: List<String>,
    val footer: AnnotatedString = buildAnnotatedString {},
)
