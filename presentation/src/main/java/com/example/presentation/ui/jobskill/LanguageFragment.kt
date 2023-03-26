package com.example.presentation.ui.jobskill

import android.content.Intent
import android.content.res.ColorStateList
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.presentation.MainActivity
import com.example.presentation.R
import com.example.presentation.databinding.FragmentJobSkillBinding
import com.google.android.material.chip.Chip

class LanguageFragment : Fragment() {
    private var _binding: FragmentJobSkillBinding? = null
    private val binding: FragmentJobSkillBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentJobSkillBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val intent = Intent(activity, MainActivity::class.java)

        binding.btnNext.apply{
            text = getString(R.string.finish)
            setOnClickListener {
                startActivity(intent)
                activity?.finish()
            }
        }

        addChip("Server")
        addChip("Client")
    }

    fun addChip(skillName: String) {
        binding.chipGroup.addView(Chip(context).apply {
            text = skillName
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