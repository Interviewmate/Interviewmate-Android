package com.example.data.remote.source

import com.example.data.remote.model.interview.InterviewInfo
import com.example.data.remote.model.interview.UserId
import com.example.data.remote.network.InterviewApiService
import com.example.data.repository.model.ResponseRepositoryModel
import javax.inject.Inject

internal class InterviewRemoteDataSourceImpl @Inject constructor(
    private val interviewApiService: InterviewApiService
) : InterviewRemoteDataSource {
    override suspend fun setInterview(accessToken: String, userId: UserId): Result<ResponseRepositoryModel<InterviewInfo>> {
        val response = interviewApiService.setInterview(accessToken, userId)
        return kotlin.runCatching {
            response.body()!!.toRepositoryModel()
        }.onFailure {
            throw Exception(response.errorBody()?.string())
        }
    }
}