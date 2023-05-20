package com.example.data.remote.source

import com.example.data.remote.mapper.InterviewMapper
import com.example.data.remote.model.interview.InterviewId
import com.example.data.remote.model.interview.QuestionInfo
import com.example.data.remote.model.interview.UserId
import com.example.data.remote.network.InterviewApiService
import com.example.data.repository.model.ResponseRepositoryModel
import javax.inject.Inject

internal class InterviewRemoteDataSourceImpl @Inject constructor(
    private val interviewApiService: InterviewApiService
) : InterviewRemoteDataSource {
    override suspend fun setInterview(
        accessToken: String,
        userId: UserId
    ): Result<ResponseRepositoryModel<InterviewId>> {
        val response = interviewApiService.setInterview(
            InterviewMapper.mapperToBearerToken(accessToken),
            userId
        )
        return kotlin.runCatching {
            response.body()!!.toRepositoryModel()
        }.onFailure {
            throw Exception(response.errorBody()?.string())
        }
    }

    override suspend fun getInterviewQuestions(
        accessToken: String,
        userId: Int,
        csKeyword: Array<String>
    ): Result<ResponseRepositoryModel<QuestionInfo>> {
        val response = interviewApiService.getInterviewQuestions(
            accessToken = InterviewMapper.mapperToBearerToken(accessToken),
            userId = userId,
            csKeyword = csKeyword
        )
        return kotlin.runCatching {
            response.body()!!.toRepositoryModel()
        }.onFailure {
            throw Exception(response.errorBody()?.string())
        }
    }
}