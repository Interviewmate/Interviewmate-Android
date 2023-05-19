package com.example.presentation.ui.analysis

import com.example.presentation.model.analysis.Date
import com.example.presentation.model.analysis.DateAnalysis

interface OnClickDateListener {
    fun onClickDate(date: Date)
}

interface OnClickInterviewListener {
    fun onClickInterview(dateAnalysis: DateAnalysis)
}