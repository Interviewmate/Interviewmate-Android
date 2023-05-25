package com.example.domain.usecase.interview

import com.example.domain.model.ResponseUseCaseModel
import com.example.domain.model.interview.PreSignedInfo
import com.example.domain.model.interview.PreSignedUrl
import com.example.domain.repository.InterviewRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SetS3PreSignedUseCaseImpl @Inject constructor(
    private val interviewRepository: InterviewRepository
) : SetS3PreSignedUseCase {
    override suspend fun invoke(
        userId: Int,
        number: Int,
        directory: String
    ): Flow<ResponseUseCaseModel<PreSignedUrl>> =
        interviewRepository.setS3PreSigned(
            PreSignedInfo(
                userId = userId,
                number = number,
                directory = directory
            )
        )
}