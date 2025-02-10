package uk.gov.android.ui.patterns.centrealignedscreen

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import uk.gov.android.ui.components.R
import uk.gov.android.ui.components.content.GdsContentText
import uk.gov.android.ui.components.m3.BulletListParameters

class ContentProvider : PreviewParameterProvider<Content> {
    override val values: Sequence<Content> = sequenceOf(
        Content(
            R.string.preview__GdsInformationBanner__title,
            ImageResource(
                R.drawable.preview__gdsvectorimage,
                R.string.preview__GdsVectorImage__description,
            ),
            Body(
                listOf(
                    BodyContent.Text(R.string.preview__GdsInformationBanner__content),
                    BodyContent.Text(R.string.preview__GdsContent__oneLine_0),
                    BodyContent.BulletList(
                        BulletListParameters(
                            title = R.string.preview__GdsHeading__subTitle1,
                            contentText = GdsContentText.GdsContentTextString(
                                text = arrayOf(
                                    R.string.preview__GdsContent__oneLine_0,
                                    R.string.preview__GdsContent__oneLine_0,
                                    R.string.preview__GdsContent__oneLine_0,
                                    R.string.preview__GdsContent__oneLine_0,
                                ).toIntArray(),
                            ),
                        ),
                    ),
                    BodyContent.Text(R.string.preview__GdsInformationBanner__content),
                ),
            ),
            R.string.preview__GdsContent__twoLine_0,
            R.string.preview__GdsButton__primary,
            R.string.preview__GdsButton__secondary,
        ),
        Content(
            R.string.preview__GdsInformationBanner__title,
            ImageResource(
                R.drawable.preview__gdsvectorimage,
                R.string.preview__GdsVectorImage__description,
            ),
            Body(
                listOf(
                    BodyContent.Text(R.string.preview__GdsInformationBanner__content),
                    BodyContent.Text(R.string.preview__GdsContent__oneLine_0),
                    BodyContent.BulletList(
                        BulletListParameters(
                            title = R.string.preview__GdsHeading__subTitle1,
                            contentText = GdsContentText.GdsContentTextString(
                                text = arrayOf(
                                    R.string.preview__GdsContent__oneLine_0,
                                    R.string.preview__GdsContent__oneLine_0,
                                    R.string.preview__GdsContent__oneLine_0,
                                    R.string.preview__GdsContent__oneLine_0,
                                ).toIntArray(),
                            ),
                        ),
                    ),
                    BodyContent.Text(R.string.preview__GdsInformationBanner__content),
                ),
            ),
            R.string.preview__GdsContent__twoLine_0,
            R.string.preview__GdsButton__primary,
        ),
        Content(
            R.string.preview__GdsInformationBanner__title,
            ImageResource(
                R.drawable.preview__gdsvectorimage,
                R.string.preview__GdsVectorImage__description,
            ),
            Body(
                listOf(
                    BodyContent.Text(R.string.preview__GdsInformationBanner__content),
                    BodyContent.Text(R.string.preview__GdsContent__oneLine_0),
                    BodyContent.BulletList(
                        BulletListParameters(
                            title = R.string.preview__GdsHeading__subTitle1,
                            contentText = GdsContentText.GdsContentTextString(
                                text = arrayOf(
                                    R.string.preview__GdsContent__oneLine_0,
                                    R.string.preview__GdsContent__oneLine_0,
                                    R.string.preview__GdsContent__oneLine_0,
                                    R.string.preview__GdsContent__oneLine_0,
                                ).toIntArray(),
                            ),
                        ),
                    ),
                    BodyContent.Text(R.string.preview__GdsInformationBanner__content),
                ),
            ),
            R.string.preview__GdsContent__twoLine_0,
        ),
        Content(
            R.string.preview__GdsInformationBanner__title,
            ImageResource(
                R.drawable.preview__gdsvectorimage,
                R.string.preview__GdsVectorImage__description,
            ),
            Body(
                listOf(
                    BodyContent.Text(R.string.preview__GdsInformationBanner__content),
                    BodyContent.Text(R.string.preview__GdsContent__oneLine_0),
                    BodyContent.BulletList(
                        BulletListParameters(
                            title = R.string.preview__GdsHeading__subTitle1,
                            contentText = GdsContentText.GdsContentTextString(
                                text = arrayOf(
                                    R.string.preview__GdsContent__oneLine_0,
                                    R.string.preview__GdsContent__oneLine_0,
                                    R.string.preview__GdsContent__oneLine_0,
                                    R.string.preview__GdsContent__oneLine_0,
                                ).toIntArray(),
                            ),
                        ),
                    ),
                    BodyContent.Text(R.string.preview__GdsInformationBanner__content),
                ),
            ),
        ),
        Content(
            R.string.preview__GdsInformationBanner__title,
            ImageResource(
                R.drawable.preview__gdsvectorimage,
                R.string.preview__GdsVectorImage__description,
            ),
            Body(
                listOf(
                    BodyContent.Text(R.string.preview__GdsInformationBanner__content),
                    BodyContent.Text(R.string.preview__GdsContent__oneLine_0),
                    BodyContent.BulletList(
                        BulletListParameters(
                            contentText = GdsContentText.GdsContentTextString(
                                text = arrayOf(
                                    R.string.preview__GdsContent__oneLine_0,
                                    R.string.preview__GdsContent__oneLine_0,
                                    R.string.preview__GdsContent__oneLine_0,
                                    R.string.preview__GdsContent__oneLine_0,
                                ).toIntArray(),
                            ),
                        ),
                    ),
                    BodyContent.Text(R.string.preview__GdsInformationBanner__content),
                ),
            ),
        ),
        Content(
            R.string.preview__GdsInformationBanner__title,
            ImageResource(
                R.drawable.preview__gdsvectorimage,
                R.string.preview__GdsVectorImage__description,
            ),
            Body(
                listOf(
                    BodyContent.Text(R.string.preview__GdsInformationBanner__content),
                    BodyContent.Text(R.string.preview__GdsContent__oneLine_0),
                    BodyContent.BulletList(
                        BulletListParameters(
                            contentText = GdsContentText.GdsContentTextString(
                                text = arrayOf(
                                    R.string.preview__GdsContent__oneLine_0,
                                    R.string.preview__GdsContent__oneLine_0,
                                    R.string.preview__GdsContent__oneLine_0,
                                    R.string.preview__GdsContent__oneLine_0,
                                ).toIntArray(),
                            ),
                        ),
                    ),
                ),
            ),
        ),
        Content(
            R.string.preview__GdsInformationBanner__title,
            ImageResource(
                R.drawable.preview__gdsvectorimage,
                R.string.preview__GdsVectorImage__description,
            ),
        ),
        Content(
            R.string.preview__GdsInformationBanner__title,
        ),
    )
}
