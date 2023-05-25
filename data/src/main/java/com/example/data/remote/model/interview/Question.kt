package com.example.data.remote.model.interview

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
internal data class Question(
    val questionId: Int,
    val content: String,
    val keyword: String
) {
    fun toDomainModel() = com.example.domain.model.interview.Question(
        questionId = questionId,
        content = content,
        keyword = keyword
    )
}
