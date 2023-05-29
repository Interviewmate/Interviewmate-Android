package com.example.data.repository

import com.example.data.remote.source.AnalysisRemoteDataSource
import com.example.domain.model.ResponseUseCaseModel
import com.example.domain.model.analysis.*
import com.example.domain.repository.AnalysisRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

internal class AnalysisRepositoryImpl @Inject constructor(
    private val analysisRemoteDataSource: AnalysisRemoteDataSource
) : AnalysisRepository {

    override suspend fun getMonthInterviews(
        accessToken: String,
        userId: Int,
        yearMonth: String
    ): Flow<ResponseUseCaseModel<MonthInterviewInfo>> =
        flow {
            analysisRemoteDataSource.getMonthInterviews(
                accessToken = accessToken,
                userId = userId,
                yearMonth = yearMonth
            )
                .onSuccess { responseRepositoryModel ->
                    emit(responseRepositoryModel.toDomainModel(responseRepositoryModel.result.toDomainModel()))
                }
                .onFailure {
                    throw it
                }
        }

    override suspend fun getDayInterviews(
        accessToken: String,
        userId: Int,
        date: String
    ): Flow<ResponseUseCaseModel<List<DayInterviewInfo>>> =
        flow {
            analysisRemoteDataSource.getDayInterviews(
                accessToken = accessToken,
                userId = userId,
                date = date
            )
                .onSuccess { responseRepositoryModel ->
                    emit(responseRepositoryModel.toDomainModel(responseRepositoryModel.result.mapIndexed { index, dayInterviewInfo ->
                        dayInterviewInfo.toDomainModel(
                            index + 1
                        )
                    }))
                }
                .onFailure {
                    throw it
                }
        }

    override suspend fun getCheckAnalysisOver(
        accessToken: String,
        interviewId: Int
    ): Flow<ResponseUseCaseModel<String>> =
        flow {
            analysisRemoteDataSource.getCheckAnalysisOver(
                accessToken = accessToken,
                interviewId = interviewId
            )
                .onSuccess { responseRepositoryModel ->
                    emit(responseRepositoryModel.toDomainModel(responseRepositoryModel.result))
                }
                .onFailure {
                    throw it
                }
        }

    override suspend fun getActionAnalysis(
        accessToken: String,
        interviewId: Int
    ): Flow<ResponseUseCaseModel<List<ActionAnalysisInfo>>> =
        flow {
            analysisRemoteDataSource.getActionAnalysis(
                accessToken = accessToken,
                interviewId = interviewId
            )
                .onSuccess { responseRepositoryModel ->
                    emit(responseRepositoryModel.toDomainModel(responseRepositoryModel.result.map { it.toDomainModel() }))
                }
                .onFailure {
                    throw it
                }
        }

    override suspend fun getAnswerAnalysis(
        accessToken: String,
        interviewId: Int
    ): Flow<ResponseUseCaseModel<List<AnswerAnalysisInfo>>> =
        flow {
            analysisRemoteDataSource.getAnswerAnalysis(
                accessToken = accessToken,
                interviewId = interviewId
            )
                .onSuccess { responseRepositoryModel ->
                    emit(responseRepositoryModel.toDomainModel(responseRepositoryModel.result.map { it.toDomainModel() }))
                }
                .onFailure {
                    throw it
                }
        }

    override suspend fun getTotalAnalysis(
        accessToken: String,
        userId: Int
    ): Flow<ResponseUseCaseModel<List<TotalAnalysisInfo>>> =
        flow {
            analysisRemoteDataSource.getTotalAnalysis(
                accessToken = accessToken,
                userId = userId
            )
                .onSuccess { responseRepositoryModel ->
                    emit(responseRepositoryModel.toDomainModel(responseRepositoryModel.result.map { it.toDomainModel() }))
                }
                .onFailure {
                    throw it
                }
        }

}