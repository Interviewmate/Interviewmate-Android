package com.example.presentation

import android.content.Intent
import android.graphics.Paint
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.presentation.databinding.ActivityPortfolioBinding
import com.example.presentation.ui.mypage.PortfolioRegisterViewModel
import com.example.presentation.ui.mypage.SafManager
import com.example.presentation.ui.mypage.SafManager.REQUEST_CODE_OPEN_SAF

class PortfolioActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPortfolioBinding

    private val portfolioRegisterViewModel: PortfolioRegisterViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPortfolioBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUnderLine()
        getPortfolioFile()
    }

    private fun setUnderLine() {
        binding.tvUserPortfolioTitle.paintFlags = Paint.UNDERLINE_TEXT_FLAG
    }

    private fun getPortfolioFile() {
        binding.btnNext.setOnClickListener {
            SafManager.openFileSAF(this, "*/*")
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
                    }
                }
            }
        }
    }
}