package com.example.presentation.ui.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.presentation.model.jobskill.Developer
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor() : ViewModel() {
    lateinit var email: String
    lateinit var password: String
    lateinit var nickName: String
    lateinit var job: Developer
    val keyword = mutableListOf<String>()

    private val _selectJobEvent = MutableSharedFlow<Developer>()
    val selectJobEvent = _selectJobEvent.asSharedFlow()

    fun changeChip(developer: Developer) {
        this.job = developer

        viewModelScope.launch {
            _selectJobEvent.emit(developer)
        }
    }
}