package com.example.data.remote.model.analysis

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
internal data class ActionAnalysisInfo(
    val score: Int,
    val behaviorAnalyses: List<BehaviorAnalyses>
) {
    fun toDomainModel() = com.example.domain.model.analysis.ActionAnalysisInfo(
        score = score,
        behaviorAnalyses = behaviorAnalyses.map { it.toDomainModel() }
    )
}