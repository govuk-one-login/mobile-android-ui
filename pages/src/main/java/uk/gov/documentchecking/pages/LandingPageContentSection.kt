package uk.gov.documentchecking.pages

import androidx.annotation.ArrayRes
import androidx.annotation.StringRes

data class LandingPageContentSection(
    @StringRes
    val subTitle: Int? = null,
    @ArrayRes
    val text: Int
)
