package com.example.presentation.jobskill

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.presentation.databinding.FragmentJobSkillBinding

class JobSkillFragment : Fragment() {
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
}