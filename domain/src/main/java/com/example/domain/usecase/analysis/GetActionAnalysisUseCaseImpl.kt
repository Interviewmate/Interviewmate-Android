package com.example.domain.usecase.analysis

import com.example.domain.model.ResponseUseCaseModel
import com.example.domain.model.analysis.ActionAnalysisInfo
import com.example.domain.repository.AnalysisRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetActionAnalysisUseCaseImpl @Inject constructor(
    private val analysisRepository: AnalysisRepository
) : GetActionAnalysisUseCase {
    override suspend fun invoke(
        accessToken: String,
        interviewId: Int
    ): Flow<ResponseUseCaseModel<List<ActionAnalysisInfo>>> =
        analysisRepository.getActionAnalysis(accessToken = accessToken, interviewId = interviewId)
}