package com.example.data.remote.model.analysis

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
internal data class AnswerList(
    val answerList: List<AnswerAnalysisInfo>
){
    fun toDomainModel() = com.example.domain.model.analysis.AnswerList(
        answerList = answerList.map { it.toDomainModel() }
    )
}
