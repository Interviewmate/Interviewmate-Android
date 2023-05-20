package com.example.domain.usecase.interview

import com.example.domain.model.ResponseUseCaseModel
import com.example.domain.model.interview.QuestionInfo
import kotlinx.coroutines.flow.Flow

interface GetInterviewQuestionsUseCase {
    suspend operator fun invoke(
        accessToken: String,
        userId: Int,
        csKeyword: Array<String>
    ): Flow<ResponseUseCaseModel<QuestionInfo>>
}