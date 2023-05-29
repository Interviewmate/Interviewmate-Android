package com.example.presentation.ui.analysis

import android.graphics.Color
import androidx.core.content.ContextCompat
import com.example.domain.model.analysis.ActionAnalysisInfo
import com.example.domain.model.analysis.KeywordInfo
import com.example.domain.model.analysis.Score
import com.example.presentation.R
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.components.Description
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.utils.ColorTemplate

object ChartManager {
    fun setPieChartKeyword(entries: ArrayList<PieEntry>, pieChart: PieChart, title: String) {
        pieChart.apply {
            setUsePercentValues(true)
            description.isEnabled = false
            setExtraOffsets(5f, 10f, 5f, 5f)
            isDrawHoleEnabled = true
            setHoleColor(Color.WHITE)
            transparentCircleRadius = 61f

            animateY(1000, Easing.EaseInOutCubic)

            val dataSet = PieDataSet(entries, "")
            with(dataSet) {
                sliceSpace = 5f
                selectionShift = 5f
                setColors(*ColorTemplate.JOYFUL_COLORS)
            }

            val pieData = PieData(dataSet)
            with(pieData) {
                setValueTextSize(15f)
                setValueTextColor(Color.BLACK)
                setEntryLabelColor(Color.BLACK)
                val des = Description()
                des.text = title
                des.textColor = Color.BLACK
                des.textSize = 13f
                description = des
            }

            data = pieData
        }
    }

    fun setLineChart(entries: ArrayList<Entry>, lineChart: LineChart, title: String) {
        lineChart.apply {
            val lineDataSet = LineDataSet(entries, title)
            lineDataSet.lineWidth = 2f
            lineDataSet.circleRadius = 6f
            lineDataSet.setCircleColor(
                ContextCompat.getColor(
                    context,
                    R.color.deep_blue
                )
            )
            lineDataSet.circleHoleColor = ContextCompat.getColor(
                context,
                R.color.deep_blue
            )
            lineDataSet.color = ContextCompat.getColor(
                context,
                R.color.sky_blue
            )
            lineDataSet.setDrawCircleHole(true)
            lineDataSet.setDrawCircles(true)
            lineDataSet.setDrawHorizontalHighlightIndicator(false)
            lineDataSet.setDrawHighlightIndicators(false)
            lineDataSet.setDrawValues(false)

            val lineData = LineData(lineDataSet)
            data = lineData

            val xAxis: XAxis = getXAxis()
            xAxis.position = XAxis.XAxisPosition.BOTTOM
            xAxis.textColor = Color.BLACK
            xAxis.enableGridDashedLine(8f, 24f, 0f)

            val yLAxis: YAxis = getAxisLeft()
            yLAxis.textColor = Color.BLACK

            val yRAxis: YAxis = getAxisRight()
            yRAxis.setDrawLabels(false)
            yRAxis.setDrawAxisLine(false)
            yRAxis.setDrawGridLines(false)

            val description = Description()
            description.text = ""

            isDoubleTapToZoomEnabled = false
            setDrawGridBackground(false)
            setDescription(description)
            animateY(2000, Easing.EaseInCubic)
            invalidate()
        }
    }

    fun makeEntriesFromAction(
        type: Int,
        actionAnalysisInfo: List<ActionAnalysisInfo>
    ): ArrayList<Entry> {
        val entries = arrayListOf<Entry>()
        actionAnalysisInfo.forEachIndexed { index, actionAnalysis ->
            entries.add(
                Entry(
                    (index + 1).toFloat(),
                    if (type == EYE) {
                        actionAnalysis.gazeAnalysis.sumOf { it.duringSec }.toFloat()
                    } else {
                        actionAnalysis.poseAnalysis.sumOf { it.duringSec }.toFloat()
                    }
                )
            )
        }
        return entries
    }

    fun makeEntriesFromTotal(scores: List<Score>): ArrayList<Entry> {
        val entries = arrayListOf<Entry>()
        scores.forEachIndexed { index, score ->
            entries.add(Entry((index + 1).toFloat(), score.score.toFloat()))
        }
        return entries
    }

    fun makePieEntries(keywordDistribution: List<KeywordInfo>): ArrayList<PieEntry> {
        val entries = arrayListOf<PieEntry>()
        keywordDistribution.forEach { keyword ->
            entries.add(PieEntry(keyword.count.toFloat(), keyword.name))
        }
        return entries
    }

    const val EYE = 1
    const val POSE = 2
}