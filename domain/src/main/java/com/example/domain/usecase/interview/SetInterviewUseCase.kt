package com.example.domain.usecase.interview

import com.example.domain.model.ResponseUseCaseModel
import com.example.domain.model.interview.InterviewInfo
import com.example.domain.model.signup.UserAuth
import kotlinx.coroutines.flow.Flow

interface SetInterviewUseCase {
    suspend operator fun invoke(userAuth: UserAuth): Flow<ResponseUseCaseModel<InterviewInfo>>
}