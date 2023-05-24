package com.example.domain.usecase.interview

import com.example.domain.model.ResponseUseCaseModel
import com.example.domain.repository.InterviewRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SetInterviewAnalysesUseCaseImpl @Inject constructor(
    private val interviewRepository: InterviewRepository
): SetInterviewAnalysesUseCase {
    override suspend fun invoke(accessToken:String, interviewId: Int, objectKey: String): Flow<ResponseUseCaseModel<String>> =
        interviewRepository.setInterviewAnalyses(accessToken, interviewId, objectKey)
}