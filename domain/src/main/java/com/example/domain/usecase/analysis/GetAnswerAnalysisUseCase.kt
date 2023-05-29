package com.example.domain.usecase.analysis

import com.example.domain.model.ResponseUseCaseModel
import com.example.domain.model.analysis.AnswerList
import kotlinx.coroutines.flow.Flow

interface GetAnswerAnalysisUseCase {
    suspend operator fun invoke(
        accessToken: String,
        interviewId: Int
    ): Flow<ResponseUseCaseModel<AnswerList>>
}