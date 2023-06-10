package com.example.data.remote.model.analysis

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
internal data class AnalysisSecInfo(
    val startSec: Double,
    val endSec: Double,
    val duringSec: Double
) {
    fun toDomainModel() = com.example.domain.model.analysis.AnalysisSecInfo(
        startSec = startSec,
        endSec = endSec,
        duringSec = duringSec
    )
}
