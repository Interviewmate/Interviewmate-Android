package com.example.data.repository

import com.example.data.remote.source.MyPageRemoteDataSource
import com.example.domain.model.ResponseUseCaseModel
import com.example.domain.model.mypage.MyPageUserInfo
import com.example.domain.repository.MyPageRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

internal class MyPageRepositoryImpl @Inject constructor(
    private val myPageRemoteDataSource: MyPageRemoteDataSource
) : MyPageRepository {
    override suspend fun getUserInfo(userId: Int): Flow<ResponseUseCaseModel<MyPageUserInfo>> =
        flow {
            myPageRemoteDataSource.getUserInfo(userId)
                .onSuccess { responseRepositoryModel ->
                    emit(responseRepositoryModel.toDomainModel(responseRepositoryModel.result.toDomainModel()))
                }
                .onFailure {
                    throw it
                }

        }
}