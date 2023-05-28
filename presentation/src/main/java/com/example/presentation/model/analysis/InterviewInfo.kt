package com.example.presentation.model.analysis

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class InterviewInfo(
    val month: Int,
    val day: Int,
    val dayOfWeek: String,
    val number: String,
    val interviewId: Int
) : Parcelable