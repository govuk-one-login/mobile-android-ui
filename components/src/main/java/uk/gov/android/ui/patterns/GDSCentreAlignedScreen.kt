package uk.gov.android.ui.patterns

import android.text.Layout
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
fun GDSCentreAlignedScreen(image: Int? = null, title: String) {
    Column(
        modifier = Modifier
            .padding(start = 16.dp, top = 0.dp, end = 16.dp, bottom = 0.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column (

        ) {
            image?.let { painterResource(it) }
            Text(
                text = title,
                style = Typography.headlineLarge,
            )
        }
    }
}


@Composable
@Preview
private fun GDSCentreAlignedScreenPreview(
) {
    GDSCentreAlignedScreen(
        image = R.drawable.preview__gdsvectorimage,
        title = "Title"
    )
}
