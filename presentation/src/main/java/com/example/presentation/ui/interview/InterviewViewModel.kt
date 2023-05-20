package com.example.presentation.ui.interview

import androidx.lifecycle.ViewModel
import com.example.domain.model.UserAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class InterviewViewModel @Inject constructor() : ViewModel() {
    lateinit var userAuth: UserAuth
}