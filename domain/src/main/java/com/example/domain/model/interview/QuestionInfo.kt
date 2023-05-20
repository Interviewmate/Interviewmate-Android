package com.example.domain.model.interview

data class QuestionInfo(
    val questionNum: Int,
    val questionList: Array<Question>
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as QuestionInfo

        if (questionNum != other.questionNum) return false
        if (!questionList.contentEquals(other.questionList)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = questionNum
        result = 31 * result + questionList.contentHashCode()
        return result
    }
}
