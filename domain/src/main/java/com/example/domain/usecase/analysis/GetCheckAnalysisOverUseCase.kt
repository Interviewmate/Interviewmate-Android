package com.example.domain.usecase.analysis

import com.example.domain.model.ResponseUseCaseModel
import kotlinx.coroutines.flow.Flow

interface GetCheckAnalysisOverUseCase {

    suspend operator fun invoke(
        accessToken: String,
        interviewId: Int
    ): Flow<ResponseUseCaseModel<String>>

}