package com.example.domain.usecase.interview

import com.example.domain.repository.InterviewRepository
import javax.inject.Inject

class PutInterviewVideoUseCaseImpl @Inject constructor(
    private val interviewRepository: InterviewRepository
) : PutInterviewVideoUseCase {
    override suspend fun invoke(url: String, filePath: String) =
        interviewRepository.putInterviewVideo(url, filePath)
}