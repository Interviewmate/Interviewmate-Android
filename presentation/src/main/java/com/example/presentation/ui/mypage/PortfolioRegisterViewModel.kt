package com.example.presentation.ui.mypage

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PortfolioRegisterViewModel @Inject constructor(

) : ViewModel() {
    lateinit var portfolioUri: String
}