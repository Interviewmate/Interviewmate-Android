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
        deepQuestions = makeDeepQuestionsForm(deepQuestions)
    )

    fun makeDeepQuestionsForm(deepQuestions: List<String>): String {
        var result = ""
        deepQuestions.forEachIndexed { index, s ->
            result += if (index != deepQuestions.size - 1) {
                "-$s\n"
            } else {
                "-$s"
            }
        }
        return result
    }
}
