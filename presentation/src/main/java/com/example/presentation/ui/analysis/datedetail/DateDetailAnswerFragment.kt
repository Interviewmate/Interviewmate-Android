package com.example.presentation.ui.analysis.datedetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.presentation.databinding.FragmentDateDetailAnswerBinding
import com.example.presentation.ui.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DateDetailAnswerFragment(private val detailArgs: DateDetailFragmentArgs) : Fragment() {
    private var _binding: FragmentDateDetailAnswerBinding? = null
    private val binding: FragmentDateDetailAnswerBinding
        get() = _binding!!

    private val dateDetailViewModel: DateDetailViewModel by viewModels()
    private val mainViewModel: MainViewModel by activityViewModels()

    private val dateDetailAnswerListAdapter =
        DateDetailAnswerListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDateDetailAnswerBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getAnswerAnalyses()
        setRecyclerView()
    }

    private fun getAnswerAnalyses() {
        viewLifecycleOwner.lifecycleScope.launch {
            dateDetailViewModel.getAnswerAnalysis(
                mainViewModel.userAuth,
                detailArgs.interviewInfo.interviewId
            )
        }
    }

    private fun setRecyclerView() {
        binding.recyclerView.adapter = dateDetailAnswerListAdapter

        viewLifecycleOwner.lifecycleScope.launch {
            dateDetailViewModel.isAnswerAnalysisSuccess.collectLatest { isAnswerAnalysisSuccess ->
                if (isAnswerAnalysisSuccess) {
                    dateDetailAnswerListAdapter.submitList(dateDetailViewModel.answerAnalyses)
                }
            }
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}