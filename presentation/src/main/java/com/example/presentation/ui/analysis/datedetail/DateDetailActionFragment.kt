package com.example.presentation.ui.analysis.datedetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.presentation.R
import com.example.presentation.databinding.FragmentDateDetailActionBinding
import com.example.presentation.ui.MainViewModel
import com.example.presentation.ui.analysis.ChartManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DateDetailActionFragment(private val detailArgs: DateDetailFragmentArgs) : Fragment() {
    private var _binding: FragmentDateDetailActionBinding? = null
    private val binding: FragmentDateDetailActionBinding
        get() = _binding!!

    private val dateDetailViewModel: DateDetailViewModel by viewModels()
    private val mainViewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDateDetailActionBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getActionAnalysis()
        setRecyclerView()

    }

    private fun getActionAnalysis() {
        viewLifecycleOwner.lifecycleScope.launch {
            dateDetailViewModel.getActionAnalysis(
                mainViewModel.userAuth,
                detailArgs.interviewInfo.interviewId
            )
        }
    }

    private fun setRecyclerView() {
        val dateAnalysisListAdapter =
            DateDetailActionListAdapter(requireContext(), requireActivity())
        binding.recyclerViewVideo.adapter = dateAnalysisListAdapter

        viewLifecycleOwner.lifecycleScope.launch {
            dateDetailViewModel.isActionAnalysisSuccess.collectLatest { isActionAnalysisSuccess ->
                if (isActionAnalysisSuccess) {
                    dateAnalysisListAdapter.submitList(dateDetailViewModel.actionAnaylses)
                    ChartManager.setLineChart(
                        dateDetailViewModel.eyesEntries,
                        binding.lineChartEyes,
                        getString(R.string.eyes_contact_by_turns)
                    )
                    ChartManager.setLineChart(
                        dateDetailViewModel.poseEntries,
                        binding.lineChartPose,
                        getString(R.string.pose_analysis_by_turns)
                    )
                }

            }

        }
    }


}