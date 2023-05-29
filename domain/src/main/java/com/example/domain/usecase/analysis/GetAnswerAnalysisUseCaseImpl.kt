package com.example.domain.usecase.analysis

import com.example.domain.model.ResponseUseCaseModel
import com.example.domain.model.analysis.AnswerAnalysisInfo
import com.example.domain.repository.AnalysisRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAnswerAnalysisUseCaseImpl @Inject constructor(
    private val analysisRepository: AnalysisRepository
) : GetAnswerAnalysisUseCase {
    override suspend fun invoke(
        accessToken: String,
        interviewId: Int
    ): Flow<ResponseUseCaseModel<List<AnswerAnalysisInfo>>> =
        analysisRepository.getAnswerAnalysis(accessToken = accessToken, interviewId = interviewId)
}