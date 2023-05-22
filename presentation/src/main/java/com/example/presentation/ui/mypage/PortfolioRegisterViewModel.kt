package com.example.presentation.ui.mypage

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.signup.UserAuth
import com.example.domain.usecase.interview.SetS3PreSignedUseCase
import com.example.domain.usecase.mypage.PutPortfolioUseCase
import com.example.presentation.model.Status
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject

@HiltViewModel
class PortfolioRegisterViewModel @Inject constructor(
    private val setS3PreSignedUseCase: SetS3PreSignedUseCase,
    private val putPortfolioUseCase: PutPortfolioUseCase
) : ViewModel() {

    lateinit var userAuth: UserAuth

    lateinit var portfolioUri: String

    private val _isPreSignedSuccess = MutableSharedFlow<Boolean>()
    val isPreSignedSuccess = _isPreSignedSuccess

    lateinit var preSignedUrl: Array<String>

    fun setS3PreSigned() {
        viewModelScope.launch {
            setS3PreSignedUseCase(
                userId = userAuth.userId,
                number = PORTFOLIO_NUM,
                directory = PORTFOLIO
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

    fun putPortfolio(file: File) {
        viewModelScope.launch {
            putPortfolioUseCase(preSignedUrl.first(), file)
                .catch {

                }
                .collectLatest { videoUploadResponse ->
                    if (videoUploadResponse) {
                        Log.d(
                            "putPortfolio",
                            "포트폴리오 보냈습니다~"
                        )

                    }
                }
        }
    }

    companion object {
        const val PORTFOLIO_NUM = 1
        const val PORTFOLIO = "portfolio"
    }

}