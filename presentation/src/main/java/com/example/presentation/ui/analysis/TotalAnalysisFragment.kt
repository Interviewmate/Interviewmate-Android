package com.example.presentation.ui.analysis

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.presentation.R
import com.example.presentation.databinding.FragmentTotalAnalysisBinding
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.Description
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.utils.ColorTemplate


class TotalAnalysisFragment : Fragment() {
    private var _binding: FragmentTotalAnalysisBinding? = null
    private val binding: FragmentTotalAnalysisBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTotalAnalysisBinding.inflate(
            layoutInflater
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val eyesEntries: ArrayList<Entry> = ArrayList()
        eyesEntries.add(Entry(1f, 5f))
        eyesEntries.add(Entry(2f, 8f))
        eyesEntries.add(Entry(3f, 10f))
        eyesEntries.add(Entry(4f, 8f))
        eyesEntries.add(Entry(5f, 12f))
        eyesEntries.add(Entry(6f, 6f))
        eyesEntries.add(Entry(7f, 4f))

        val poseEntries: ArrayList<Entry> = ArrayList()
        poseEntries.add(Entry(1f, 7f))
        poseEntries.add(Entry(2f, 10f))
        poseEntries.add(Entry(3f, 2f))
        poseEntries.add(Entry(4f, 18f))
        poseEntries.add(Entry(5f, 15f))
        poseEntries.add(Entry(6f, 6f))
        poseEntries.add(Entry(7f, 8f))

        setNotChangeSeekbar()
        setPieChartKeyword()
        setLineChart(eyesEntries, binding.lineChartEyes)
        setLineChart(poseEntries, binding.lineChartPose)
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun setNotChangeSeekbar() {
        binding.seekBar.setOnTouchListener { _, _ ->
            true
        }
    }

    private fun setPieChartKeyword() {
        binding.pieChartKeyword.apply {
            setUsePercentValues(true)
            description.isEnabled = false
            setExtraOffsets(5f, 10f, 5f, 5f)
            isDrawHoleEnabled = true
            setHoleColor(Color.WHITE)
            transparentCircleRadius = 61f

            val yValues: ArrayList<PieEntry> = ArrayList()
            with(yValues) {
                add(PieEntry(34f, "NETWORK"))
                add(PieEntry(23f, "ALGORITHM"))
                add(PieEntry(10f, "JAVA"))
                add(PieEntry(3f, "ANDROID"))
            }
            animateY(1000, Easing.EaseInOutCubic)

            val dataSet: PieDataSet = PieDataSet(yValues, "")
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
                des.text = "답변한 키워드 비율"
                des.textColor = Color.BLACK
                des.textSize = 13f
                description = des
            }

            data = pieData
        }
    }

    private fun setLineChart(entries: ArrayList<Entry>, lineChart: LineChart) {
        lineChart.apply {
            val lineDataSet = LineDataSet(entries, "회차별 시선 처리")
            lineDataSet.setLineWidth(2f)
            lineDataSet.setCircleRadius(6f)
            lineDataSet.setCircleColor(
                ContextCompat.getColor(
                context,
                R.color.deep_blue
            ))
            lineDataSet.circleHoleColor = ContextCompat.getColor(
                context,
                R.color.deep_blue
            )
            lineDataSet.setColor(ContextCompat.getColor(
                context,
                R.color.sky_blue
            ))
            lineDataSet.setDrawCircleHole(true)
            lineDataSet.setDrawCircles(true)
            lineDataSet.setDrawHorizontalHighlightIndicator(false)
            lineDataSet.setDrawHighlightIndicators(false)
            lineDataSet.setDrawValues(false)

            val lineData = LineData(lineDataSet)
            setData(lineData)

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

            setDoubleTapToZoomEnabled(false)
            setDrawGridBackground(false)
            setDescription(description)
            animateY(2000, Easing.EaseInCubic)
            invalidate()
        }
    }
}