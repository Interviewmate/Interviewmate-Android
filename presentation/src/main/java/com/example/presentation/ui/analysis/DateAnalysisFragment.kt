package com.example.presentation.ui.analysis

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.presentation.databinding.FragmentDateAnalysisBinding
import com.example.presentation.model.analysis.Date
import com.example.presentation.model.analysis.DateAnalysis
import com.kizitonwose.calendar.core.daysOfWeek
import kotlinx.coroutines.launch
import java.time.DayOfWeek
import java.time.YearMonth
import java.util.*

class DateAnalysisFragment : Fragment(), ClickDateListener {
    private var _binding: FragmentDateAnalysisBinding? = null
    private val binding: FragmentDateAnalysisBinding
        get() = _binding!!

    private val dateAnalysisViewModel: DateAnalysisViewModel by viewModels()

    @RequiresApi(Build.VERSION_CODES.O)
    private val currentMonth = YearMonth.now()

    @RequiresApi(Build.VERSION_CODES.O)
    private val startMonth = currentMonth.minusMonths(100)

    @RequiresApi(Build.VERSION_CODES.O)
    private val endMonth = currentMonth.plusMonths(100)

    @RequiresApi(Build.VERSION_CODES.O)
    private val firstDayOfWeek = daysOfWeek(firstDayOfWeek = DayOfWeek.SUNDAY)
    private val interviewedDays = listOf(listOf(2023, 5, 1), listOf(2023, 5, 10))

    private val dateAnalysisListAdapter = DateAnalysisListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDateAnalysisBinding.inflate(layoutInflater)

        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initBinding()

        with(binding.calendarView) {
            dayBinder = CalendarDayBinder(this, interviewedDays, this@DateAnalysisFragment)
            monthScrollListener = { calendarMonth ->
                onMonthScrolled(calendarMonth.yearMonth)
            }
            setup(startMonth, endMonth, firstDayOfWeek.first())
            scrollToMonth(currentMonth)
        }

        setRecyclerView()
    }

    private fun initBinding() {
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = dateAnalysisViewModel
    }

    private fun setRecyclerView() {
        binding.recyclerView.adapter = dateAnalysisListAdapter

        viewLifecycleOwner.lifecycleScope.launch {
            dateAnalysisListAdapter.submitList(
                listOf(
                    DateAnalysis(1, "09시20분", "1회차"),
                    DateAnalysis(2, "10시30분", "2회차"),
                )
            )
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun onMonthScrolled(currentMonth: YearMonth) {
        val visibleMonth = binding.calendarView.findFirstVisibleMonth() ?: return
        binding.tvMonthIndicator.text = visibleMonth.yearMonth.month.toString()
        if (currentMonth != visibleMonth.yearMonth) {
            binding.calendarView.smoothScrollToMonth(currentMonth)
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCallback(date: Date) {
        viewLifecycleOwner.lifecycleScope.launch {
            dateAnalysisViewModel.clickedDay.emit(date)
        }
    }

}