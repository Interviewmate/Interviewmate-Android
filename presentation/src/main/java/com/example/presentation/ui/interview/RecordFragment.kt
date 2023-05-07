package com.example.presentation.ui.interview

import android.hardware.Camera
import android.media.MediaRecorder
import android.os.Bundle
import android.view.*
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.presentation.R
import com.example.presentation.databinding.FragmentRecordBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class RecordFragment : Fragment() {
    private var _binding: FragmentRecordBinding? = null
    private val binding: FragmentRecordBinding
        get() = _binding!!

    private val recordViewModel: RecordViewModel by viewModels()

    private val camera: Camera? = null
    private val mediaRecorder: MediaRecorder? = null
    private val btn_record: Button? = null
    private val surfaceView: SurfaceView? = null
    private val surfaceHolder: SurfaceHolder? = null
    private val recording = false
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRecordBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initBinding()

        viewLifecycleOwner.lifecycleScope.launch {
            recordViewModel.startTimer()
        }

        viewLifecycleOwner.lifecycleScope.launch {
            recordViewModel.isOver.collectLatest { isOver ->
                if (isOver) {
                    findNavController().navigate(R.id.action_recordFragment_to_interviewOverFragment)
                }
            }
        }

        binding.btnNext.setOnClickListener {
            viewLifecycleOwner.lifecycleScope.launch {
                recordViewModel.reset()
            }
        }
    }

    private fun initBinding() {
        binding.lifecycleOwner = viewLifecycleOwner
        binding.recordViewModel = recordViewModel
    }

}