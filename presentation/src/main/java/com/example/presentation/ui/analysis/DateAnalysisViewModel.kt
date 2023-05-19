package com.example.presentation.ui.analysis

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import com.example.presentation.model.analysis.Date
import kotlinx.coroutines.flow.MutableStateFlow
import java.time.LocalDate
import java.time.format.TextStyle
import java.util.*

class DateAnalysisViewModel : ViewModel() {
    @RequiresApi(Build.VERSION_CODES.O)
    private val nowLocalDate = LocalDate.now()

    @RequiresApi(Build.VERSION_CODES.O)
    private val _clickedDay =
        MutableStateFlow(
            Date(
                nowLocalDate.year,
                nowLocalDate.monthValue,
                nowLocalDate.dayOfMonth,
                nowLocalDate.dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.KOREAN)
            )
        )

    @RequiresApi(Build.VERSION_CODES.O)
    val clickedDay = _clickedDay

}