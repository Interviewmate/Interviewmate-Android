package com.example.presentation.ui.interview

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.signup.UserAuth
import com.example.domain.usecase.interview.GetInterviewQuestionsUseCase
import com.example.domain.usecase.interview.SetInterviewUseCase
import com.example.presentation.model.Status
import com.example.presentation.model.interview.Question
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InterviewViewModel @Inject constructor(
    private val setInterviewUseCase: SetInterviewUseCase,
    private val getInterviewQuestionsUseCase: GetInterviewQuestionsUseCase
) : ViewModel() {

    private val _isInterviewMadeSuccess = MutableSharedFlow<Boolean>()
    val isInterviewMadeSuccess = _isInterviewMadeSuccess

    private val _isQuestionSuccess = MutableSharedFlow<Boolean>()
    val isQuestionSuccess = _isQuestionSuccess

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading

    val keywords = mutableListOf<String>()

    var questions = listOf(
        Question(
            1,
            "1번",
            "질문 키워드"
        ),
        Question(
            2,
            "2번",
            "질문 키워드"
        ),
        Question(
            3,
            "3번",
            "질문 키워드"
        ),
        Question(
            4,
            "4번",
            "질문 키워드"
        ),
        Question(
            5,
            "5번",
            "질문 키워드"
        ),
        Question(
            6,
            "6번",
            "질문 키워드"
        ),
        Question(
            7,
            "7번",
            "질문 키워드"
        ),
        Question(
            8,
            "8번",
            "질문 키워드"
        ),
        Question(
            9,
            "9번",
            "질문 키워드"
        ),
        Question(
            10,
            "10번",
            "질문 키워드"
        ),
    )

    suspend fun setInterview(userAuth: UserAuth) {
        viewModelScope.launch {
            setInterviewUseCase(userAuth)
                .catch {
                    _isInterviewMadeSuccess.emit(false)
                }
                .collectLatest { interviewResponse ->
                    if (interviewResponse.status == Status.SUCCESS.name) {
                        _isInterviewMadeSuccess.emit(true)
                    } else {
                        _isInterviewMadeSuccess.emit(false)
                    }
                }
        }
    }

    suspend fun getInterviewQuestions(userAuth: UserAuth) {
        viewModelScope.launch {
            _isLoading.emit(true)
            getInterviewQuestionsUseCase(userAuth, keywords.toTypedArray())
                .catch {
                    _isQuestionSuccess.emit(false)
                    _isLoading.emit(false)
                }
                .collectLatest { questionResponse ->
                    if (questionResponse.status == Status.SUCCESS.name) {
                        questions = questionResponse.result.questionList.map {
                            Question(
                                it.questionId,
                                it.content,
                                it.keyword
                            )
                        }
                        _isQuestionSuccess.emit(true)
                    } else {
                        _isQuestionSuccess.emit(false)
                    }
                    _isLoading.emit(false)
                }
        }
    }
}