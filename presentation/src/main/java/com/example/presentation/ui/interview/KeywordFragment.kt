package com.example.presentation.ui.interview

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.presentation.databinding.FragmentJobSkillBinding

class KeywordFragment : Fragment() {
    private var _binding: FragmentJobSkillBinding? = null
    private val binding: FragmentJobSkillBinding
        get() = _binding!!

    private lateinit var viewModel: KeywordViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentJobSkillBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(KeywordViewModel::class.java)
        // TODO: Use the ViewModel
    }

}