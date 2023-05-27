package com.example.data.remote.model.analysis

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
internal data class MonthInterviewInfo(
    val count: Int,
    val dateList: List<Int>
) {
    fun toDomainModel() = com.example.domain.model.analysis.MonthInterviewInfo(
        count = count,
        dateList = dateList
    )
}