package com.example.presentation.ui.analysis

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnTouchListener
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.presentation.R
import com.example.presentation.databinding.FragmentTotalAnalysisBinding
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.components.Description
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
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

        setNotChangeSeekbar()
        setPieChartKeyword()
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
}