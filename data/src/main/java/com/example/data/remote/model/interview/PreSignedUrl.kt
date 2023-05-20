package com.example.data.remote.model.interview

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
internal data class PreSignedUrl(
    val preSignedUrl: Array<String>
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as PreSignedUrl

        if (!preSignedUrl.contentEquals(other.preSignedUrl)) return false

        return true
    }

    override fun hashCode(): Int {
        return preSignedUrl.contentHashCode()
    }

    fun toDomainModel() = com.example.domain.model.interview.PreSignedUrl(
        preSignedUrl = preSignedUrl
    )
}
