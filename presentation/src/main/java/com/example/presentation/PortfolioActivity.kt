package com.example.presentation

import android.content.Intent
import android.graphics.Paint
import android.os.Build
import android.os.Bundle
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.domain.model.signup.UserAuth
import com.example.presentation.databinding.ActivityPortfolioBinding
import com.example.presentation.ui.mypage.PortfolioRegisterViewModel
import com.example.presentation.ui.mypage.SafManager
import com.example.presentation.ui.mypage.SafManager.REQUEST_CODE_OPEN_SAF
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PortfolioActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPortfolioBinding

    private val portfolioRegisterViewModel: PortfolioRegisterViewModel by viewModels()

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPortfolioBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUserAuth()
        setUnderLine()
        getPortfolioFile()
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    private fun setUserAuth() {
        portfolioRegisterViewModel.userAuth =
            intent.getSerializableExtra("userAuth", UserAuth::class.java) ?: UserAuth(-1, "")
    }

    private fun setUnderLine() {
        binding.tvUserPortfolioTitle.paintFlags = Paint.UNDERLINE_TEXT_FLAG
    }

    private fun getPortfolioFile() {
        binding.btnNext.setOnClickListener {
            SafManager.openFileSAF(this, "*/*")
            lifecycleScope.launch {
                portfolioRegisterViewModel.setS3PreSigned()
            }
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == RESULT_OK) {
            when (requestCode) {
                REQUEST_CODE_OPEN_SAF -> {
                    val uri = data?.data
                    uri?.let {
                        val file = SafManager.getFileFromSAF(this, uri)
                        portfolioRegisterViewModel.portfolioUri = uri.toString()
                        binding.tvUserPortfolioTitle.text = uri.toString()
                        lifecycleScope.launch {
                            portfolioRegisterViewModel.putPortfolio(file)
                        }
                    }
                }
            }
        }
    }
}