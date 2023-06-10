package com.example.domain.model.analysis

data class TotalAnalysisInfo(
    val averageInterviewScore: Int,
    val gazeScore: List<Score>,
    val poseScore: List<Score>,
    val keywordDistribution: List<KeywordInfo>
)
