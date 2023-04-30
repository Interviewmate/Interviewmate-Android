package com.example.presentation.ui.interview

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class KeywordViewModel @Inject constructor() : ViewModel() {
    val keywords = mutableListOf<String>()
}