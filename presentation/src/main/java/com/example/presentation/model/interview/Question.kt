package com.example.presentation.model.interview

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Question(
    val questionId: Int,
    val content: String,
    val keyword: String
) : Parcelable
