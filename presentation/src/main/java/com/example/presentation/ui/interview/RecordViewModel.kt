package com.example.presentation.ui.interview

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.signup.UserAuth
import com.example.domain.usecase.interview.PutInterviewVideoUseCase
import com.example.domain.usecase.interview.SetInterviewAnalysesUseCase
import com.example.domain.usecase.interview.SetInterviewVideoUrlUseCase
import com.example.domain.usecase.interview.SetS3PreSignedUseCase
import com.example.presentation.model.Status
import com.example.presentation.model.interview.Question
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
    private val setS3PreSignedUseCase: SetS3PreSignedUseCase,
    private val putInterviewVideoUseCase: PutInterviewVideoUseCase,
    private val setInterviewAnalysesUseCase: SetInterviewAnalysesUseCase,
    private val setInterviewVideoUrlUseCase: SetInterviewVideoUrlUseCase
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

    private val _nowQuestion = MutableStateFlow(Question(-1, "", ""))
    val nowQuestion = _nowQuestion

    private val _isPreSignedSuccess = MutableSharedFlow<Boolean>()
    val isPreSignedSuccess = _isPreSignedSuccess

    private val _isVideoUploadSuccess = MutableSharedFlow<Boolean>()
    val isVideoUploadSuccess = _isVideoUploadSuccess

    private val _canOver = MutableSharedFlow<Boolean>()
    val canOver = _canOver

    private var time = INIT_TIMER_TIME

    private var idx = 10

    var questions: List<Question> = listOf()
    var interviewId = -1

    lateinit var preSignedUrl: Array<String>

    lateinit var videoPath: String

    suspend fun startTimer(userAuth: UserAuth) {
        idx -= 1
        if (idx == -1) {
            viewModelScope.launch {
                timerTask.cancel()
                _isOver.emit(true)
            }
            return
        }
        _nowQuestion.emit(questions[idx])
        timerTask = kotlin.concurrent.timer(period = ONE_SECOND) {
            time -= 1
            viewModelScope.launch {
                _timer.emit(time)
            }
            if (time == TIME_OVER) {
                viewModelScope.launch {
                    changeLayout(false)
                    startRecording(userAuth)
                }
            }
        }
    }

    private suspend fun startRecording(userAuth: UserAuth) {
        timerTask.cancel()
        time = INIT_RECORDING_TIME_INT
        setS3PreSigned(userAuth)
        timerTask = kotlin.concurrent.timer(period = ONE_SECOND) {
            time -= 1
            viewModelScope.launch {
                _recording.emit(convertToTimeString(time))
            }
            if (time == TIME_OVER) {
                viewModelScope.launch {
                    reset(userAuth)
                }
            }
        }
    }

    private fun convertToTimeString(time: Int) = "${"%02d".format(time / M_UNIT)}:${"%02d".format(time % M_UNIT)}"

    suspend fun reset(userAuth: UserAuth) {
        timerTask.cancel()
        time = INIT_TIMER_TIME
        if (_isTimerVisible.value.not()) {
            changeLayout(true)
        }
        putInterviewVideo(userAuth, questions[idx].questionId, idx == LAST)
        startTimer(userAuth)
    }

    private fun changeLayout(isVisible: Boolean) {
        viewModelScope.launch {
            _isTimerVisible.emit(isVisible)
        }
    }

    private fun setS3PreSigned(userAuth: UserAuth) {
        viewModelScope.launch {
            setS3PreSignedUseCase(
                userId = userAuth.userId,
                number = PRE_SIGNED_NUM,
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

    private fun putInterviewVideo(userAuth: UserAuth, questionId: Int, isLast: Boolean) {
        viewModelScope.launch {
            putInterviewVideoUseCase(preSignedUrl.first(), videoPath)
                .catch {
                    _isVideoUploadSuccess.emit(false)
                }
                .collectLatest { videoUploadResponse ->
                    if (videoUploadResponse) {
                        _isVideoUploadSuccess.emit(true)
                        setInterviewVideoUrl(
                            userAuth,
                            interviewId,
                            questionId,
                            getUrl(preSignedUrl.first()),
                        )
                        setInterviewAnalyses(
                            userAuth,
                            getObjectKey(preSignedUrl.first()),
                            questionId,
                            isLast
                        )
                    } else {
                        _isVideoUploadSuccess.emit(false)
                    }
                }
        }
    }

    private fun setInterviewVideoUrl(userAuth: UserAuth, interviewId: Int, questionId: Int, url: String) {
        viewModelScope.launch {
            setInterviewVideoUrlUseCase(userAuth.accessToken, interviewId, questionId, url)
                .catch {
                    Log.d("setInterviewVideoUrl", "catch")
                }
                .collectLatest { videoUrlResponse ->
                    if (videoUrlResponse.status == Status.SUCCESS.name) {
                        Log.d("setInterviewVideoUrl", "success")
                    } else {
                        Log.d("setInterviewVideoUrl", "fail")
                    }
                }
        }
    }

    private fun setInterviewAnalyses(
        userAuth: UserAuth,
        objectKey: String,
        questionId: Int,
        isLast: Boolean
    ) {
        viewModelScope.launch {
            Log.d("setInterviewAnalyses", "잘 보내지나 분석 $interviewId ${questions.find { it.questionId == questionId }?.content} $objectKey $questionId")
            setInterviewAnalysesUseCase(userAuth.accessToken, interviewId, objectKey, questionId)
                .catch {
                    if (isLast) {
                        _canOver.emit(true)
                    }
                }
                .collectLatest { _ ->
                    if (isLast) {
                        _canOver.emit(true)
                    }
                }
        }
    }

    private fun getObjectKey(preSignedUrl: String): String =
        preSignedUrl.split("amazonaws.com/").last().split("?").first()

    private fun getUrl(preSignedUrl: String): String =
        preSignedUrl.split("?").first()

    companion object {
        const val INIT_TIMER_TIME = 20
        const val ONE_SECOND = 1000L
        const val INIT_RECORDING_TIME = "02:00"
        const val INIT_RECORDING_TIME_INT = 120
        const val M_UNIT = 60
        const val TIME_OVER = 0
        const val INTERVIEW = "interview"
        const val PRE_SIGNED_NUM = 1
        const val LAST = 0
    }
}