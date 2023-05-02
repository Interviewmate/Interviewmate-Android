package com.example.presentation.ui.interview

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class RecordViewModel @Inject constructor() : ViewModel() {

    private lateinit var timerTask: Timer

    private val _timer = MutableStateFlow(INIT_TIMER_TIME)
    val timer = _timer

    private val _isTimerVisible = MutableStateFlow(true)
    val isTimerVisible = _isTimerVisible

    private var time = INIT_TIMER_TIME

    suspend fun start() {
        timerTask = kotlin.concurrent.timer(period = ONE_SECOND) {
            time -= 1
            viewModelScope.launch {
                _timer.emit(time)
            }
            if (time == 0) {
                cancel()
                changeLayout()
            }
        }
    }

    suspend fun reset() {
        changeLayout()
        timerTask.cancel()
        time = INIT_TIMER_TIME
        start()
    }

    fun changeLayout() {
        viewModelScope.launch {
            _isTimerVisible.emit(_isTimerVisible.value.not())
        }
    }

    companion object {
        const val INIT_TIMER_TIME = 5 //21
        const val ONE_SECOND = 1000L
    }
}