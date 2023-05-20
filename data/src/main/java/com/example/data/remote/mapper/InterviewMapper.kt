package com.example.data.remote.mapper

import com.example.domain.model.interview.PreSignedInfo
import com.example.domain.model.interview.UserId

internal object InterviewMapper {

    fun mapperToUserId(userId: UserId) =
        com.example.data.remote.model.interview.UserId(
            userId = userId.userId
        )

    fun mapperToBearerToken(accessToken: String) =
        "Bearer $accessToken"

    fun mapperToPreSignedUrl(preSignedInfo: PreSignedInfo) =
        com.example.data.remote.model.interview.PreSignedInfo(
            userId = preSignedInfo.userId,
            number = preSignedInfo.number,
            directory = preSignedInfo.directory
        )
}