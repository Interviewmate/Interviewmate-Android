package com.example.presentation.ui.analysis.datedetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.presentation.R
import com.example.presentation.databinding.FragmentDateDetailActionBinding
import com.example.presentation.model.analysis.InterviewVideo
import com.example.presentation.ui.analysis.ChartManager
import com.github.mikephil.charting.data.Entry
import kotlinx.coroutines.launch

class DateDetailActionFragment : Fragment() {
    private var _binding: FragmentDateDetailActionBinding? = null
    private val binding: FragmentDateDetailActionBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDateDetailActionBinding.inflate(layoutInflater)

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

        setRecyclerView()
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

    private fun setRecyclerView() {
        val dateAnalysisListAdapter =
            DateDetailActionListAdapter(requireContext(), requireActivity())
        binding.recyclerViewVideo.adapter = dateAnalysisListAdapter

        viewLifecycleOwner.lifecycleScope.launch {
            dateAnalysisListAdapter.submitList(
                listOf(
                    InterviewVideo(
                        "OSI 7계층이란?",
                        "https://interviewmate.s3.ap-northeast-2.amazonaws.com/interview/2/0493ed8d-de26-4dac-bc4b-3f6140cdbe1c"
                    ),
                    InterviewVideo(
                        "페이지 로드 시간을 줄이는 세 가지 방법에 관해 이야기 해 보세요.",
                        "https://interviewmate.s3.ap-northeast-2.amazonaws.com/interview/2/0493ed8d-de26-4dac-bc4b-3f6140cdbe1c"
                    ),
                )
            )
        }
    }


}