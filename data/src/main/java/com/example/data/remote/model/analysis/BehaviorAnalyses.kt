package com.example.data.remote.model.analysis

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
internal data class BehaviorAnalyses(
    val url: String,
    val question: String,
    val gazeAnalysis: List<AnalysisSecInfo>,
    val poseAnalysis: List<AnalysisSecInfo>
) {
    fun toDomainModel() = com.example.domain.model.analysis.BehaviorAnalyses(
        url = url,
        question = question,
        gazeAnalysis = gazeAnalysis.map { it.toDomainModel() },
        poseAnalysis = poseAnalysis.map { it.toDomainModel() }
    )
}
