package com.example.presentation.ui.analysis.datedetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.presentation.databinding.FragmentDateDetailActionBinding
import com.example.presentation.model.analysis.InterviewVideo
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
        setRecyclerView()
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