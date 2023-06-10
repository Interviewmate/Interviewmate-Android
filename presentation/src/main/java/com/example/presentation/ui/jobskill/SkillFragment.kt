package com.example.presentation.ui.jobskill

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
import androidx.navigation.fragment.findNavController
import com.example.presentation.R
import com.example.presentation.databinding.FragmentJobSkillBinding
import com.example.presentation.model.jobskill.Ai
import com.example.presentation.model.jobskill.Client
import com.example.presentation.model.jobskill.Developer
import com.example.presentation.model.jobskill.Server
import com.example.presentation.ui.signup.SignUpViewModel
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.google.android.material.snackbar.Snackbar

class SkillFragment : Fragment() {
    private var _binding: FragmentJobSkillBinding? = null
    private val binding: FragmentJobSkillBinding
        get() = _binding!!

    private val signUpViewModel: SignUpViewModel by activityViewModels()

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
            val chipId = contentChipGroup.checkedChipId
            if (chipId == NOT_CHECKED) {
                Snackbar.make(
                    binding.root,
                    R.string.select_skill_description,
                    Snackbar.LENGTH_SHORT
                ).show()
            } else {
                signUpViewModel.keyword.add((contentChipGroup.getChildAt(chipId) as Chip).text.toString().uppercase())
                findNavController().navigate(R.id.action_skillFramgnet_to_languageFragment)
            }
        }
    }

    fun setSkills() {
        binding.tvHeader.text = getString(R.string.select_skill)
        binding.tvDescription.text = getString(R.string.select_skill_description)
        addLayout()
    }

    fun addLayout() {
        val layoutInflater =
            activity?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val containView = layoutInflater.inflate(R.layout.item_skills, null)
        binding.layoutSkills.addView(containView)

        val contentView = containView as View
        (contentView.findViewById(R.id.tv_skill_title) as TextView).apply {
            text = when (signUpViewModel.job) {
                Developer.SERVER, Developer.CLIENT -> {
                    signUpViewModel.job?.text
                }
                else -> {
                    getString(R.string.ai)
                }
            }
        }
        contentChipGroup = contentView.findViewById(R.id.chip_group) as ChipGroup
        when (signUpViewModel.job) {
            Developer.SERVER -> {
                Server.values().forEach {
                    addChip(it.text, contentChipGroup)
                }
            }
            Developer.CLIENT -> {
                Client.values().forEach {
                    addChip(it.text, contentChipGroup)
                }
            }
            else -> {
                Ai.values().forEach {
                    addChip(it.text, contentChipGroup)
                }
            }
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

    companion object {
        const val NOT_CHECKED = -1
    }

}