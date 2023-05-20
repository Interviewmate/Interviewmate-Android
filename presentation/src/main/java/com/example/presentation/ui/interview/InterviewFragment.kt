package com.example.presentation.ui.interview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.presentation.R
import com.example.presentation.databinding.FragmentInterviewBinding

class InterviewFragment : Fragment() {
    private var _binding: FragmentInterviewBinding? = null
    private val binding: FragmentInterviewBinding
        get() = _binding!!

    private val interviewViewModel: InterviewViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentInterviewBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnDoInterview.setOnClickListener{
            findNavController().navigate(R.id.action_interviewFragment_to_keywordFragment)
        }
    }

}