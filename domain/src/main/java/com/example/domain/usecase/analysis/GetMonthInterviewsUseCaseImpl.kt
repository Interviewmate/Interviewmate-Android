package com.example.domain.usecase.analysis

import com.example.domain.model.ResponseUseCaseModel
import com.example.domain.model.analysis.MonthInterviewInfo
import com.example.domain.repository.AnalysisRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMonthInterviewsUseCaseImpl @Inject constructor(
    private val analysisRepository: AnalysisRepository
) : GetMonthInterviewsUseCase {

    override suspend fun invoke(
        accessToken: String,
        userId: Int,
        yearMonth: String
    ): Flow<ResponseUseCaseModel<MonthInterviewInfo>> =
        analysisRepository.getMonthInterviews(
            accessToken = accessToken,
            userId = userId,
            yearMonth = yearMonth
        )

}