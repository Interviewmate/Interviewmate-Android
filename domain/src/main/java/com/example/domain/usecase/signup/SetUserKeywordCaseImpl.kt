package com.example.domain.usecase.signup

import com.example.domain.model.ResponseUseCaseModel
import com.example.domain.model.signup.UserKeyword
import com.example.domain.repository.SignUpRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SetUserKeywordCaseImpl @Inject constructor(
    private val setSignUpRepository: SignUpRepository
): SetUserKeywordCase{
    override suspend fun invoke(
        userId: Int,
        keywords: List<String>
    ): Flow<ResponseUseCaseModel<String>> =
        setSignUpRepository.setKeywords(
            UserKeyword(
                userId = userId,
                keywords = keywords
            )
        )
}