package com.example.domain.usecase.interview

import com.example.domain.model.ResponseUseCaseModel
import com.example.domain.model.interview.InterviewInfo
import com.example.domain.model.interview.UserId
import com.example.domain.model.signup.UserAuth
import com.example.domain.repository.InterviewRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SetInterviewUseCaseImpl @Inject constructor(
    private val interviewRepository: InterviewRepository
) : SetInterviewUseCase {
    override suspend fun invoke(userAuth: UserAuth): Flow<ResponseUseCaseModel<InterviewInfo>> =
        interviewRepository.setInterview(userAuth.accessToken, UserId(userAuth.userId))
}