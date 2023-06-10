package com.example.domain.model.analysis

data class BehaviorAnalyses(
    val url: String,
    val question: String,
    val gazeAnalysis: List<AnalysisSecInfo>,
    val poseAnalysis: List<AnalysisSecInfo>
)
