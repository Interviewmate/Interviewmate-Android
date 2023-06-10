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

        initBinding()
        getTotalAnalysis()
        setNotChangeSeekbar()
    }

    private fun initBinding() {
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = dateAnalysisViewModel
    }

    private fun getTotalAnalysis() {
        viewLifecycleOwner.lifecycleScope.launch {
            dateAnalysisViewModel.getTotalAnalysis(mainViewModel.userAuth)
        }

        viewLifecycleOwner.lifecycleScope.launch {
            dateAnalysisViewModel.isTotalAnalysisSuccess.collectLatest { isTotalAnalysisSuccess ->
                if (isTotalAnalysisSuccess) {
                    ChartManager.setPieChartKeyword(
                        dateAnalysisViewModel.keywordEntries,
                        binding.pieChartKeyword,
                        getString(R.string.percent_of_keyword)
                    )
                    ChartManager.setLineChart(
                        ChartManager.TOTAL,
                        dateAnalysisViewModel.eyesEntries,
                        binding.lineChartEyes,
                        getString(R.string.eyes_contact_by_turns)
                    )
                    ChartManager.setLineChart(
                        ChartManager.TOTAL,
                        dateAnalysisViewModel.poseEntries,
                        binding.lineChartPose,
                        getString(R.string.pose_analysis_by_turns)
                    )
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