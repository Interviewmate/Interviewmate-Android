package com.example.data.remote.model.analysis

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
internal data class TotalAnalysisInfo(
    val averageInterviewScore: Int,
    val gazeScore: List<Score>,
    val poseScore: List<Score>,
    val keywordDistribution: List<KeywordInfo>
) {
    fun toDomainModel() = com.example.domain.model.analysis.TotalAnalysisInfo(
        averageInterviewScore = averageInterviewScore,
        gazeScore = gazeScore.map { it.toDomainModel() },
        poseScore = poseScore.map { it.toDomainModel() },
        keywordDistribution = keywordDistribution.map { it.toDomainModel() }
    )
}