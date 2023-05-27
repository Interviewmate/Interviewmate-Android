package com.example.data.remote.source

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

}