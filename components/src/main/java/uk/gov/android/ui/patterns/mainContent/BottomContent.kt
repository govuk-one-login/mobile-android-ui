package uk.gov.android.ui.patterns.mainContent

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import uk.gov.android.ui.theme.m3.Typography

@Composable
fun ButtonContent(supportingText: String? = null) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom,
        modifier = Modifier
            .padding(top = 16.dp, bottom = 16.dp)
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