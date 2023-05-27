package com.example.domain.usecase.interview

import com.example.domain.model.ResponseUseCaseModel
import com.example.domain.repository.InterviewRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SetInterviewVideoUrlUseCaseImpl @Inject constructor(
    private val interviewRepository: InterviewRepository
) : SetInterviewVideoUrlUseCase {
    override suspend fun invoke(
        accessToken: String,
        interviewId: Int,
        questionId: Int,
        url: String
    ): Flow<ResponseUseCaseModel<String>> =
        interviewRepository.setInterviewVideoUrl(accessToken, interviewId, questionId, url)
}