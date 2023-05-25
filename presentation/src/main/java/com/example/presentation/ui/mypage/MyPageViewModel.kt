package com.example.presentation.ui.mypage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.mypage.MyPageUserInfo
import com.example.domain.model.signup.UserAuth
import com.example.domain.usecase.mypage.GetUserInfoUseCase
import com.example.presentation.model.Status
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyPageViewModel @Inject constructor(
    private val getUserInfoUseCase: GetUserInfoUseCase
) : ViewModel() {

    private val _userInfo = MutableStateFlow(MyPageUserInfo("", ""))
    val userInfo = _userInfo

    fun getUserInfo(userAuth: UserAuth) {
        viewModelScope.launch {
            getUserInfoUseCase(userAuth)
                .catch {

                }
                .collectLatest { userInfoResponse ->
                    if (userInfoResponse.status == Status.SUCCESS.name) {
                        _userInfo.emit(userInfoResponse.result)
                    }
                }
        }
    }
}