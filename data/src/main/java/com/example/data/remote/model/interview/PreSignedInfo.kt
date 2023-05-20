package com.example.data.remote.model.interview

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
internal data class PreSignedInfo(
    val userId: Int,
    val number: Int,
    val directory: String
)