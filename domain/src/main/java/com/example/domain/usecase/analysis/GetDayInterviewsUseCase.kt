package com.example.domain.usecase.analysis

import com.example.domain.model.ResponseUseCaseModel
import com.example.domain.model.analysis.DayInterviewInfo
import kotlinx.coroutines.flow.Flow

interface GetDayInterviewsUseCase {

    suspend operator fun invoke(
        accessToken: String,
        userId: Int,
        date: String
    ): Flow<ResponseUseCaseModel<List<DayInterviewInfo>>>

}