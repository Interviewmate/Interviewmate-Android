package com.example.data.remote.model.analysis

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
internal data class AnswerAnalysisInfo(
    val question: String,
    val answer: String,
    val answerAnalysis: String,
    val bestAnswer: String,
    val keyword: String,
    val deepQuestions: List<String>
) {
    fun toDomainModel() = com.example.domain.model.analysis.AnswerAnalysisInfo(
        question = question,
        answer = answer,
        answerAnalysis = answerAnalysis,
        bestAnswer = bestAnswer,
        keyword = keyword,
        deepQuestions = deepQuestions
    )
}
