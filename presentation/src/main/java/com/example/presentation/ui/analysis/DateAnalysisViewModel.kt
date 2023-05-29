package com.example.presentation.ui.analysis

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.analysis.DayInterviewInfo
import com.example.domain.model.signup.UserAuth
import com.example.domain.usecase.analysis.GetCheckAnalysisOverUseCase
import com.example.domain.usecase.analysis.GetDayInterviewsUseCase
import com.example.domain.usecase.analysis.GetMonthInterviewsUseCase
import com.example.domain.usecase.analysis.GetTotalAnalysisUseCase
import com.example.presentation.model.Status
import com.example.presentation.model.analysis.Date
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.PieEntry
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.TextStyle
import java.util.*
import javax.inject.Inject

@HiltViewModel
class DateAnalysisViewModel @Inject constructor(
    private val getMonthInterviewsUseCase: GetMonthInterviewsUseCase,
    private val getDayInterviewsUseCase: GetDayInterviewsUseCase,
    private val getCheckAnalysisOverUseCase: GetCheckAnalysisOverUseCase,
    private val getTotalAnalysisUseCase: GetTotalAnalysisUseCase
) : ViewModel() {
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

    private val _isMonthInterviewsSuccess = MutableSharedFlow<Boolean>()
    val isMonthInterviewsSuccess = _isMonthInterviewsSuccess

    private val _isDayInterviewsSuccess = MutableSharedFlow<Boolean>()
    val isDayInterviewsSuccess = _isDayInterviewsSuccess

    private val _isAnalysisOver = MutableSharedFlow<Boolean>()
    val isAnalysisOver = _isAnalysisOver

    private val _isTotalAnalysisSuccess = MutableSharedFlow<Boolean>()
    val isTotalAnalysisSuccess = _isTotalAnalysisSuccess

    var monthInterviews = listOf<Int>()
    var dayInterviews = listOf<DayInterviewInfo>()

    var eyesEntries = arrayListOf<Entry>()
    var poseEntries = arrayListOf<Entry>()
    var keywordEntries = arrayListOf<PieEntry>()

    suspend fun getMonthInterviews(userAuth: UserAuth, yearMonth: String) {
        viewModelScope.launch {
            getMonthInterviewsUseCase(userAuth.accessToken, userAuth.userId, yearMonth)
                .catch {
                    _isMonthInterviewsSuccess.emit(false)
                }
                .collectLatest { monthInterviewsResponse ->
                    if (monthInterviewsResponse.status == Status.SUCCESS.name) {
                        monthInterviews = monthInterviewsResponse.result.dateList
                        _isMonthInterviewsSuccess.emit(true)
                    } else {
                        _isMonthInterviewsSuccess.emit(false)
                    }
                }
        }
    }

    suspend fun getDayInterviews(userAuth: UserAuth, date: String) {
        viewModelScope.launch {
            getDayInterviewsUseCase(userAuth.accessToken, userAuth.userId, date)
                .catch {
                    _isDayInterviewsSuccess.emit(false)
                }
                .collectLatest { dayInterviewsResponse ->
                    if (dayInterviewsResponse.status == Status.SUCCESS.name) {
                        dayInterviews = dayInterviewsResponse.result
                        _isDayInterviewsSuccess.emit(true)
                    } else {
                        _isDayInterviewsSuccess.emit(false)
                    }
                }
        }
    }

    suspend fun getCheckAnalysisOver(userAuth: UserAuth, interviewId: Int) {
        getCheckAnalysisOverUseCase(userAuth.accessToken, interviewId)
            .catch {
                _isAnalysisOver.emit(false)
            }
            .collect { checkResponse ->
                if (checkResponse.result == "true") {
                    _isAnalysisOver.emit(true)
                } else {
                    _isAnalysisOver.emit(false)
                }
            }
    }

    suspend fun getTotalAnalysisOver(userAuth: UserAuth) {
        getTotalAnalysisUseCase(userAuth.accessToken, userAuth.userId)
            .catch {
                _isTotalAnalysisSuccess.emit(false)
            }
            .collect { totalResponse ->
                if (totalResponse.status == Status.SUCCESS.name) {
                    eyesEntries = ChartManager.makeEntriesFromTotal(totalResponse.result.gazeScore)
                    poseEntries = ChartManager.makeEntriesFromTotal(totalResponse.result.poseScore)
                    keywordEntries =
                        ChartManager.makePieEntries(totalResponse.result.keywordDistribution)
                    _isTotalAnalysisSuccess.emit(true)
                } else {
                    _isTotalAnalysisSuccess.emit(false)
                }
            }
    }
}