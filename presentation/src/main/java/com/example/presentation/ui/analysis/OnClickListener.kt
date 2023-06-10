package com.example.presentation.ui.analysis

import com.example.domain.model.analysis.DayInterviewInfo
import com.example.presentation.model.analysis.Date

interface OnClickDateListener {
    fun onClickDate(date: Date)
}

interface OnClickInterviewListener {
    fun onClickInterview(dayInterviewInfo: DayInterviewInfo)
}

interface OnClickMyPageListener {
    fun onClickMyPage(item: String)
}