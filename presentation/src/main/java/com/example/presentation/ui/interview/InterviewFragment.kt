package com.example.presentation.ui.interview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.presentation.R
import com.example.presentation.databinding.FragmentInterviewBinding
import com.example.presentation.ui.MainViewModel
import kotlinx.coroutines.launch

class InterviewFragment : Fragment() {
    private var _binding: FragmentInterviewBinding? = null
    private val binding: FragmentInterviewBinding
        get() = _binding!!

    private val interviewViewModel: InterviewViewModel by viewModels()
    private val mainViewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentInterviewBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setInterview()
    }

    private fun setInterview() {
        binding.btnDoInterview.setOnClickListener {
            viewLifecycleOwner.lifecycleScope.launch {
                interviewViewModel.setInterview(mainViewModel.userAuth)
                interviewViewModel.isInterviewMade.collect { isInterviewMade ->
                    if (isInterviewMade) {
                        findNavController().navigate(R.id.action_interviewFragment_to_keywordFragment)
                    } else {
                        Toast.makeText(requireContext(), "인터뷰 생성에 실패하였습니다.", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            }

        }
    }

}