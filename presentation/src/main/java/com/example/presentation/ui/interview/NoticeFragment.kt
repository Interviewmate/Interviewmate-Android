package com.example.presentation.ui.interview

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.presentation.databinding.FragmentNoticeBinding

class NoticeFragment : Fragment() {
    private var _binding: FragmentNoticeBinding? = null
    private val binding: FragmentNoticeBinding
        get() = _binding!!

    private var isPermission = false

    private val args: NoticeFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNoticeBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        checkPermission()

        binding.btnNext.setOnClickListener {
            if (isPermission) {
                val action =
                    NoticeFragmentDirections.actionNoticeFragmentToRecordFragment(args.questions, args.interviewId)
                findNavController().navigate(action)
            }
        }
    }

    private fun checkPermission() {
        val permission = mutableMapOf<String, String>()
        permission["camera"] = Manifest.permission.CAMERA
        permission["record_audio"] = Manifest.permission.RECORD_AUDIO
        permission["storageWrite"] = Manifest.permission.WRITE_EXTERNAL_STORAGE

        val denied = permission.count {
            ContextCompat.checkSelfPermission(
                requireContext(),
                it.value
            ) == PackageManager.PERMISSION_DENIED
        }

        if (denied > 0 && Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) {
            requestPermissions(permission.values.toTypedArray(), REQUEST_CODE)
        } else {
            isPermission = true
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_CODE) {
            grantResults.forEach {
                if (it == PackageManager.PERMISSION_DENIED) {
                    Toast.makeText(context, "서비스의 필요한 권한입니다.\n권한에 동의해주세요.", Toast.LENGTH_SHORT)
                        .show()
                    isPermission = false
                }
            }
        }
    }

    companion object {
        const val REQUEST_CODE = 1
    }
}