package com.example.domain.usecase.signup

import com.example.domain.model.LoginUserInfo
import com.example.domain.model.ResponseUseCaseModel
import com.example.domain.model.UserAuth
import com.example.domain.repository.SignUpRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SetLoginUserCaseImpl @Inject constructor(
    private val signUpRepository: SignUpRepository
) : SetLoginUseCase {

    override suspend fun invoke(
        email: String,
        password: String
    ): Flow<ResponseUseCaseModel<UserAuth>> =
        signUpRepository.setLogin(LoginUserInfo(email, password))

}