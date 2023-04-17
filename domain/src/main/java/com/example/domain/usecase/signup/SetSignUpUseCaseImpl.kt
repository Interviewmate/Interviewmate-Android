package com.example.domain.usecase.signup

import com.example.domain.model.SignUpUserInfo
import com.example.domain.repository.SignUpRepository
import javax.inject.Inject

class SetSignUpUseCaseImpl @Inject constructor(
    private val setSignUpRepository: SignUpRepository
) : SetSignUpUseCase {

    override suspend fun invoke(
        email: String,
        password: String,
        job: String,
        keywords: MutableList<String>
    ) {
        setSignUpRepository.setSignUp(
            SignUpUserInfo(
                email = email,
                password = password,
                job = job,
                keywords = keywords.toList()
            )
        )
    }

}