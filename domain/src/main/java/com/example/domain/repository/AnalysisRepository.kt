package com.example.domain.repository

import com.example.domain.model.ResponseUseCaseModel
import com.example.domain.model.analysis.ActionAnalysisInfo
import com.example.domain.model.analysis.DayInterviewInfo
import com.example.domain.model.analysis.MonthInterviewInfo
import kotlinx.coroutines.flow.Flow

interface AnalysisRepository {

    suspend fun getMonthInterviews(
        accessToken: String,
        userId: Int,
        yearMonth: String
    ): Flow<ResponseUseCaseModel<MonthInterviewInfo>>

    suspend fun getDayInterviews(
        accessToken: String,
        userId: Int,
        date: String
    ): Flow<ResponseUseCaseModel<List<DayInterviewInfo>>>

    suspend fun getCheckAnalysisOver(
        accessToken: String,
        interviewId: Int
    ): Flow<ResponseUseCaseModel<String>>

    suspend fun getActionAnalysis(
        accessToken: String,
        interviewId: Int
    ): Flow<ResponseUseCaseModel<List<ActionAnalysisInfo>>>

}