package com.example.data.repository

import com.example.data.remote.source.AnalysisRemoteDataSource
import com.example.domain.model.ResponseUseCaseModel
import com.example.domain.model.analysis.MonthInterviewInfo
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

}