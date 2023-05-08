package com.example.presentation.ui.interview

import android.hardware.Camera
import android.media.CamcorderProfile
import android.media.MediaRecorder
import android.os.Build
import android.os.Bundle
import android.view.*
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.presentation.R
import com.example.presentation.databinding.FragmentRecordBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class RecordFragment : Fragment(), SurfaceHolder.Callback {
    private var _binding: FragmentRecordBinding? = null
    private val binding: FragmentRecordBinding
        get() = _binding!!

    private val recordViewModel: RecordViewModel by viewModels()

    private val camera = Camera.open(CAMERA_FRONT)

    @RequiresApi(Build.VERSION_CODES.S)
    private lateinit var mediaRecorder: MediaRecorder
    private lateinit var surfaceHolder: SurfaceHolder
    private var isRecording = false
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRecordBinding.inflate(layoutInflater)

        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.S)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initBinding()

        viewLifecycleOwner.lifecycleScope.launch {
            recordViewModel.startTimer()
        }

        viewLifecycleOwner.lifecycleScope.launch {
            recordViewModel.isTimerVisible.collectLatest { isTimerVisible ->
                if (isTimerVisible.not()) {
                    setRecorder()
                } else {
                    if (isRecording) {
                        setOverRecorder()
                    }
                }
            }
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
        camera.setDisplayOrientation(90)
        surfaceHolder = binding.surfaceView.holder
        surfaceHolder.addCallback(this)
        surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS)
    }

    @RequiresApi(Build.VERSION_CODES.S)
    private fun setRecorder() {
        mediaRecorder = MediaRecorder()
        camera.unlock()
        mediaRecorder.setCamera(camera)
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.CAMCORDER)
        mediaRecorder.setVideoSource(MediaRecorder.VideoSource.CAMERA)
        mediaRecorder.setProfile(CamcorderProfile.get(CamcorderProfile.QUALITY_720P))
        mediaRecorder.setOrientationHint(90)
        mediaRecorder.setOutputFile(recordingFilePath)
        mediaRecorder.setPreviewDisplay(surfaceHolder.surface)
        mediaRecorder.prepare();
        mediaRecorder.start();
        isRecording = true
    }

    private val recordingFilePath: String by lazy {
        "${requireActivity().externalCacheDir?.absolutePath}/interview.mp4"
    }

    override fun surfaceCreated(p0: SurfaceHolder) {
    }

    override fun surfaceChanged(p0: SurfaceHolder, p1: Int, p2: Int, p3: Int) {
        if (surfaceHolder.surface == null) {
            return
        }

        try {
            camera.stopPreview()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun surfaceDestroyed(p0: SurfaceHolder) {
    }

    @RequiresApi(Build.VERSION_CODES.S)
    private fun setOverRecorder() {
        mediaRecorder.stop()
        mediaRecorder.release()
        camera.lock()
        isRecording = false
    }

    companion object {
        const val CAMERA_FRONT = 1
    }
}