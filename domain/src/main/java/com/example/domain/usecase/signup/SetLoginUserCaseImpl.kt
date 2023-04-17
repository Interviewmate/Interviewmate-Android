package com.example.domain.usecase.signup

import com.example.domain.model.LoginResponse
import com.example.domain.model.LoginUserInfo
import com.example.domain.repository.SignUpRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SetLoginUserCaseImpl @Inject constructor(
    private val signUpRepository: SignUpRepository
) : SetLoginUseCase {

    override suspend fun invoke(email: String, password: String): Flow<LoginResponse> =
        signUpRepository.setLogin(LoginUserInfo(email, password))

}