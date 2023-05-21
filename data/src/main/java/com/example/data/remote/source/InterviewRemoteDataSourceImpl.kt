package com.example.data.remote.source

import android.util.Log
import com.example.data.remote.mapper.InterviewMapper
import com.example.data.remote.model.interview.*
import com.example.data.remote.network.InterviewApiService
import com.example.data.repository.model.ResponseRepositoryModel
import okhttp3.RequestBody
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

    override suspend fun setS3PreSigned(preSignedInfo: PreSignedInfo): Result<ResponseRepositoryModel<PreSignedUrl>> {
        val response = interviewApiService.setS3PreSigned(preSignedInfo)
        return kotlin.runCatching {
            response.body()!!.toRepositoryModel()
        }.onFailure {
            throw Exception(response.errorBody()?.string())
        }
    }

    override suspend fun putInterviewVideo(url: String, requestBody: RequestBody): Result<Boolean> {
        val response = interviewApiService.putInterviewVideo(url, requestBody)
        return kotlin.runCatching {
            Log.d("putInterviewVideo", "video upload success <${response.string()}>")
            response.string() == ""
        }.onFailure {
            Log.d("putInterviewVideo", "video upload fail ${response.string()}")
            throw Exception("false")
        }
    }
}