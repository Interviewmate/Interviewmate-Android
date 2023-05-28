package com.example.presentation.ui.analysis.datedetail

import androidx.lifecycle.ViewModel
import com.example.domain.model.analysis.ActionAnalysisInfo
import com.example.domain.model.signup.UserAuth
import com.example.domain.usecase.analysis.GetActionAnalysisUseCase
import com.example.presentation.model.Status
import com.example.presentation.model.analysis.InterviewVideo
import com.github.mikephil.charting.data.Entry
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

@HiltViewModel
class DateDetailViewModel @Inject constructor(
    private val getActionAnalysisUseCase: GetActionAnalysisUseCase
) : ViewModel() {

    private val _isActionAnalysisSuccess = MutableSharedFlow<Boolean>()
    val isActionAnalysisSuccess = _isActionAnalysisSuccess

    var actionAnaylses = listOf<InterviewVideo>()
    val eyesEntries = arrayListOf<Entry>()
    val poseEntries = arrayListOf<Entry>()

    suspend fun getActionAnalysis(userAuth: UserAuth, interviewId: Int) {
        getActionAnalysisUseCase(userAuth.accessToken, interviewId)
            .catch {
                _isActionAnalysisSuccess.emit(false)
            }
            .collectLatest { actionResponse ->
                if (actionResponse.status == Status.SUCCESS.name) {
                    actionAnaylses = makeInterviewVideoForm(actionResponse.result)
                    makeEntries(eyesEntries, actionResponse.result)
                    makeEntries(poseEntries, actionResponse.result)
                    _isActionAnalysisSuccess.emit(true)
                } else {
                    _isActionAnalysisSuccess.emit(false)
                }

            }
    }

    private fun makeInterviewVideoForm(actionAnalysisInfo: List<ActionAnalysisInfo>) =
        actionAnalysisInfo.map { InterviewVideo(question = it.question, url = it.url) }

    fun makeEntries(entries: ArrayList<Entry>, actionAnalysisInfo: List<ActionAnalysisInfo>) {
        actionAnalysisInfo.forEachIndexed { index, actionAnalysis ->
            entries.add(
                Entry(
                    (index + 1).toFloat(),
                    actionAnalysis.gazeAnalysis.sumOf { it.duringSec }.toFloat()
                )
            )
        }
    }

}