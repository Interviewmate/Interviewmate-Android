package com.example.domain.usecase.analysis

import com.example.domain.model.ResponseUseCaseModel
import com.example.domain.model.analysis.DayInterviewInfo
import com.example.domain.repository.AnalysisRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetDayInterviewsUseCaseImpl @Inject constructor(
    private val analysisRepository: AnalysisRepository
) : GetDayInterviewsUseCase {

    override suspend fun invoke(
        accessToken: String,
        userId: Int,
        date: String
    ): Flow<ResponseUseCaseModel<List<DayInterviewInfo>>> =
        analysisRepository.getDayInterviews(accessToken, userId, date)

}