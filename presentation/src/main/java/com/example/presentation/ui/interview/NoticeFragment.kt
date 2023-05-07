package com.example.presentation.ui.interview

import android.Manifest
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.presentation.R
import com.example.presentation.databinding.FragmentNoticeBinding
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.normal.TedPermission


class NoticeFragment : Fragment() {
    private var _binding: FragmentNoticeBinding? = null
    private val binding: FragmentNoticeBinding
        get() = _binding!!

    private var isPermission = false
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNoticeBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        TedPermission.create()
            .setPermissionListener(permissionlistener)
            .setRationaleMessage("녹화를 위하여 권한을 허용해주세요.")
            .setDeniedMessage("권한이 거부되었습니다. 설정 > 권한에서 허용해주세요.")
            .setPermissions(Manifest.permission.CAMERA, Manifest.permission.RECORD_AUDIO)
            .check();

        binding.btnNext.setOnClickListener {
            if (isPermission) {
                findNavController().navigate(R.id.action_noticeFragment_to_recordFragment)
            }
        }
    }

    var permissionlistener: PermissionListener = object : PermissionListener {
        override fun onPermissionGranted() {
            Toast.makeText(context, "권한 허가", Toast.LENGTH_SHORT).show()
            isPermission = true
        }

        override fun onPermissionDenied(deniedPermissions: List<String>) {
            Toast.makeText(context, "권한 거부", Toast.LENGTH_SHORT).show()
            isPermission = false
        }
    }
}