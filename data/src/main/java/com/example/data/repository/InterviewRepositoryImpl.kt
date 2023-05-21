package com.example.data.repository

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.data.remote.mapper.InterviewMapper
import com.example.data.remote.source.InterviewRemoteDataSource
import com.example.domain.model.ResponseUseCaseModel
import com.example.domain.model.interview.*
import com.example.domain.repository.InterviewRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File
import javax.inject.Inject


internal class InterviewRepositoryImpl @Inject constructor(
    private val interviewRemoteDataSource: InterviewRemoteDataSource
) : InterviewRepository {
    override suspend fun setInterview(
        accessToken: String,
        userId: UserId
    ): Flow<ResponseUseCaseModel<InterviewId>> =
        flow {
            interviewRemoteDataSource.setInterview(
                accessToken,
                InterviewMapper.mapperToUserId(userId)
            )
                .onSuccess { responseRepositoryModel ->
                    emit(responseRepositoryModel.toDomainModel(responseRepositoryModel.result.toDomainModel()))
                }
                .onFailure {
                    throw it
                }

        }

    override suspend fun getInterviewQuestions(
        accessToken: String,
        userId: Int,
        csKeyword: Array<String>
    ): Flow<ResponseUseCaseModel<QuestionInfo>> =
        flow {
            interviewRemoteDataSource.getInterviewQuestions(
                accessToken = accessToken,
                userId = userId,
                csKeyword = csKeyword
            )
                .onSuccess { responseRepositoryModel ->
                    emit(responseRepositoryModel.toDomainModel(responseRepositoryModel.result.toDomainModel()))
                }
                .onFailure {
                    throw it
                }
        }

    override suspend fun setS3PreSigned(preSignedInfo: PreSignedInfo): Flow<ResponseUseCaseModel<PreSignedUrl>> =
        flow {
            interviewRemoteDataSource.setS3PreSigned(
                InterviewMapper.mapperToPreSignedUrl(
                    preSignedInfo
                )
            )
                .onSuccess { responseRepositoryModel ->
                    emit(responseRepositoryModel.toDomainModel(responseRepositoryModel.result.toDomainModel()))
                }
                .onFailure {
                    throw it
                }
        }

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun putInterviewVideo(url: String, filePath: String): Flow<Boolean> {
        val file = File(filePath).readBytes()
        val requestBody: RequestBody =
            file.toRequestBody("video/mp4".toMediaTypeOrNull(), 0, file.size)
        return flow {
            interviewRemoteDataSource.putInterviewVideo(url, requestBody)
                .onSuccess { responseRepositoryModel ->
                    emit(responseRepositoryModel)
                }
                .onFailure {
                    throw it
                }
        }

    }
}