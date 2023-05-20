package com.example.data.repository

import com.example.data.remote.mapper.InterviewMapper
import com.example.data.remote.source.InterviewRemoteDataSource
import com.example.domain.model.ResponseUseCaseModel
import com.example.domain.model.interview.InterviewId
import com.example.domain.model.interview.QuestionInfo
import com.example.domain.model.interview.UserId
import com.example.domain.repository.InterviewRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
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
}