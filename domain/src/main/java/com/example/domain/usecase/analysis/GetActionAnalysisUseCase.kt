package com.example.domain.usecase.analysis

import com.example.domain.model.ResponseUseCaseModel
import com.example.domain.model.analysis.ActionAnalysisInfo
import kotlinx.coroutines.flow.Flow

interface GetActionAnalysisUseCase {
    suspend operator fun invoke(
        accessToken: String,
        interviewId: Int
    ): Flow<ResponseUseCaseModel<ActionAnalysisInfo>>
}