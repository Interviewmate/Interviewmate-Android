package com.example.data.remote.source

import com.example.data.remote.model.interview.InterviewInfo
import com.example.data.remote.model.interview.UserId
import com.example.data.repository.model.ResponseRepositoryModel

internal interface InterviewRemoteDataSource {

    suspend fun setInterview(accessToken: String, userId: UserId): Result<ResponseRepositoryModel<InterviewInfo>>

}