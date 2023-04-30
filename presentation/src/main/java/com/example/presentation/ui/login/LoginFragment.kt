package com.example.presentation.ui.login

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.presentation.MainActivity
import com.example.presentation.R
import com.example.presentation.databinding.FragmentLoginBinding
import com.example.presentation.ui.signup.SignUpViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding: FragmentLoginBinding
        get() = _binding!!

    private val signUpViewModel: SignUpViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setLogin()
        setSignUp()

    }

    private fun setLogin() {
        val intent = Intent(activity, MainActivity::class.java)

        binding.btnLogin.setOnClickListener {
            lifecycleScope.launch {
                signUpViewModel.setLogin(
                    binding.etEmail.text.toString(),
                    binding.etPassword.text.toString()
                )
                signUpViewModel.isSuccessLogin.collect {
                    when (it.first) {
                        false -> {
                            Snackbar.make(
                                binding.root,
                                it.second,
                                Snackbar.LENGTH_SHORT
                            ).show()
                        }
                        else -> {
                            startActivity(intent)
                            activity?.finish()
                        }
                    }
                }
            }
        }
    }

    private fun setSignUp() {
        binding.tvSignUp.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_signUpFragment)
        }
    }

}