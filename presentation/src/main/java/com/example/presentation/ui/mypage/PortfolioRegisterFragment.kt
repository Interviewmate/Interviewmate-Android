package com.example.presentation.ui.mypage

import android.graphics.Paint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.presentation.databinding.FragmentPortfolioRegisterBinding

class PortfolioRegisterFragment : Fragment() {
    private var _binding: FragmentPortfolioRegisterBinding? = null
    private val binding: FragmentPortfolioRegisterBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPortfolioRegisterBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUnderLine()
    }

    private fun setUnderLine() {
        binding.tvUserPortfolioTitle.paintFlags = Paint.UNDERLINE_TEXT_FLAG
    }
}