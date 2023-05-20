package com.example.data.remote.model.interview

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
internal data class InterviewInfo(
    val userId: Int,
    val interviewId: Int,
    val behaviorId: Int
) {
    fun toDomainModel() = com.example.domain.model.interview.InterviewInfo(
        userId = userId,
        interviewId = interviewId,
        behaviorId = behaviorId
    )
}
