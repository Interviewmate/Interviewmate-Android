package com.example.domain.usecase.interview

import com.example.domain.model.ResponseUseCaseModel
import com.example.domain.model.interview.QuestionInfo
import com.example.domain.repository.InterviewRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetInterviewQuestionUseCaseImpl @Inject constructor(
    private val interviewRepository: InterviewRepository
) : GetInterviewQuestionsUseCase {
    override suspend fun invoke(
        accessToken: String,
        userId: Int,
        csKeyword: Array<String>
    ): Flow<ResponseUseCaseModel<QuestionInfo>> =
        interviewRepository.getInterviewQuestions(
            accessToken = accessToken,
            userId = userId,
            csKeyword = csKeyword
        )
}