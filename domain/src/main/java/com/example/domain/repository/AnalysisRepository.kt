package com.example.domain.repository

import com.example.domain.model.ResponseUseCaseModel
import com.example.domain.model.analysis.MonthInterviewInfo
import kotlinx.coroutines.flow.Flow

interface AnalysisRepository {

    suspend fun getMonthInterviews(
        accessToken: String,
        userId: Int,
        yearMonth: String
    ): Flow<ResponseUseCaseModel<MonthInterviewInfo>>

}