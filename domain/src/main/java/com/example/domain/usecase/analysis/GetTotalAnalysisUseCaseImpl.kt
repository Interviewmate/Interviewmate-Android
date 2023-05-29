package com.example.domain.usecase.analysis

import com.example.domain.model.ResponseUseCaseModel
import com.example.domain.model.analysis.TotalAnalysisInfo
import com.example.domain.repository.AnalysisRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTotalAnalysisUseCaseImpl @Inject constructor(
    private val analysisRepository: AnalysisRepository
) : GetTotalAnalysisUseCase {
    override suspend fun invoke(
        accessToken: String,
        userId: Int
    ): Flow<ResponseUseCaseModel<List<TotalAnalysisInfo>>> =
        analysisRepository.getTotalAnalysis(accessToken = accessToken, userId = userId)
}