package com.example.domain.usecase.signup

import com.example.domain.model.ResponseUseCaseModel
import com.example.domain.repository.SignUpRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CheckNicknameDuplicationUseCaseImpl @Inject constructor(
    private val signUpRepository: SignUpRepository
) : CheckNicknameDuplicationUseCase {

    override suspend fun invoke(nickname: String): Flow<ResponseUseCaseModel<String>> =
        signUpRepository.checkNicknameDuplication(nickname)

}