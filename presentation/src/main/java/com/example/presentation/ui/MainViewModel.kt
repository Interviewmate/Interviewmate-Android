package com.example.presentation.ui

import androidx.lifecycle.ViewModel
import com.example.domain.model.signup.UserAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {
    var userAuth: UserAuth = UserAuth(2, "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ7XCJlbWFpbFwiOlwibW9hLm1vYS5pbnRlcnZpZXdAZ21haWwuY29tXCIsXCJ0eXBlXCI6XCJBQ0NFU1NfVE9LRU5cIn0iLCJyb2xlcyI6W3sibmFtZSI6IlJPTEVfVVNFUiJ9XSwiaWF0IjoxNjg0Njc5NTAxLCJleHAiOjE2ODQ2ODEzMDF9.eC5q3rJ_dc-XIulCIXoP1saOvMXuOoRcyzLLqOOKFpc")
}