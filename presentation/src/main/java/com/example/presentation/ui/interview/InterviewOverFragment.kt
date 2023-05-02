package com.example.presentation.ui.interview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.presentation.R
import com.example.presentation.databinding.FragmentInterviewOverBinding

class InterviewOverFragment : Fragment() {
    private var _binding: FragmentInterviewOverBinding? = null
    private val binding: FragmentInterviewOverBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentInterviewOverBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnNext.setOnClickListener {
        }
    }
}