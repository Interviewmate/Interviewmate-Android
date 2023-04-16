package com.example.presentation.ui.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecase.signup.SendEmailUseCase
import com.example.presentation.model.jobskill.Developer
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val sendEmailUseCase: SendEmailUseCase
) : ViewModel() {
    lateinit var email: String
    lateinit var password: String
    lateinit var nickName: String
    var job: Developer? = null
    val keyword = mutableListOf<String>()

    private val _statusEmailSending = MutableStateFlow("")
    val statusEmailSending = _statusEmailSending

    private val _isEmailNoticeVisible = MutableStateFlow(false)
    val isEmailNoticeVisible = _isEmailNoticeVisible

    private val _selectJobEvent = MutableSharedFlow<Developer>()
    val selectJobEvent = _selectJobEvent.asSharedFlow()

    suspend fun sendEmail(email: String) {
        sendEmailUseCase(email)
            .catch {
                _statusEmailSending.emit(EMAIL_SEND_FAILURE)
            }
            .collectLatest { emailResponse ->
                _statusEmailSending.emit(emailResponse.result)
            }
        _isEmailNoticeVisible.emit(true)
    }

    fun changeChip(developer: Developer) {
        this.job = developer

        viewModelScope.launch {
            _selectJobEvent.emit(developer)
        }
    }

    companion object {
        const val EMAIL_SEND_FAILURE = "이메일 전송에 실패했습니다."
    }
}