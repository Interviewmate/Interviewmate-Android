package com.example.presentation.ui.interview

import android.hardware.Camera
import android.media.CamcorderProfile
import android.media.MediaRecorder
import android.os.Build
import android.os.Bundle
import android.view.*
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.presentation.R
import com.example.presentation.databinding.FragmentRecordBinding
import com.example.presentation.ui.MainViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


@AndroidEntryPoint
class RecordFragment : Fragment(), SurfaceHolder.Callback {
    private var _binding: FragmentRecordBinding? = null
    private val binding: FragmentRecordBinding
        get() = _binding!!

    private val recordViewModel: RecordViewModel by viewModels()
    private val interviewViewModel: InterviewViewModel by viewModels()
    private val mainViewModel: MainViewModel by activityViewModels()

    private val camera = Camera.open(CAMERA_FRONT)

    @RequiresApi(Build.VERSION_CODES.S)
    private var mediaRecorder: MediaRecorder? = null
    private lateinit var surfaceHolder: SurfaceHolder
    private var isRecording = false

    private val args: NoticeFragmentArgs by navArgs()

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
        setLoadingImg()
        setTimer()
        setInterviewOver()
        clickNextButton()
    }

    private fun initBinding() {
        binding.lifecycleOwner = viewLifecycleOwner
        binding.recordViewModel = recordViewModel

        camera.setDisplayOrientation(90)
        surfaceHolder = binding.surfaceView.holder
        surfaceHolder.addCallback(this)
        surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS)

        recordViewModel.videoPath = recordingFilePath

        recordViewModel.questions = args.questions.toList()
    }

    private fun setLoadingImg() {
        Glide.with(this).load(R.raw.loading).into(binding.ivLoading)
    }

    @RequiresApi(Build.VERSION_CODES.S)
    private fun setTimer() {
        viewLifecycleOwner.lifecycleScope.launch {
            recordViewModel.startTimer(mainViewModel.userAuth)
            recordViewModel.isPreSignedSuccess.collectLatest { isPreSignedSuccess ->
                if (isPreSignedSuccess.not()) {
                    Snackbar.make(
                        binding.root,
                        R.string.error_make_pre_signed,
                        Snackbar.LENGTH_SHORT
                    ).show()
                }
            }
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
    }

    private fun setInterviewOver() {
        viewLifecycleOwner.lifecycleScope.launch {
            recordViewModel.canOver.collectLatest { canOver ->
                if (canOver) {
                    findNavController().navigate(R.id.action_recordFragment_to_interviewOverFragment)
                }
            }
        }
    }

    private fun clickNextButton() {
        binding.btnNext.setOnClickListener {
            viewLifecycleOwner.lifecycleScope.launch {
                recordViewModel.reset(mainViewModel.userAuth)
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.S)
    private fun setRecorder() {
        mediaRecorder = MediaRecorder()
        camera.unlock()
        mediaRecorder!!.setCamera(camera)
        mediaRecorder!!.setAudioSource(MediaRecorder.AudioSource.CAMCORDER)
        mediaRecorder!!.setVideoSource(MediaRecorder.VideoSource.CAMERA)
        mediaRecorder!!.setProfile(CamcorderProfile.get(CamcorderProfile.QUALITY_720P))
        mediaRecorder!!.setOrientationHint(90)
        mediaRecorder!!.setOutputFile(recordingFilePath)
        mediaRecorder!!.setPreviewDisplay(surfaceHolder.surface)
        mediaRecorder!!.prepare()
        mediaRecorder!!.start()
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
        mediaRecorder!!.stop()
        mediaRecorder!!.release()
        mediaRecorder = null
        camera.lock()
        isRecording = false
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    companion object {
        const val CAMERA_FRONT = 1
    }
}