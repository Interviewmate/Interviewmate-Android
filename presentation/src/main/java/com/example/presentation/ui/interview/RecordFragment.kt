package com.example.presentation.ui.interview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.presentation.databinding.FragmentRecordBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class RecordFragment : Fragment() {
    private var _binding: FragmentRecordBinding? = null
    private val binding: FragmentRecordBinding
        get() = _binding!!

    private val recordViewModel: RecordViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRecordBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launch {
            recordViewModel.start()
            recordViewModel.timer.collectLatest {
                binding.tvTimer.text = it.toString()
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            recordViewModel.isTimerVisible.collect { isVisible ->
                if (isVisible) {
                    binding.layoutTimer.visibility = View.VISIBLE
                } else {
                    binding.layoutTimer.visibility = View.GONE
                }
            }
        }

        binding.btnNext.setOnClickListener {
            viewLifecycleOwner.lifecycleScope.launch {
                recordViewModel.reset()
                recordViewModel.timer.collectLatest {
                    binding.tvTimer.text = it.toString()
                }
            }
        }
    }
    
}