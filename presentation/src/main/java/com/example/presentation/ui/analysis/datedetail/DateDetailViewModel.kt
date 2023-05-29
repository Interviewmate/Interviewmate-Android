package com.example.presentation.ui.analysis.datedetail

import androidx.lifecycle.ViewModel
import com.example.domain.model.analysis.ActionAnalysisInfo
import com.example.domain.model.analysis.AnswerAnalysisInfo
import com.example.domain.model.signup.UserAuth
import com.example.domain.usecase.analysis.GetActionAnalysisUseCase
import com.example.domain.usecase.analysis.GetAnswerAnalysisUseCase
import com.example.presentation.model.Status
import com.example.presentation.model.analysis.InterviewVideo
import com.example.presentation.ui.analysis.ChartManager
import com.github.mikephil.charting.data.Entry
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

@HiltViewModel
class DateDetailViewModel @Inject constructor(
    private val getActionAnalysisUseCase: GetActionAnalysisUseCase,
    private val getAnswerAnalysisUseCase: GetAnswerAnalysisUseCase
) : ViewModel() {

    private val _isActionAnalysisSuccess = MutableSharedFlow<Boolean>()
    val isActionAnalysisSuccess = _isActionAnalysisSuccess

    private val _isAnswerAnalysisSuccess = MutableSharedFlow<Boolean>()
    val isAnswerAnalysisSuccess = _isAnswerAnalysisSuccess

    var actionAnaylses = listOf<InterviewVideo>()
    var eyesEntries = arrayListOf<Entry>()
    var poseEntries = arrayListOf<Entry>()

    var answerAnalyses = listOf<AnswerAnalysisInfo>()

    suspend fun getActionAnalysis(userAuth: UserAuth, interviewId: Int) {
        getActionAnalysisUseCase(userAuth.accessToken, interviewId)
            .catch {
                _isActionAnalysisSuccess.emit(false)
            }
            .collectLatest { actionResponse ->
                if (actionResponse.status == Status.SUCCESS.name) {
                    actionAnaylses = makeInterviewVideoForm(actionResponse.result)
                    eyesEntries = ChartManager.makeEntriesFromAction(ChartManager.EYE, actionResponse.result)
                    poseEntries = ChartManager.makeEntriesFromAction(ChartManager.POSE, actionResponse.result)
                    _isActionAnalysisSuccess.emit(true)
                } else {
                    _isActionAnalysisSuccess.emit(false)
                }

            }
    }

    private fun makeInterviewVideoForm(actionAnalysisInfo: List<ActionAnalysisInfo>) =
        actionAnalysisInfo.map { InterviewVideo(question = it.question, url = it.url) }

    suspend fun getAnswerAnalysis(userAuth: UserAuth, interviewId: Int) {
        getAnswerAnalysisUseCase(userAuth.accessToken, interviewId)
            .catch {
                _isActionAnalysisSuccess.emit(false)
            }
            .collectLatest { answerResponse ->
                if (answerResponse.status == Status.SUCCESS.name) {
                    answerAnalyses = answerResponse.result
                    _isAnswerAnalysisSuccess.emit(true)
                } else {
                    _isAnswerAnalysisSuccess.emit(false)
                }
            }
    }

}