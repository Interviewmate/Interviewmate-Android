package com.example.data.remote.model.interview

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
internal data class InterviewId(
    val interviewId: Int
) {
    fun toDomainModel() = com.example.domain.model.interview.InterviewId(
        interviewId = interviewId
    )
}
