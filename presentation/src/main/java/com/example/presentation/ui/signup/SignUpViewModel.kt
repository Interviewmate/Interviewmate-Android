package com.example.presentation.ui.signup

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(): ViewModel() {
    lateinit var email: String
    lateinit var password: String
    lateinit var nickName: String
    lateinit var job: String
    val keyword = mutableListOf<String>()
}