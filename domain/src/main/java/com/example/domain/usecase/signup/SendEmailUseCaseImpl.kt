package com.example.domain.usecase.signup

import com.example.domain.model.ResponseUseCaseModel
import com.example.domain.repository.SignUpRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SendEmailUseCaseImpl @Inject constructor(
    private val signUpRepository: SignUpRepository
) : SendEmailUseCase {
    override suspend fun invoke(email: String): Flow<ResponseUseCaseModel<String>> =
        signUpRepository.sendEmail(email)
}