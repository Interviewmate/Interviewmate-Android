package com.example.data.remote.source

import com.example.data.remote.model.analysis.ActionAnalysisInfo
import com.example.data.remote.model.analysis.AnswerAnalysisInfo
import com.example.data.remote.model.analysis.DayInterviewInfo
import com.example.data.remote.model.analysis.MonthInterviewInfo
import com.example.data.repository.model.ResponseRepositoryModel

internal interface AnalysisRemoteDataSource {

    suspend fun getMonthInterviews(
        accessToken: String,
        userId: Int,
        yearMonth: String
    ): Result<ResponseRepositoryModel<MonthInterviewInfo>>

    suspend fun getDayInterviews(
        accessToken: String,
        userId: Int,
        date: String
    ): Result<ResponseRepositoryModel<List<DayInterviewInfo>>>

    suspend fun getCheckAnalysisOver(
        accessToken: String,
        interviewId: Int
    ): Result<ResponseRepositoryModel<String>>

    suspend fun getActionAnalysis(
        accessToken: String,
        interviewId: Int
    ): Result<ResponseRepositoryModel<List<ActionAnalysisInfo>>>

    suspend fun getAnswerAnalysis(
        accessToken: String,
        interviewId: Int
    ): Result<ResponseRepositoryModel<List<AnswerAnalysisInfo>>>

}