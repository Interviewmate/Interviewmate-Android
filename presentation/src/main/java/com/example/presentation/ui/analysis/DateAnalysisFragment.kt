package com.example.presentation.ui.analysis

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.presentation.databinding.FragmentDateAnalysisBinding
import com.example.presentation.model.analysis.Date
import com.example.presentation.model.analysis.DateAnalysis
import com.example.presentation.model.analysis.InterviewInfo
import com.kizitonwose.calendar.core.daysOfWeek
import com.kizitonwose.calendar.core.nextMonth
import com.kizitonwose.calendar.core.previousMonth
import kotlinx.coroutines.launch
import java.time.DayOfWeek
import java.time.YearMonth
import java.util.*

class DateAnalysisFragment : Fragment(), OnClickDateListener, OnClickInterviewListener {
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

    private val dateAnalysisListAdapter = DateAnalysisListAdapter(this@DateAnalysisFragment)

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
        setCalendar()
        setRecyclerView()
        setClickMonthBtn()
    }

    private fun initBinding() {
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = dateAnalysisViewModel
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun setCalendar() {
        with(binding.calendarView) {
            dayBinder = CalendarDayBinder(this, interviewedDays, this@DateAnalysisFragment)
            monthScrollListener = { calendarMonth ->
                onMonthScrolled(calendarMonth.yearMonth)
            }
            setup(startMonth, endMonth, firstDayOfWeek.first())
            scrollToMonth(currentMonth)
        }

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

    private fun setClickMonthBtn() {
        binding.btnMonthNext.setOnClickListener {
            val visibleMonth = binding.calendarView.findFirstVisibleMonth() ?: return@setOnClickListener
            binding.calendarView.smoothScrollToMonth(visibleMonth.yearMonth.nextMonth)
        }

        binding.btnMonthPrev.setOnClickListener {
            val visibleMonth = binding.calendarView.findFirstVisibleMonth() ?: return@setOnClickListener
            binding.calendarView.smoothScrollToMonth(visibleMonth.yearMonth.previousMonth)
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
    override fun onClickDate(date: Date) {
        viewLifecycleOwner.lifecycleScope.launch {
            dateAnalysisViewModel.clickedDay.emit(date)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onClickInterview(dateAnalysis: DateAnalysis) {
        Toast.makeText(requireContext(), dateAnalysis.toString(), Toast.LENGTH_SHORT).show()
        val interviewInfo = InterviewInfo(
            dateAnalysisViewModel.clickedDay.value.month,
            dateAnalysisViewModel.clickedDay.value.day,
            dateAnalysisViewModel.clickedDay.value.dayOfWeek,
            dateAnalysis.number
        )
        val action =
            AnalysisFragmentDirections.actionAnalysisFragmentToDateDetailFragment(interviewInfo)
        findNavController().navigate(action)
        viewLifecycleOwner.lifecycleScope.launch {
            //api 통신으로 분석 끝났는지 check
        }
    }

}