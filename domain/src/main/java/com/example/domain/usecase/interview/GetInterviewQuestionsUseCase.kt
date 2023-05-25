package com.example.domain.usecase.interview

import com.example.domain.model.ResponseUseCaseModel
import com.example.domain.model.interview.QuestionInfo
import com.example.domain.model.signup.UserAuth
import kotlinx.coroutines.flow.Flow

interface GetInterviewQuestionsUseCase {
    suspend operator fun invoke(
        userAuth: UserAuth,
        csKeyword: Array<String>
    ): Flow<ResponseUseCaseModel<QuestionInfo>>
}