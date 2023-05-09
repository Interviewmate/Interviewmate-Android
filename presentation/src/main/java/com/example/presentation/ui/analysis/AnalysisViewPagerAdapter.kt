package com.example.presentation.ui.analysis

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class AnalysisViewPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    val fragments: ArrayList<Fragment> = arrayListOf(
        DateAnalysisFragment(),
        TotalAnalysisFragment()
    )

    override fun getItemCount(): Int = fragments.size

    override fun createFragment(position: Int): Fragment = fragments[position]

}