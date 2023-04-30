package com.example.presentation.ui.signup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.presentation.R
import com.example.presentation.databinding.FragmentSignUpBinding
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch

class SignUpFragment : Fragment() {

    private var _binding: FragmentSignUpBinding? = null
    private val binding: FragmentSignUpBinding
        get() = _binding!!

    private val signUpViewModel: SignUpViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSignUpBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initBinding()
        checkNicknameDuplication()
        sendEmail()
        confirmCode()
        setSignUp()
    }

    private fun initBinding() {
        binding.lifecycleOwner = viewLifecycleOwner
        binding.signUpViewModel = signUpViewModel
    }

    private fun checkNicknameDuplication() {
        binding.btnCheckDuplication.setOnClickListener {
            viewLifecycleOwner.lifecycleScope.launch {
                signUpViewModel.checkNicknameDuplication(binding.etNickname.text.toString())
            }
        }
    }

    private fun sendEmail() {
        binding.btnCertifyEmail.setOnClickListener {
            viewLifecycleOwner.lifecycleScope.launch {
                signUpViewModel.sendEmail(binding.etEmail.text.toString())
            }
        }
    }

    private fun confirmCode() {
        binding.btnConfirm.setOnClickListener {
            viewLifecycleOwner.lifecycleScope.launch {
                signUpViewModel.authenticateCode(
                    binding.etEmail.text.toString(),
                    binding.etCertificationNumber.text.toString()
                )
            }
        }
    }

    private fun setSignUp() {
        binding.btnNext.setOnClickListener {
            if (signUpViewModel.isEmailSending.value.not() || signUpViewModel.isCodeAuth.value.not()) {
                Snackbar.make(
                    binding.root,
                    R.string.notice_send_email,
                    Snackbar.LENGTH_SHORT
                ).show()
            } else if (binding.etNickname.text.isEmpty() || signUpViewModel.isNicknameDuplication.value.not()) {
                Snackbar.make(
                    binding.root,
                    R.string.notice_fill_nickname,
                    Snackbar.LENGTH_SHORT
                ).show()
            } else if (binding.etEmail.text.isEmpty()) {
                Snackbar.make(
                    binding.root,
                    R.string.notice_fill_emil,
                    Snackbar.LENGTH_SHORT
                ).show()
            } else if (binding.etPassword.text.isEmpty()) {
                Snackbar.make(
                    binding.root,
                    R.string.notice_fill_password,
                    Snackbar.LENGTH_SHORT
                ).show()
            } else {
                signUpViewModel.nickname = binding.etNickname.text.toString()
                signUpViewModel.email = binding.etEmail.text.toString()
                signUpViewModel.password = binding.etPassword.text.toString()
                findNavController().navigate(R.id.action_signUpFragment_to_jobFragment)
            }
        }
    }

}