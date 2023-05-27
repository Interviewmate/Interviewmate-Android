package com.example.domain.usecase.analysis

import com.example.domain.model.ResponseUseCaseModel
import com.example.domain.model.analysis.MonthInterviewInfo
import kotlinx.coroutines.flow.Flow

interface GetMonthInterviewsUseCase {

    suspend operator fun invoke(
        accessToken: String,
        userId: Int,
        yearMonth: String
    ): Flow<ResponseUseCaseModel<MonthInterviewInfo>>

}