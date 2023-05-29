package com.example.presentation.ui.jobskill

import android.content.Context
import android.content.Intent
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
import androidx.lifecycle.lifecycleScope
import com.example.presentation.MainActivity
import com.example.presentation.R
import com.example.presentation.databinding.FragmentJobSkillBinding
import com.example.presentation.model.jobskill.Language
import com.example.presentation.ui.signup.SignUpViewModel
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch

class LanguageFragment : Fragment() {
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
        setSignUp()
    }

    private fun setSkills() {
        binding.tvHeader.text = getString(R.string.select_language)
        binding.tvDescription.text = getString(R.string.select_language_description)
        addLayout()
    }

    private fun addLayout() {
        val layoutInflater =
            activity?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val containView = layoutInflater.inflate(R.layout.item_skills, null)
        binding.layoutSkills.addView(containView)

        val contentView = containView as View
        (contentView.findViewById(R.id.tv_skill_title) as TextView).apply {
            text = getString(R.string.language)
        }
        contentChipGroup = contentView.findViewById(R.id.chip_group) as ChipGroup
        Language.values().forEach {
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

    private fun setSignUp() {
        val intent = Intent(activity, MainActivity::class.java)

        binding.btnNext.apply {
            text = getString(R.string.finish)
            setOnClickListener {
                val chipId = contentChipGroup.checkedChipId
                if (chipId == SkillFragment.NOT_CHECKED) {
                    Snackbar.make(
                        binding.root,
                        R.string.select_language_description,
                        Snackbar.LENGTH_SHORT
                    ).show()
                } else {
                    signUpViewModel.keyword.add((contentChipGroup.getChildAt(chipId) as Chip).text.toString().uppercase())
                    viewLifecycleOwner.lifecycleScope.launch {
                        signUpViewModel.setKeywords()
                        signUpViewModel.isSuccessKeyword.collect {
                            if (it) {
                                signUpViewModel.setLogin(
                                    signUpViewModel.email,
                                    signUpViewModel.password
                                )
                            } else {
                                Snackbar.make(
                                    binding.root,
                                    R.string.error_keyword,
                                    Snackbar.LENGTH_SHORT
                                ).show()
                            }
                        }
                    }
                    viewLifecycleOwner.lifecycleScope.launch {
                        signUpViewModel.isSuccessLogin.collect {
                            startActivity(intent)
                            activity?.finish()
                        }
                    }
                }
            }
        }
    }
}