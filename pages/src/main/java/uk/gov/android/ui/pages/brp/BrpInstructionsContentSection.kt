package uk.gov.android.ui.pages.brp

import androidx.annotation.ArrayRes
import androidx.annotation.StringRes

data class BrpInstructionsContentSection(
    @StringRes
    val subTitle: Int? = null,
    @ArrayRes
    val text: Int,
)
