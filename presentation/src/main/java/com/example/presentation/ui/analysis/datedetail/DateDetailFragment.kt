package com.example.presentation.ui.analysis.datedetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.presentation.R
import com.example.presentation.databinding.FragmentDateDetailBinding
import com.example.presentation.ui.analysis.AnalysisViewPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator

class DateDetailFragment : Fragment() {
    private var _binding: FragmentDateDetailBinding? = null
    private val binding: FragmentDateDetailBinding
        get() = _binding!!

    private val args: DateDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDateDetailBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewPager.adapter = AnalysisViewPagerAdapter(
            this, arrayListOf(
                DateDetailActionFragment(),
                DateDetailAnswerFragment()
            )
        )

        val tabTitleList = listOf(
            getString(R.string.action_analysis),
            getString(R.string.answer_analysis),
        )

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = tabTitleList[position]
        }.attach()

        binding.tvHeader.text = getString(
            R.string.clicked_day,
            args.interviewInfo.month,
            args.interviewInfo.day,
            args.interviewInfo.dayOfWeek
        )
        binding.tvInterviewNumber.text = args.interviewInfo.number
    }
}