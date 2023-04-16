package com.example.domain.usecase.signup

import com.example.domain.model.EmailResponse
import com.example.domain.repository.SignUpRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AuthenticateCodeUseCaseImpl @Inject constructor(
    private val signUpRepository: SignUpRepository
) : AuthenticateCodeUseCase {
    override suspend fun invoke(email: String, code: String): Flow<EmailResponse> =
        signUpRepository.authenticateCode(email, code)
}