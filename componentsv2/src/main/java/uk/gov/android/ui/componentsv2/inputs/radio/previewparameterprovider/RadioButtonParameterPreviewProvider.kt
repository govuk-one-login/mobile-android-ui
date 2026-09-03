package uk.gov.android.ui.componentsv2.inputs.radio.previewparameterprovider

import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import kotlinx.collections.immutable.persistentListOf
import uk.gov.android.ui.componentsv2.heading.GdsHeadingStyle
import uk.gov.android.ui.componentsv2.inputs.radio.GdsRadiosTitle
import uk.gov.android.ui.componentsv2.inputs.radio.radiobuttonparameters.GdsRadioOptionItemPreviewData
import uk.gov.android.ui.componentsv2.inputs.radio.radiobuttonparameters.GdsRadiosPreviewData

internal class GdsRadiosPreviewDataProvider : PreviewParameterProvider<GdsRadiosPreviewData> {
    override val values: Sequence<GdsRadiosPreviewData> = sequenceOf(
        GdsRadiosPreviewData(
            items = persistentListOf(OPTION1),
            title = GdsRadiosTitle(EXAMPLE_TITLE, GdsHeadingStyle.Body),
        ),
        GdsRadiosPreviewData(
            items = persistentListOf(OPTION1, OPTION2),
            title = GdsRadiosTitle("Example Heading", GdsHeadingStyle.Title3),
            selectedIndex = 1,
        ),
        GdsRadiosPreviewData(
            items = persistentListOf(OPTION1, OPTION2),
            title = GdsRadiosTitle("Example Bold Title", GdsHeadingStyle.Body, FontWeight.Bold),
            selectedIndex = 0,
        ),
        GdsRadiosPreviewData(
            items = persistentListOf(OPTION1, OPTION2, "option three"),
            selectedIndex = 2,
        ),
        GdsRadiosPreviewData(
            items = persistentListOf(OPTION1, OPTION2, LONG_OPTION),
            title = GdsRadiosTitle(EXAMPLE_TITLE, GdsHeadingStyle.Body),
            selectedIndex = 1,
        ),
        GdsRadiosPreviewData(
            items = persistentListOf(
                "option one: Lorem ipsum dolor sit amet, consectetur adipiscing elit",
                "option two: Lorem ipsum dolor sit amet, consectetur adipiscing elit",
                "option three:Lorem ipsum dolor sit amet, consectetur adipiscing elit",
                "option four:Lorem ipsum dolor sit amet, consectetur adipiscing elit",
            ),
            title = GdsRadiosTitle(EXAMPLE_TITLE, GdsHeadingStyle.Body),
            selectedIndex = 3,
        ),
    )
}

internal class GdsRadioOptionItemProvider : PreviewParameterProvider<GdsRadioOptionItemPreviewData> {
    override val values: Sequence<GdsRadioOptionItemPreviewData> = sequenceOf(
        GdsRadioOptionItemPreviewData(
            text = OPTION1,
            isSelected = false,
            isFocused = true,
        ),
        GdsRadioOptionItemPreviewData(
            text = OPTION1,
            isSelected = true,
            isFocused = true,
        ),
    )
}


const val OPTION1 = "option one"
const val OPTION2 = "option two"
const val LONG_OPTION = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed " +
        "do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim " +
        "ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut " +
        "aliquip ex ea commodo consequat"
private const val EXAMPLE_TITLE = "Example Title"
