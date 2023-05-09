package com.example.presentation.ui.analysis

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.presentation.R
import com.example.presentation.databinding.FragmentAnalysisBinding
import com.google.android.material.tabs.TabLayoutMediator

class AnalysisFragment : Fragment() {
    private var _binding: FragmentAnalysisBinding? = null
    private val binding: FragmentAnalysisBinding
        get() = _binding!!

    private lateinit var analysisViewModel: AnalysisViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAnalysisBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewPager.adapter = AnalysisViewPagerAdapter(this)

        val tabTitleList = listOf(
            getString(R.string.date_analysis),
            getString(R.string.total_analysis),
        )

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = tabTitleList[position]
        }.attach()
    }
}