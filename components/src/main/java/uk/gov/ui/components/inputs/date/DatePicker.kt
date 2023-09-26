package uk.gov.ui.components.inputs.date

import android.content.res.Configuration
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.google.android.material.datepicker.MaterialDatePicker
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Locale
import uk.gov.ui.components.R
import uk.gov.ui.theme.GdsTheme
import uk.gov.ui.theme.smallPadding

@Composable
fun GdsDatePicker(
    params: DatePickerParameters
) {
    params.apply {
        val activity = LocalContext.current as? AppCompatActivity
        val (selectedDate, onDateChanged) = dateState
        val dateFormatter = DateTimeFormatter.ofPattern(dateFormatString)

        val interactionSource = remember { MutableInteractionSource() }
        val isPressed: Boolean by interactionSource.collectIsPressedAsState()
        LaunchedEffect(isPressed) {
            if (isPressed) {
                println("clicked")
                activity?.supportFragmentManager?.let {
                    getDatePickerDialog(
                        datePickerTitle,
                        onDateChanged,
                        dateFormatString
                    ).show(
                        it,
                        "datePicker"
                    )
                }
            }
        }

        Column(
            modifier = Modifier
                .semantics(mergeDescendants = true) {}
                .background(
                    colors.background
                )
                .then(
                    colModifier
                )
        ) {
            OutlinedTextField(
                modifier = Modifier
                    .testTag("datePicker")
                    .fillMaxWidth(),
                value = selectedDate?.format(dateFormatter)
                    ?: dateFormatString.lowercase(),
                onValueChange = { },
                readOnly = true,
                label = {
                    Text("Date")
                },
                trailingIcon = {
                    Icon(
                        painterResource(id = R.drawable.baseline_today_24),
                        "Calendar"
                    )
                },
                colors = TextFieldDefaults.textFieldColors(
                    trailingIconColor = colors.primary,
                    backgroundColor = colors.background
                ),
                interactionSource = interactionSource
            )
        }
    }
}

private fun getDatePickerDialog(
    pickerTitleRes: Int,
    saveDate: (LocalDate?) -> Unit,
    dateFormatString: String
): MaterialDatePicker<Long> {
    val datePickerDialog = MaterialDatePicker.Builder.datePicker()
        .setTitleText(pickerTitleRes)
        .setTextInputFormat(SimpleDateFormat(dateFormatString, Locale.getDefault()))
        .build()
    datePickerDialog.addOnPositiveButtonClickListener {
        val date = LocalDateTime.ofInstant(Instant.ofEpochMilli(it), ZoneId.systemDefault())
        saveDate(date.toLocalDate())
    }
    return datePickerDialog
}

data class DatePickerParameters(
    val dateState: MutableState<LocalDate?>,
    @StringRes
    val datePickerTitle: Int,
    val dateFormatString: String = "dd/MM/yyyy",
    val textStyle: TextStyle? = null,
    val colModifier: Modifier = Modifier
        .padding(bottom = smallPadding)
) {
    override fun toString(): String = this::class.java.simpleName
}

class DatePickerProvider : PreviewParameterProvider<DatePickerParameters> {
    override val values: Sequence<DatePickerParameters> = sequenceOf(
        DatePickerParameters(
            dateState = mutableStateOf(null),
            datePickerTitle = R.string.preview__GdsHeading__h4
        )
    )
}

@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
private fun Preview(
    @PreviewParameter(DatePickerProvider::class)
    datePickerParams: DatePickerParameters
) {
    GdsTheme {
        GdsDatePicker(
            datePickerParams
        )
    }
}
