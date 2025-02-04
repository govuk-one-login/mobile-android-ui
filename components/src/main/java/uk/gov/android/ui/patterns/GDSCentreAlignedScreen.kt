package uk.gov.android.ui.patterns

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import uk.gov.android.ui.components.R
import uk.gov.android.ui.components.content.GdsContentText
import uk.gov.android.ui.components.m3.BulletListParameters
import uk.gov.android.ui.components.m3.GdsBulletList
import uk.gov.android.ui.components.m3.buttons.ButtonParameters
import uk.gov.android.ui.components.m3.buttons.ButtonType
import uk.gov.android.ui.components.m3.buttons.GdsButton
import uk.gov.android.ui.theme.m3.Typography
import uk.gov.android.ui.theme.spacingSingle

@Composable
fun GDSCentreAlignedScreen(
    @DrawableRes image: Int? = null,
    @StringRes title: Int,
    body: Body? = null,
    @StringRes supportingText: Int? = null,
    @StringRes primaryButtonText: Int? = null,
    @StringRes secondaryButtonText: Int? = null
) {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .padding(
                start = 16.dp,
                top = 0.dp,
                end = 16.dp,
                bottom = 0.dp
            )
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
    ) {
        Spacer(
            modifier = Modifier
                .weight(0.2f)
                .fillMaxWidth()
        )
        Column(
            modifier = Modifier.weight(0.5f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            image?.let {
                Image(
                    painter = painterResource(it),
                    contentDescription = "Optional Image"
                )
            }

            Spacer(modifier = Modifier.padding(spacingSingle))

            Text(
                text = context.getString(title),
                style = Typography.headlineLarge,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.padding(spacingSingle))

            body?.let {
                body.bodyText?.forEach {
                    Text(
                        text = context.getString(it),
                        style = Typography.bodyLarge,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                    Spacer(modifier = Modifier.padding(spacingSingle))
                }

                body.bulletList?.let {
                    GdsBulletList(it)
                }
            }
        }

        Spacer(
            Modifier
                .fillMaxSize()
                .weight(0.2f)
        )
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom,
        modifier = Modifier
            .fillMaxSize(),
    ) {

        Spacer(modifier = Modifier.padding(spacingSingle))

        supportingText?.let {
            Text(
                text = context.getString(it),
                style = Typography.bodySmall,
            )
        }

        Spacer(modifier = Modifier.padding(spacingSingle))

        primaryButtonText?.let {
            GdsButton(
                ButtonParameters(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 32.dp, end = 32.dp),
                    buttonType = ButtonType.PRIMARY(),
                    onClick = {},
                    text = context.getString(it)
                )
            )
        }

        Spacer(modifier = Modifier.padding(spacingSingle))

        secondaryButtonText?.let {
            GdsButton(
                ButtonParameters(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 32.dp, end = 32.dp),
                    buttonType = ButtonType.SECONDARY(),
                    onClick = {},
                    text = context.getString(it)
                )
            )
        }

        Spacer(modifier = Modifier.padding(spacingSingle))
    }
}

data class Body(
    val bodyText: List<Int>? = null,
    val bulletList: BulletListParameters? = null
)

/*
@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
)
@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
*/

@Composable
@Preview
@PreviewLightDark
private fun GDSCentreAlignedScreenPreview(
) {
    val bodyText = listOf(
        R.string.preview__GdsInformationBanner__title,
        R.string.preview__GdsInformationBanner__title
    )
    val bulletList = BulletListParameters(
        title = R.string.preview__GdsHeading__subTitle1,
        contentText = GdsContentText.GdsContentTextString(
            text = arrayOf(
                R.string.preview__GdsContent__oneLine_0,
                R.string.preview__GdsContent__oneLine_0,
                R.string.preview__GdsContent__oneLine_0
            ).toIntArray()
        )
    )

    GDSCentreAlignedScreen(
        R.drawable.preview__gdsvectorimage,
        R.string.preview__GdsInformationBanner__title,
        Body(bodyText, bulletList),
        R.string.preview__GdsContent__twoLine_0,
        R.string.preview__GdsButton__primary,
        R.string.preview__GdsButton__secondary
    )
}
