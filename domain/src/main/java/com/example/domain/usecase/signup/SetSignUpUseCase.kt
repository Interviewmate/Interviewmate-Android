package com.example.domain.usecase.signup

interface SetSignUpUseCase {

    suspend operator fun invoke(
        email: String,
        password: String,
        job: String,
        keywords: MutableList<String>
    )

}