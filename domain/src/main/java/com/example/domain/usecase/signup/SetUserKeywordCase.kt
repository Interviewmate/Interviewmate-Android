package com.example.domain.usecase.signup

import com.example.domain.model.ResponseUseCaseModel
import kotlinx.coroutines.flow.Flow

interface SetUserKeywordCase {

    suspend operator fun invoke(
        userId: Int,
        keywords: List<String>
    ): Flow<ResponseUseCaseModel<String>>

}