package com.example.domain.model.analysis

data class AnswerAnalysisInfo(
    val question: String,
    val answer: String,
    val answerAnalysis: String,
    val bestAnswer: String,
    val keyword: String,
    val deepQuestions: String
)