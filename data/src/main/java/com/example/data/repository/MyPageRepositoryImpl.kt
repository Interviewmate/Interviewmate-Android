package com.example.data.repository

import com.example.data.remote.source.InterviewRemoteDataSource
import com.example.data.remote.source.MyPageRemoteDataSource
import com.example.domain.model.ResponseUseCaseModel
import com.example.domain.model.mypage.IsExist
import com.example.domain.model.mypage.MyPageUserInfo
import com.example.domain.model.signup.UserAuth
import com.example.domain.repository.MyPageRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File
import java.io.FileInputStream
import java.io.InputStream
import javax.inject.Inject

internal class MyPageRepositoryImpl @Inject constructor(
    private val myPageRemoteDataSource: MyPageRemoteDataSource,
    private val interviewRemoteDataSource: InterviewRemoteDataSource
) : MyPageRepository {
    override suspend fun getUserInfo(userAuth: UserAuth): Flow<ResponseUseCaseModel<MyPageUserInfo>> =
        flow {
            myPageRemoteDataSource.getUserInfo(userAuth)
                .onSuccess { responseRepositoryModel ->
                    emit(responseRepositoryModel.toDomainModel(responseRepositoryModel.result.toDomainModel()))
                }
                .onFailure {
                    throw it
                }

        }

    override suspend fun putPortfolio(url: String, file: File): Flow<Boolean> {
        val `in`: InputStream = withContext(Dispatchers.IO) {
            FileInputStream(file)
        }
        val buf = ByteArray(withContext(Dispatchers.IO) {
            `in`.available()
        })
        while (withContext(Dispatchers.IO) {
                `in`.read(buf)
            } !== -1);
        val requestBody: RequestBody =
            buf.toRequestBody(
                "application/octet-stream".toMediaTypeOrNull(),
                0,
                file.readBytes().size
            )
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

    override suspend fun getPortfolioKeyword(
        accessToken: String,
        userId: Int
    ): Flow<ResponseUseCaseModel<String>> =
        flow {
            myPageRemoteDataSource.getPortfoliosKeyword(accessToken, userId)
                .onSuccess { responseRepositoryModel ->
                    emit(responseRepositoryModel.toDomainModel(responseRepositoryModel.result))
                }
                .onFailure {
                    throw it
                }

        }

    override suspend fun getPortfolioExist(
        accessToken: String,
        userId: Int
    ): Flow<ResponseUseCaseModel<IsExist>> =
        flow {
            myPageRemoteDataSource.getPortfolioExist(accessToken, userId)
                .onSuccess { responseRepositoryModel ->
                    emit(responseRepositoryModel.toDomainModel(responseRepositoryModel.result.toDomainModel()))
                }
                .onFailure {
                    throw it
                }
        }
}