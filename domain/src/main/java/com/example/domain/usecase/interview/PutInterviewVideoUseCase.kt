package com.example.domain.usecase.interview

interface PutInterviewVideoUseCase {
    suspend operator fun invoke(
        url: String,
        filePath: String
    )
}