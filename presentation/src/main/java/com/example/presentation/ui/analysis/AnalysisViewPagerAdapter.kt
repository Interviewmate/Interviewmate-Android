package com.example.presentation.ui.analysis

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class AnalysisViewPagerAdapter(
    fragment: Fragment,
    private val fragments: ArrayList<Fragment>
) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = fragments.size

    override fun createFragment(position: Int): Fragment = fragments[position]

}