package com.example.domain.usecase.analysis

import com.example.domain.model.ResponseUseCaseModel
import com.example.domain.repository.AnalysisRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCheckAnalysisOverUseCaseImpl @Inject constructor(
    private val analysisRepository: AnalysisRepository
) : GetCheckAnalysisOverUseCase {

    override suspend fun invoke(
        accessToken: String,
        interviewId: Int
    ): Flow<ResponseUseCaseModel<String>> =
        analysisRepository.getCheckAnalysisOver(
            accessToken = accessToken,
            interviewId = interviewId
        )

}