package com.example.presentation.ui.interview

import android.content.Context
import android.content.res.ColorStateList
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.TypedValue
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.example.presentation.MainActivity
import com.example.presentation.R
import com.example.presentation.databinding.FragmentJobSkillBinding
import com.example.presentation.model.interview.Cs
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup

class KeywordFragment : Fragment() {
    private var _binding: FragmentJobSkillBinding? = null
    private val binding: FragmentJobSkillBinding
        get() = _binding!!

    private lateinit var mainActivity: MainActivity

    private lateinit var viewModel: KeywordViewModel

    private lateinit var contentChipGroup: ChipGroup
    private var chipId = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentJobSkillBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mainActivity = activity as MainActivity
        mainActivity.hideBottomNavi(true)

        chipId = 0

        setSkills()
    }

    private fun setSkills() {
        binding.tvHeader.text = getString(R.string.select_keyword)
        binding.tvDescription.text = getString(R.string.select_keyword_description)
        addLayout()
    }

    private fun addLayout() {
        val layoutInflater =
            activity?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val containView = layoutInflater.inflate(R.layout.item_skills, null)
        binding.layoutSkills.addView(containView)

        val contentView = containView as View
        (contentView.findViewById(R.id.tv_skill_title) as TextView).text = getString(R.string.cs)

        contentChipGroup = contentView.findViewById(R.id.chip_group) as ChipGroup
        contentChipGroup.isSingleSelection = false
        Cs.values().forEach {
            addChip(it.text, contentChipGroup)
        }
    }

    private fun addChip(skillName: String, chipGroup: ChipGroup?) {
        chipGroup?.addView(Chip(context).apply {
            text = skillName
            id = chipId++
            setTextSize(TypedValue.COMPLEX_UNIT_DIP, 16F)
            isCheckable = true
            isCheckedIconVisible = false
            chipBackgroundColor = ColorStateList(
                arrayOf(
                    intArrayOf(-android.R.attr.state_checked),
                    intArrayOf(android.R.attr.state_checked)
                ),
                intArrayOf(
                    ContextCompat.getColor(context, R.color.medium_gray),
                    ContextCompat.getColor(context, R.color.yellow)
                )
            )
            setTextColor(ContextCompat.getColor(context, R.color.white))
        })
    }

    override fun onDestroyView() {
        mainActivity.hideBottomNavi(false)

        super.onDestroyView()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(KeywordViewModel::class.java)
        // TODO: Use the ViewModel
    }

}