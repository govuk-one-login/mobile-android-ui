package uk.gov.android.ui.patterns

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import uk.gov.android.ui.components.R
import uk.gov.android.ui.theme.m3.Typography

@Composable
fun GDSCentreAlignedScreen(
    image: Int? = null,
    title: String,
    body: String? = null,
    supportingText: String? = null,
) {
    Column(
        modifier = Modifier
            .padding(
                start = 16.dp,
                top = 0.dp,
                end = 16.dp,
                bottom = 0.dp
            )
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
    ) {
        Spacer(
            Modifier
                .fillMaxSize()
                .weight(0.2f)
        )
        image?.let {
            Image(
                painter = painterResource(it),
                contentDescription = "Optional Image"
            )
        }
        Text(
            text = title,
            style = Typography.headlineLarge
        )
        body?.let {
            Text(
                text = body,
                style = Typography.bodyLarge,
            )

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
            .fillMaxSize()
        ,
    ) {
        supportingText?.let {
            Text(
                text = supportingText,
                style = Typography.bodySmall,
            )
        }
    }
}


@Composable
@Preview
private fun GDSCentreAlignedScreenPreview(
) {
    GDSCentreAlignedScreen(
        R.drawable.preview__gdsvectorimage,
        "Title",
        "Body (Optional)",
        "Supporting text (Optional)"
    )
}
