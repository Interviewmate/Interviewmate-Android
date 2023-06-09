package com.example.presentation.ui.interview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.presentation.R
import com.example.presentation.databinding.FragmentInterviewBinding
import com.example.presentation.ui.MainViewModel
import com.example.presentation.ui.mypage.PortfolioRegisterViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class InterviewFragment : Fragment() {
    private var _binding: FragmentInterviewBinding? = null
    private val binding: FragmentInterviewBinding
        get() = _binding!!

    private val interviewViewModel: InterviewViewModel by viewModels()
    private val portfolioRegisterViewModel: PortfolioRegisterViewModel by viewModels()
    private val mainViewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentInterviewBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initBinding()
        setInterview()
        getPortfolioExist()
    }

    private fun initBinding() {
        binding.lifecycleOwner = viewLifecycleOwner
        binding.portfolioViewModel = portfolioRegisterViewModel
    }

    private fun setInterview() {
        binding.btnDoInterview.setOnClickListener {
            viewLifecycleOwner.lifecycleScope.launch {
                interviewViewModel.setInterview(mainViewModel.userAuth)
                interviewViewModel.isInterviewMadeSuccess.collect { isInterviewMade ->
                    if (isInterviewMade) {
                        val action = InterviewFragmentDirections.actionInterviewFragmentToKeywordFragment(interviewViewModel.interviewId)
                        findNavController().navigate(action)
                    } else {
                        Snackbar.make(
                            binding.root,
                            R.string.error_make_interview,
                            Snackbar.LENGTH_SHORT
                        ).show()
                    }
                }
            }

        }
    }

    private fun getPortfolioExist() {
        viewLifecycleOwner.lifecycleScope.launch {
            portfolioRegisterViewModel.getPortfolioExist(mainViewModel.userAuth)
        }
    }
}