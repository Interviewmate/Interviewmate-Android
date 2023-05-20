package com.example.presentation.ui.analysis

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.presentation.databinding.FragmentTotalAnalysisBinding

class TotalAnalysisFragment : Fragment() {
    private var _binding: FragmentTotalAnalysisBinding? = null
    private val binding: FragmentTotalAnalysisBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTotalAnalysisBinding.inflate(
            layoutInflater
        )
        return binding.root
    }
}