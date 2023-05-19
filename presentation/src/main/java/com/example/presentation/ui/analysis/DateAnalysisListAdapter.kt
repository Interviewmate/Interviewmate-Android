package com.example.presentation.ui.analysis

import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.presentation.databinding.ItemInterviewListBinding
import com.example.presentation.model.analysis.DateAnalysis

class DateAnalysisListAdapter(
    private val listener: OnClickInterviewListener
) : ListAdapter<DateAnalysis, RecyclerView.ViewHolder>(diffUtil) {

    inner class DateAnalysisViewHolder(
        private val binding: ItemInterviewListBinding
    ) :
        RecyclerView.ViewHolder(binding.root) {
        @RequiresApi(Build.VERSION_CODES.O)
        fun bind(item: DateAnalysis) {
            binding.dateAnalysis = item
            binding.btnAnalysisResult.setOnClickListener {
                listener.onClickInterview(getItem(layoutPosition))
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return DateAnalysisViewHolder(
            ItemInterviewListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as DateAnalysisViewHolder).bind(getItem(position) ?: return)
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<DateAnalysis>() {
            override fun areItemsTheSame(
                oldItem: DateAnalysis,
                newItem: DateAnalysis
            ): Boolean {
                return oldItem.time == newItem.time
            }

            override fun areContentsTheSame(
                oldItem: DateAnalysis,
                newItem: DateAnalysis
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}

