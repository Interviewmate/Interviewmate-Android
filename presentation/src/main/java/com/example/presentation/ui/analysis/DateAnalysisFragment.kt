package com.example.presentation.ui.analysis

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.presentation.databinding.FragmentDateAnalysisBinding

class DateAnalysisFragment : Fragment() {
    private var _binding: FragmentDateAnalysisBinding? = null
    private val binding: FragmentDateAnalysisBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDateAnalysisBinding.inflate(layoutInflater)

        return binding.root
    }
}