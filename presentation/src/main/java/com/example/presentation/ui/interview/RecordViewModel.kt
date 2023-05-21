package com.example.presentation.ui.interview

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.interview.Question
import com.example.domain.model.signup.UserAuth
import com.example.domain.usecase.interview.SetS3PreSignedUseCase
import com.example.presentation.model.Status
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class RecordViewModel @Inject constructor(
    private val setS3PreSignedUseCase: SetS3PreSignedUseCase
) : ViewModel() {

    private lateinit var timerTask: Timer

    private val _timer = MutableStateFlow(INIT_TIMER_TIME)
    val timer = _timer

    private val _recording = MutableStateFlow(INIT_RECORDING_TIME)
    val recording = _recording

    private val _isTimerVisible = MutableStateFlow(true)
    val isTimerVisible = _isTimerVisible

    private val _isOver = MutableStateFlow(false)
    val isOver = _isOver

    private val _nowQuestion = MutableStateFlow("")
    val nowQuestion = _nowQuestion

    private val _isPreSignedSuccess = MutableSharedFlow<Boolean>()
    val isPreSignedSuccess = _isPreSignedSuccess

    private var time = INIT_TIMER_TIME

    private var idx = 10

    var questions: Array<Question> = arrayOf()

    lateinit var preSignedUrl: Array<String>

    suspend fun startTimer() {
        idx -= 1
        if (idx == -1) {
            viewModelScope.launch {
                timerTask.cancel()
                _isOver.emit(true)
            }
            return
        }
        _nowQuestion.emit(questions[idx].content)
        timerTask = kotlin.concurrent.timer(period = ONE_SECOND) {
            time -= 1
            viewModelScope.launch {
                _timer.emit(time)
            }
            if (time == TIME_OVER) {
                viewModelScope.launch {
                    changeLayout(false)
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
        if (_isTimerVisible.value.not()) {
            changeLayout(true)
        }
        startTimer()
    }

    private fun changeLayout(isVisible: Boolean) {
        viewModelScope.launch {
            _isTimerVisible.emit(isVisible)
        }
    }

    fun setS3PreSigned(userAuth: UserAuth) {
        viewModelScope.launch {
            setS3PreSignedUseCase(
                userId = userAuth.userId,
                number = INTERVIEW_NUM,
                directory = INTERVIEW
            )
                .catch {
                    _isPreSignedSuccess.emit(false)
                }
                .collectLatest { preSignedResponse ->
                    if (preSignedResponse.status == Status.SUCCESS.name) {
                        preSignedUrl = preSignedResponse.result.preSignedUrl
                        _isPreSignedSuccess.emit(true)
                    } else {
                        _isPreSignedSuccess.emit(false)
                    }
                }
        }
    }

    companion object {
        const val INIT_TIMER_TIME = 20
        const val ONE_SECOND = 1000L
        const val INIT_RECORDING_TIME = "02:00"
        const val INIT_RECORDING_TIME_INT = 120
        const val M_UNIT = 60
        const val TIME_OVER = 0
        const val INTERVIEW = "interview"
        const val INTERVIEW_NUM = 10
    }
}