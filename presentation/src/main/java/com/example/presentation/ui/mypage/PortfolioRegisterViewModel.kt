package com.example.presentation.ui.mypage

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.signup.UserAuth
import com.example.domain.usecase.interview.SetS3PreSignedUseCase
import com.example.domain.usecase.mypage.GetPortfolioExistUseCase
import com.example.domain.usecase.mypage.PutPortfolioKeywordUseCase
import com.example.domain.usecase.mypage.PutPortfolioUseCase
import com.example.presentation.model.Status
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject

@HiltViewModel
class PortfolioRegisterViewModel @Inject constructor(
    private val setS3PreSignedUseCase: SetS3PreSignedUseCase,
    private val putPortfolioUseCase: PutPortfolioUseCase,
    private val putPortfolioKeywordUseCase: PutPortfolioKeywordUseCase,
    private val getPortfolioExistUseCase: GetPortfolioExistUseCase
) : ViewModel() {

    lateinit var userAuth: UserAuth

    lateinit var portfolioUri: String

    private val _isPortfolioSuccess = MutableSharedFlow<Boolean>()
    val isPortfolioSuccess = _isPortfolioSuccess

    private val _isPortfolioExist = MutableStateFlow(false)
    val isPortfolioExist = _isPortfolioExist

    lateinit var preSignedUrl: Array<String>

    fun setS3PreSigned() {
        viewModelScope.launch {
            setS3PreSignedUseCase(
                userId = userAuth.userId,
                number = PORTFOLIO_NUM,
                directory = PORTFOLIO
            )
                .catch {
                    _isPortfolioSuccess.emit(false)
                }
                .collectLatest { preSignedResponse ->
                    if (preSignedResponse.status == Status.SUCCESS.name) {
                        preSignedUrl = preSignedResponse.result.preSignedUrl
                    } else {
                        _isPortfolioSuccess.emit(false)
                    }
                }
        }
    }

    fun putPortfolio(file: File) {
        viewModelScope.launch {
            putPortfolioUseCase(preSignedUrl.first(), file)
                .catch {
                    _isPortfolioSuccess.emit(false)
                }
                .collectLatest { videoUploadResponse ->
                    if (videoUploadResponse) {
                        putPortfolioKeyword()
                    } else {
                        _isPortfolioSuccess.emit(false)
                    }
                }
        }
    }

    private fun putPortfolioKeyword() {
        viewModelScope.launch {
            putPortfolioKeywordUseCase(userAuth)
                .catch {
                    Log.d("keyword", "keyword 추출 catch $it")
                }
                .collectLatest { keywordResponse ->
                    Log.d("keyword", "keyword 추출 collect $keywordResponse")
                }
        }
    }

    fun getPortfolioExist(userAuth: UserAuth) {
        viewModelScope.launch {
            getPortfolioExistUseCase(userAuth)
                .catch {
                    _isPortfolioExist.emit(false)
                }
                .collectLatest { portfolioExistResponse ->
                    if (portfolioExistResponse.status == Status.SUCCESS.name) {
                        if (portfolioExistResponse.result.isExist) {
                            _isPortfolioExist.emit(true)
                        }
                    }
                }
        }
    }

    companion object {
        const val PORTFOLIO_NUM = 1
        const val PORTFOLIO = "portfolio"
    }

}