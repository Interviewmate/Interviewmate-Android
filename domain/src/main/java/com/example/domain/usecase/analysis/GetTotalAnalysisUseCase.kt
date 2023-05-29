package com.example.domain.usecase.analysis

import com.example.domain.model.ResponseUseCaseModel
import com.example.domain.model.analysis.TotalAnalysisInfo
import kotlinx.coroutines.flow.Flow

interface GetTotalAnalysisUseCase {
    suspend operator fun invoke(
        accessToken: String,
        userId: Int
    ): Flow<ResponseUseCaseModel<List<TotalAnalysisInfo>>>
}