package com.example.presentation.ui.analysis.datedetail

import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.model.analysis.AnswerAnalysisInfo
import com.example.presentation.databinding.ItemInterviewAnswerBinding

class DateDetailAnswerListAdapter :
    ListAdapter<AnswerAnalysisInfo, RecyclerView.ViewHolder>(diffUtil) {

    class DateDetailAnswerViewHolder(
        private val binding: ItemInterviewAnswerBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        @RequiresApi(Build.VERSION_CODES.O)
        fun bind(item: AnswerAnalysisInfo) {
            binding.answerAnalysisInfo = item

            binding.layoutParent.setOnClickListener {
                if (binding.layoutChild.visibility == View.VISIBLE) {
                    binding.layoutChild.visibility = View.GONE
                    binding.btnDown.visibility = View.VISIBLE
                } else {
                    binding.layoutChild.visibility = View.VISIBLE
                    binding.btnDown.visibility = View.GONE
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return DateDetailAnswerViewHolder(
            ItemInterviewAnswerBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as DateDetailAnswerViewHolder).bind(getItem(position) ?: return)
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<AnswerAnalysisInfo>() {
            override fun areItemsTheSame(
                oldItem: AnswerAnalysisInfo,
                newItem: AnswerAnalysisInfo
            ): Boolean {
                return oldItem.question == newItem.question
            }

            override fun areContentsTheSame(
                oldItem: AnswerAnalysisInfo,
                newItem: AnswerAnalysisInfo
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}

