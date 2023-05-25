package com.example.data.remote.model.interview

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
internal data class QuestionInfo(
    val questionNum: Int,
    val questionList: List<Question>
) {
    fun toDomainModel() = com.example.domain.model.interview.QuestionInfo(
        questionNum = questionNum,
        questionList = questionList.map { it.toDomainModel() } as ArrayList<com.example.domain.model.interview.Question>
    )
}