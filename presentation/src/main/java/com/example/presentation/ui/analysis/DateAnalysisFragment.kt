package com.example.presentation.ui.analysis

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.example.presentation.databinding.FragmentDateAnalysisBinding
import com.kizitonwose.calendar.core.daysOfWeek
import java.time.DayOfWeek
import java.time.YearMonth
import java.util.*

class DateAnalysisFragment : Fragment() {
    private var _binding: FragmentDateAnalysisBinding? = null
    private val binding: FragmentDateAnalysisBinding
        get() = _binding!!

    @RequiresApi(Build.VERSION_CODES.O)
    private val currentMonth = YearMonth.now()

    @RequiresApi(Build.VERSION_CODES.O)
    private val startMonth = currentMonth.minusMonths(100)

    @RequiresApi(Build.VERSION_CODES.O)
    private val endMonth = currentMonth.plusMonths(100)

    @RequiresApi(Build.VERSION_CODES.O)
    private val firstDayOfWeek = daysOfWeek(firstDayOfWeek = DayOfWeek.SUNDAY)
    private val interviewedDays = listOf(listOf(2023, 5, 1), listOf(2023, 5, 10))

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

        with(binding.calendarView) {
            dayBinder = CalendarDayBinder(this, interviewedDays)
            monthScrollListener = { calendarMonth ->
                onMonthScrolled(calendarMonth.yearMonth)
            }
            setup(startMonth, endMonth, firstDayOfWeek.first())
            scrollToMonth(currentMonth)
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

}