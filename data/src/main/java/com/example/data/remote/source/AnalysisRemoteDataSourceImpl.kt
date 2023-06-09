package com.example.data.remote.source

import com.example.data.remote.mapper.InterviewMapper
import com.example.data.remote.model.analysis.*
import com.example.data.remote.network.AnalysisApiService
import com.example.data.repository.model.ResponseRepositoryModel
import javax.inject.Inject

internal class AnalysisRemoteDataSourceImpl @Inject constructor(
    private val analysisApiService: AnalysisApiService
) : AnalysisRemoteDataSource {

    override suspend fun getMonthInterviews(
        accessToken: String,
        userId: Int,
        yearMonth: String
    ): Result<ResponseRepositoryModel<MonthInterviewInfo>> {
        val response = analysisApiService.getMonthInterviews(
            accessToken = InterviewMapper.mapperToBearerToken(accessToken),
            userId = userId,
            yearMonth = yearMonth
        )
        return kotlin.runCatching {
            response.body()!!.toRepositoryModel()
        }.onFailure {
            throw Exception(response.errorBody()?.string())
        }
    }

    override suspend fun getDayInterviews(
        accessToken: String,
        userId: Int,
        date: String
    ): Result<ResponseRepositoryModel<List<DayInterviewInfo>>> {
        val response = analysisApiService.getDayInterviews(
            accessToken = InterviewMapper.mapperToBearerToken(accessToken),
            userId = userId,
            date = date
        )
        return kotlin.runCatching {
            response.body()!!.toRepositoryModel()
        }.onFailure {
            throw Exception(response.errorBody()?.string())
        }
    }

    override suspend fun getCheckAnalysisOver(
        accessToken: String,
        interviewId: Int
    ): Result<ResponseRepositoryModel<String>> {
        val response = analysisApiService.getCheckAnalysisOver(
            accessToken = InterviewMapper.mapperToBearerToken(accessToken),
            interviewId = interviewId
        )

        return kotlin.runCatching {
            response.body()!!.toRepositoryModel()
        }.onFailure {
            throw Exception(response.errorBody()?.string())
        }
    }

    override suspend fun getActionAnalysis(
        accessToken: String,
        interviewId: Int
    ): Result<ResponseRepositoryModel<ActionAnalysisInfo>> {
        val response = analysisApiService.getActionAnalysis(
            accessToken = InterviewMapper.mapperToBearerToken(accessToken),
            interviewId = interviewId
        )

        return kotlin.runCatching {
            response.body()!!.toRepositoryModel()
        }.onFailure {
            throw Exception(response.errorBody()?.string())
        }
    }

    override suspend fun getAnswerAnalysis(
        accessToken: String,
        interviewId: Int
    ): Result<ResponseRepositoryModel<AnswerList>> {
        val response = analysisApiService.getAnswerAnalysis(
            accessToken = InterviewMapper.mapperToBearerToken(accessToken),
            interviewId = interviewId
        )

        return kotlin.runCatching {
            response.body()!!.toRepositoryModel()
        }.onFailure {
            throw Exception(response.errorBody()?.string())
        }
    }

    override suspend fun getTotalAnalysis(
        accessToken: String,
        userId: Int
    ): Result<ResponseRepositoryModel<TotalAnalysisInfo>> {
        val response = analysisApiService.getTotalAnalysis(
            accessToken = InterviewMapper.mapperToBearerToken(accessToken),
            userId = userId
        )

        return kotlin.runCatching {
            response.body()!!.toRepositoryModel()
        }.onFailure {
            throw Exception(response.errorBody()?.string())
        }
    }

}