package com.example.presentation.ui.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.signup.UserAuth
import com.example.domain.usecase.signup.*
import com.example.presentation.model.Status
import com.example.presentation.model.jobskill.Developer
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val sendEmailUseCase: SendEmailUseCase,
    private val authenticateCodeUseCase: AuthenticateCodeUseCase,
    private val setLoginUseCase: SetLoginUseCase,
    private val checkNicknameDuplicationUseCase: CheckNicknameDuplicationUseCase,
    private val setSignUpUseCase: SetSignUpUseCase,
    private val setUserKeywordCase: SetUserKeywordCase
) : ViewModel() {
    lateinit var email: String
    lateinit var password: String
    lateinit var nickname: String
    var job: Developer? = null
    val keyword = mutableListOf<String>()
    var userId = -1

    private val _statusNicknameDuplication = MutableStateFlow("")
    val statusNicknameDuplication = _statusNicknameDuplication

    private val _isDuplicationNoticeVisible = MutableStateFlow(false)
    val isDuplicationNoticeVisible = _isDuplicationNoticeVisible

    private val _isNicknameDuplication = MutableStateFlow(false)
    val isNicknameDuplication = _isNicknameDuplication


    private val _statusEmailSending = MutableStateFlow("")
    val statusEmailSending = _statusEmailSending

    private val _isEmailNoticeVisible = MutableStateFlow(false)
    val isEmailNoticeVisible = _isEmailNoticeVisible

    private val _isEmailSending = MutableStateFlow(false)
    val isEmailSending = _isEmailSending


    private val _statusAuthCode = MutableStateFlow("")
    val statusAuthCode = _statusAuthCode

    private val _isCodeNoticeVisible = MutableStateFlow(false)
    val isCodeNoticeVisible = _isCodeNoticeVisible

    private val _isCodeAuth = MutableStateFlow(false)
    val isCodeAuth = _isCodeAuth


    private val _selectJobEvent = MutableSharedFlow<Developer>()
    val selectJobEvent = _selectJobEvent.asSharedFlow()

    private val _isSuccessSignUp = MutableSharedFlow<Pair<Boolean, String>>()
    val isSuccessSignUp = _isSuccessSignUp

    private val _isSuccessLogin = MutableSharedFlow<Pair<Boolean, String>>()
    val isSuccessLogin = _isSuccessLogin

    private val _isSuccessKeyword = MutableSharedFlow<Boolean>()
    val isSuccessKeyword = _isSuccessKeyword

    lateinit var userAuth: UserAuth


    suspend fun checkNicknameDuplication(nickname: String) {
        viewModelScope.launch {
            checkNicknameDuplicationUseCase(nickname)
                .catch {
                    _statusNicknameDuplication.emit(NICKNAME_DUPLICATION)
                    _isNicknameDuplication.emit(false)
                }
                .collectLatest { nicknameResponse ->
                    _statusNicknameDuplication.emit(nicknameResponse.result)
                    _isNicknameDuplication.emit(true)
                }
            _isDuplicationNoticeVisible.emit(true)
        }
    }

    suspend fun sendEmail(email: String) {
        viewModelScope.launch {
            sendEmailUseCase(email)
                .catch {
                    _statusEmailSending.emit(EMAIL_SEND_FAILURE)
                    _isEmailSending.emit(false)
                }
                .collectLatest { emailResponse ->
                    _statusEmailSending.emit(emailResponse.result)
                    _isEmailSending.emit(true)
                }
            _isEmailNoticeVisible.emit(true)
        }
    }

    suspend fun authenticateCode(email: String, code: String) {
        viewModelScope.launch {
            authenticateCodeUseCase(email, code)
                .catch {
                    _statusAuthCode.emit(CODE_FAILURE)
                    _isCodeAuth.emit(false)
                }
                .collectLatest { emailResponse ->
                    if (emailResponse.status == Status.SUCCESS.name) {
                        _statusAuthCode.emit(CODE_SUCCESS)
                        _isCodeAuth.emit(true)
                    } else if (emailResponse.status == Status.FAILURE.name) {
                        _statusAuthCode.emit(CODE_FAILURE)
                        _isCodeAuth.emit(false)
                    }
                }
            _isCodeNoticeVisible.emit(true)
        }
    }

    fun changeChip(developer: Developer) {
        this.job = developer

        viewModelScope.launch {
            _selectJobEvent.emit(developer)
        }
    }

    suspend fun setSignUp() {
        viewModelScope.launch {
            if (job != null) {
                setSignUpUseCase(email, password, nickname, job!!.name)
                    .catch {
                        _isSuccessSignUp.emit(Pair(false, SIGNUP_FAILURE))
                    }
                    .collectLatest { signUpResponse ->
                        if (signUpResponse.status == Status.SUCCESS.name) {
                            _isSuccessSignUp.emit(Pair(true, nickname + SIGNUP_SUCCESS))
                            userId = signUpResponse.result.userId
                        }
                    }
            }
        }
    }

    suspend fun setLogin(email: String, password: String) {
        viewModelScope.launch {
            setLoginUseCase(email, password)
                .catch {
                    _isSuccessLogin.emit(Pair(false, LOGIN_FAILURE))
                }
                .collectLatest { loginResponse ->
                    if (loginResponse.status == Status.SUCCESS.name) {
                        userAuth = loginResponse.result
                        _isSuccessLogin.emit(Pair(true, loginResponse.message))
                    } else if (loginResponse.status == Status.FAILURE.name) {
                        _isSuccessLogin.emit(Pair(false, loginResponse.message))
                    }
                }
        }
    }

    suspend fun setKeywords() {
        viewModelScope.launch {
            setUserKeywordCase(userId, keyword)
                .catch {
                    _isSuccessKeyword.emit(false)
                }
                .collectLatest { keywordResponse ->
                    if (keywordResponse.status == Status.SUCCESS.name) {
                        _isSuccessKeyword.emit(true)
                    } else {
                        _isSuccessKeyword.emit(false)
                    }
                }
        }
    }

    companion object {
        const val EMAIL_SEND_FAILURE = "이메일 전송에 실패했습니다."
        const val CODE_SUCCESS = "인증에 성공했습니다."
        const val CODE_FAILURE = "인증에 실패했습니다."
        const val LOGIN_FAILURE = "로그인에 실패했습니다."
        const val NICKNAME_DUPLICATION = "중복된 닉네임입니다."
        const val SIGNUP_FAILURE = "회원가입에 실패했습니다."
        const val SIGNUP_SUCCESS = "님 환영합니다."
    }
}