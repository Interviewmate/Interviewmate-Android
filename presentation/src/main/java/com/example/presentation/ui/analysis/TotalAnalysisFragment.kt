package com.example.presentation.ui.analysis

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.presentation.R
import com.example.presentation.databinding.FragmentTotalAnalysisBinding
import com.example.presentation.ui.MainViewModel
import com.github.mikephil.charting.data.*
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class TotalAnalysisFragment : Fragment() {
    private var _binding: FragmentTotalAnalysisBinding? = null
    private val binding: FragmentTotalAnalysisBinding
        get() = _binding!!

    private val dateAnalysisViewModel: DateAnalysisViewModel by viewModels()
    private val mainViewModel: MainViewModel by activityViewModels()

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

        getTotalAnalysis()

        val keywordEntries: ArrayList<PieEntry> = ArrayList()
        keywordEntries.add(PieEntry(34f, "NETWORK"))
        keywordEntries.add(PieEntry(23f, "ALGORITHM"))
        keywordEntries.add(PieEntry(10f, "JAVA"))
        keywordEntries.add(PieEntry(3f, "ANDROID"))

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
        ChartManager.setPieChartKeyword(
            keywordEntries,
            binding.pieChartKeyword,
            getString(R.string.percent_of_keyword)
        )
        ChartManager.setLineChart(
            eyesEntries,
            binding.lineChartEyes,
            getString(R.string.eyes_contact_by_turns)
        )
        ChartManager.setLineChart(
            poseEntries,
            binding.lineChartPose,
            getString(R.string.pose_analysis_by_turns)
        )
    }

    private fun getTotalAnalysis() {
        viewLifecycleOwner.lifecycleScope.launch {
            dateAnalysisViewModel.getTotalAnalysisOver(mainViewModel.userAuth)
            dateAnalysisViewModel.isTotalAnalysisSuccess.collectLatest { isTotalAnalysisSuccess ->
                if (isTotalAnalysisSuccess) {

                } else {
                    Snackbar.make(
                        binding.root,
                        R.string.analysis_not_yet,
                        Snackbar.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun setNotChangeSeekbar() {
        binding.seekBar.setOnTouchListener { _, _ ->
            true
        }
    }
}