package com.example.presentation.ui.jobskill

import android.content.Context
import android.content.res.ColorStateList
import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.presentation.R
import com.example.presentation.databinding.FragmentJobSkillBinding
import com.example.presentation.model.jobskill.AiDeveloper
import com.example.presentation.model.jobskill.SwDeveloper
import com.example.presentation.ui.signup.SignUpViewModel
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup

class JobFragment : Fragment() {
    private var _binding: FragmentJobSkillBinding? = null
    private val binding: FragmentJobSkillBinding
        get() = _binding!!

    private val signUpViewModel: SignUpViewModel by activityViewModels()

    private val contentViews = arrayOfNulls<View>(2)
    private val contentTextViews = arrayOfNulls<TextView>(2)
    private val contentChipGroups = arrayOfNulls<ChipGroup>(2)
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

        binding.btnNext.setOnClickListener {
            val chipId = contentChipGroups[0]?.checkedChipId
            signUpViewModel.job =
                (contentChipGroups[0]?.getChildAt(chipId ?: 0) as Chip).text.toString()
            Log.d("LanguageFragment", "viewModel ${signUpViewModel.job} ${signUpViewModel.keyword}")

            findNavController().navigate(R.id.action_jobFragment_to_skillFramgnet)
        }

        setSkills()
    }

    fun setSkills() {
        binding.tvHeader.text = getString(R.string.select_job)
        binding.tvDescription.text = getString(R.string.select_job_description)
        addLayout()
    }

    fun addLayout() {
        val titles = arrayListOf(getString(R.string.sw_developer), getString(R.string.ai_developer))
        val chipTexts = mutableListOf<MutableList<String>>()
        val texts = mutableListOf<String>()
        SwDeveloper.values().forEach {
            texts.add(it.text)
        }
        chipTexts.add(texts.toMutableList())
        texts.clear()
        AiDeveloper.values().forEach {
            texts.add(it.text)
        }
        chipTexts.add(texts)

        for (idx in contentViews.indices) {
            val layoutInflater =
                activity?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val containView = layoutInflater.inflate(R.layout.item_skills, null)
            binding.layoutSkills.addView(containView)

            contentViews[idx] = containView as View
            contentTextViews[idx] = contentViews[idx]?.findViewById(R.id.tv_skill_title) as TextView
            contentChipGroups[idx] = contentViews[idx]?.findViewById(R.id.chip_group)

            contentTextViews[idx]?.text = titles[idx]
            chipTexts[idx].forEach {
                addChip(it, contentChipGroups[idx])
            }
        }
    }

    fun addChip(skillName: String, chipGroup: ChipGroup?) {
        chipGroup?.addView(Chip(context).apply {
            id = chipId++
            text = skillName
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