package com.example.data.remote.model.analysis

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ActionAnalysisInfo(
    val url: String,
    val question: String,
    val score: Int,
    val gazeAnalysis: List<AnalysisSecInfo>,
    val poseAnalysis: List<AnalysisSecInfo>
) {
    fun toDomainModel() = com.example.domain.model.analysis.ActionAnalysisInfo(
        url = url,
        question = question,
        score = score,
        gazeAnalysis = gazeAnalysis.map { it.toDomainModel() },
        poseAnalysis = poseAnalysis.map { it.toDomainModel() }
    )
}