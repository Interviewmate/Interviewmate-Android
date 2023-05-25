package com.example.data.remote.model.mypage

import com.squareup.moshi.JsonClass
import com.example.domain.model.mypage.MyPageUserInfo

@JsonClass(generateAdapter = true)
internal data class MyPageUserInfo(
    val nickname: String,
    val job: String
){
    fun toDomainModel() = MyPageUserInfo(
        nickname = nickname,
        job = job
    )
}