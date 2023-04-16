package com.example.presentation.ui.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecase.signup.AuthenticateCodeUseCase
import com.example.domain.usecase.signup.SendEmailUseCase
import com.example.presentation.model.Status
import com.example.presentation.model.jobskill.Developer
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val sendEmailUseCase: SendEmailUseCase,
    private val authenticateCodeUseCase: AuthenticateCodeUseCase
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

    private val _statusAuthCode = MutableStateFlow("")
    val statusAuthCode = _statusAuthCode

    private val _isCodeNoticeVisible = MutableStateFlow(false)
    val isCodeNoticeVisible = _isCodeNoticeVisible

    private val _selectJobEvent = MutableSharedFlow<Developer>()
    val selectJobEvent = _selectJobEvent.asSharedFlow()

    var isEmailSending = false
    var isCodeAuth = false

    suspend fun sendEmail(email: String) {
        sendEmailUseCase(email)
            .catch {
                _statusEmailSending.emit(EMAIL_SEND_FAILURE)
            }
            .collectLatest { emailResponse ->
                _statusEmailSending.emit(emailResponse.result)
                isEmailSending = true
            }
        _isEmailNoticeVisible.emit(true)
    }

    suspend fun authenticateCode(email: String, code: String) {
        authenticateCodeUseCase(email, code)
            .catch {
                _statusAuthCode.emit(CODE_FAILURE)
            }
            .collectLatest { emailResponse ->
                if (emailResponse.status == Status.SUCCESS.name) {
                    _statusAuthCode.emit(CODE_SUCCESS)
                } else if (emailResponse.status == Status.FAILURE.name) {
                    _statusAuthCode.emit(CODE_FAILURE)
                    isCodeAuth = true
                }
            }
        _isCodeNoticeVisible.emit(true)
    }

    fun changeChip(developer: Developer) {
        this.job = developer

        viewModelScope.launch {
            _selectJobEvent.emit(developer)
        }
    }

    companion object {
        const val EMAIL_SEND_FAILURE = "이메일 전송에 실패했습니다."
        const val CODE_SUCCESS = "인증에 성공했습니다."
        const val CODE_FAILURE = "인증에 실패했습니다."
    }
}