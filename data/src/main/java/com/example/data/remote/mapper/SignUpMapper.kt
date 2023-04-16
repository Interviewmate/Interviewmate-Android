package com.example.data.remote.mapper

import com.example.domain.model.SignUpUserInfo

internal object SignUpMapper {

    fun mapperToSignUpUserInfo(signUpUserInfo: SignUpUserInfo) =
        com.example.data.remote.model.signup.SignUpUserInfo(
            email = signUpUserInfo.email,
            password = signUpUserInfo.password,
            job = signUpUserInfo.job,
            keywords = signUpUserInfo.keywords
        )

}