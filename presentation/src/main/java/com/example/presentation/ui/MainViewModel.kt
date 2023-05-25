package com.example.presentation.ui

import androidx.lifecycle.ViewModel
import com.example.domain.model.signup.UserAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {
    lateinit var userAuth: UserAuth
}