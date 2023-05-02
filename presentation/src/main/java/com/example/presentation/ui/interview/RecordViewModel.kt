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

    private val _recording = MutableStateFlow(INIT_RECORDING_TIME)
    val recording = _recording

    private val _isTimerVisible = MutableStateFlow(true)
    val isTimerVisible = _isTimerVisible

    private var time = INIT_TIMER_TIME

    private var idx = 3 //10

    private val _isOver = MutableStateFlow(false)
    val isOver = _isOver

    suspend fun startTimer() {
        idx -= 1
        if (idx == -1) {
            viewModelScope.launch {
                timerTask.cancel()
                _isOver.emit(true)
            }
            return
        }
        timerTask = kotlin.concurrent.timer(period = ONE_SECOND) {
            time -= 1
            viewModelScope.launch {
                _timer.emit(time)
            }
            if (time == TIME_OVER) {
                viewModelScope.launch {
                    changeLayout()
                    startRecording()
                }
            }
        }
    }

    private suspend fun startRecording() {
        timerTask.cancel()
        time = INIT_RECORDING_TIME_INT
        timerTask = kotlin.concurrent.timer(period = ONE_SECOND) {
            time -= 1
            viewModelScope.launch {
                _recording.emit(convertToTimeString(time))
            }
            if (time == TIME_OVER) {
                viewModelScope.launch {
                    reset()
                }
            }
        }
    }

    private fun convertToTimeString(time: Int) = "${time / M_UNIT}:${time % M_UNIT}"

    suspend fun reset() {
        timerTask.cancel()
        time = INIT_TIMER_TIME
        changeLayout()
        startTimer()
    }

    private fun changeLayout() {
        viewModelScope.launch {
            _isTimerVisible.emit(_isTimerVisible.value.not())
        }
    }

    companion object {
        const val INIT_TIMER_TIME = 5 //21
        const val ONE_SECOND = 1000L
        const val INIT_RECORDING_TIME = "02:00"
        const val INIT_RECORDING_TIME_INT = 5
        const val M_UNIT = 60
        const val TIME_OVER = 0
    }
}