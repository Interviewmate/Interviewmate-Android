package com.example.domain.usecase

import com.example.domain.model.EmailResponse
import com.example.domain.repository.SignUpRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SendEmailUseCaseImpl @Inject constructor(
    private val signUpRepository: SignUpRepository
): SendEmailUseCase {
    override suspend fun invoke(email: String): Flow<EmailResponse> =
        signUpRepository.sendEmail(email)
}