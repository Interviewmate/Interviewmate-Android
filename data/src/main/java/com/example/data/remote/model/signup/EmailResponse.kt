package com.example.data.remote.model.signup

import com.example.data.repository.model.EmailResponseRepositoryModel
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
internal data class EmailResponse(
    val status: String,
    val code: String,
    val message: String,
    val result: String
){
    fun toRepositoryModel() = EmailResponseRepositoryModel(
        status = status,
        code = code,
        message = message,
        result = result
    )
}