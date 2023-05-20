package com.example.presentation.ui.interview

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.signup.UserAuth
import com.example.domain.usecase.interview.SetInterviewUseCase
import com.example.presentation.model.Status
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InterviewViewModel @Inject constructor(
    private val setInterviewUseCase: SetInterviewUseCase
) : ViewModel() {

    private val _isInterviewMade = MutableSharedFlow<Boolean>()
    val isInterviewMade = _isInterviewMade
    suspend fun setInterview(userAuth: UserAuth) {
        viewModelScope.launch {
            setInterviewUseCase(userAuth)
                .catch {
                    _isInterviewMade.emit(false)
                }
                .collectLatest { interviewResponse ->
                    if (interviewResponse.status == Status.SUCCESS.name) {
                        _isInterviewMade.emit(true)
                    } else {
                        _isInterviewMade.emit(false)
                    }
                }
        }
    }
}