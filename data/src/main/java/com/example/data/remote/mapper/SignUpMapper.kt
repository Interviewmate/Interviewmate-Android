package com.example.data.remote.mapper

import com.example.domain.model.signup.LoginUserInfo
import com.example.domain.model.signup.SignUpUserInfo
import com.example.domain.model.signup.UserKeyword

internal object SignUpMapper {

    fun mapperToSignUpUserInfo(signUpUserInfo: SignUpUserInfo) =
        com.example.data.remote.model.signup.SignUpUserInfo(
            email = signUpUserInfo.email,
            password = signUpUserInfo.password,
            nickname = signUpUserInfo.nickname,
            job = signUpUserInfo.job
        )

    fun mapperToLoginUserInfo(loginUserInfo: LoginUserInfo) =
        com.example.data.remote.model.signup.LoginUserInfo(
            email = loginUserInfo.email,
            password = loginUserInfo.password
        )

    fun mapperToUserKeyword(userKeyword: UserKeyword) =
        com.example.data.remote.model.signup.UserKeyword(
            userId = userKeyword.userId,
            keywords = userKeyword.keywords
        )

}