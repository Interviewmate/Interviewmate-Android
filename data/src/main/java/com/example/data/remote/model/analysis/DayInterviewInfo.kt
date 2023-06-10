package com.example.data.remote.model.analysis

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
internal data class DayInterviewInfo(
    val interviewId: Int,
    val time: String
) {
    fun toDomainModel(num: Int) = com.example.domain.model.analysis.DayInterviewInfo(
        interviewId = interviewId,
        time = makeTimeForm(time),
        num = "${num}회차"
    )

    private fun makeTimeForm(time: String): String {
        val times = time.split(":")
        return "${times[0]}시 ${times[1]}분"
    }
}