package com.example.presentation.ui.interview

import android.content.Context
import android.content.res.ColorStateList
import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.presentation.R
import com.example.presentation.databinding.FragmentJobSkillBinding
import com.example.presentation.model.interview.Cs
import com.example.presentation.ui.MainViewModel
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class KeywordFragment : Fragment() {
    private var _binding: FragmentJobSkillBinding? = null
    private val binding: FragmentJobSkillBinding
        get() = _binding!!

    private val interviewViewModel: InterviewViewModel by viewModels()
    private val mainViewModel: MainViewModel by activityViewModels()

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

        chipId = 0

        setSkills()

        binding.btnNext.setOnClickListener {
            viewLifecycleOwner.lifecycleScope.launch {
                contentChipGroup.checkedChipIds.forEach {
                    interviewViewModel.keywords.add(Cs.values()[it].text)
                }
                interviewViewModel.getInterviewQuestions(mainViewModel.userAuth)
                interviewViewModel.isQuestionSuccess.collect { isQuestionSuccess ->
                    if (isQuestionSuccess) {
                        findNavController().navigate(R.id.action_keywordFragment_to_noticeFragment)
                    } else {
                        Snackbar.make(
                            binding.root,
                            R.string.error_keyword,
                            Snackbar.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }
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
}