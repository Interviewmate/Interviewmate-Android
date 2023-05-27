package com.example.domain.model.analysis

data class ActionAnalysisInfo(
    val url: String,
    val question: String,
    val score: Int,
    val gazeAnalysis: List<AnalysisSecInfo>,
    val poseAnalysis: List<AnalysisSecInfo>
)
