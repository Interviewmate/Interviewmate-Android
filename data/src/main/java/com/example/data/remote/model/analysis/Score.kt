package com.example.data.remote.model.analysis

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
internal data class Score(
    val interviewId: Int,
    val score: Int
) {
    fun toDomainModel() = com.example.domain.model.analysis.Score(
        interviewId = interviewId,
        score = score
    )
}
