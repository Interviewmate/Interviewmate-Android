package com.example.presentation.ui.analysis

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.domain.model.analysis.DayInterviewInfo
import com.example.presentation.R
import com.example.presentation.databinding.FragmentDateAnalysisBinding
import com.example.presentation.model.analysis.Date
import com.example.presentation.model.analysis.InterviewInfo
import com.example.presentation.ui.MainViewModel
import com.google.android.material.snackbar.Snackbar
import com.kizitonwose.calendar.core.daysOfWeek
import com.kizitonwose.calendar.core.nextMonth
import com.kizitonwose.calendar.core.previousMonth
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.YearMonth
import java.util.*

@AndroidEntryPoint
class DateAnalysisFragment : Fragment(), OnClickDateListener, OnClickInterviewListener {
    private var _binding: FragmentDateAnalysisBinding? = null
    private val binding: FragmentDateAnalysisBinding
        get() = _binding!!

    private val dateAnalysisViewModel: DateAnalysisViewModel by viewModels()
    private val mainViewModel: MainViewModel by activityViewModels()

    @RequiresApi(Build.VERSION_CODES.O)
    private val currentMonth = YearMonth.now()

    @RequiresApi(Build.VERSION_CODES.O)
    private val startMonth = currentMonth.minusMonths(100)

    @RequiresApi(Build.VERSION_CODES.O)
    private val endMonth = currentMonth.plusMonths(100)

    @RequiresApi(Build.VERSION_CODES.O)
    private val firstDayOfWeek = daysOfWeek(firstDayOfWeek = DayOfWeek.SUNDAY)

    private val dateAnalysisListAdapter = DateAnalysisListAdapter(this@DateAnalysisFragment)

    private lateinit var interviewInfo: InterviewInfo

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
        getMonthInterviews(currentMonth)
        setCalendar()
        setRecyclerView()
        setClickMonthBtn()
        checkAnalysisOver()
    }

    private fun initBinding() {
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = dateAnalysisViewModel
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun setCalendar() {
        viewLifecycleOwner.lifecycleScope.launch {
            dateAnalysisViewModel.isMonthInterviewsSuccess.collectLatest {  isMonthInterviewsSuccess ->
                if (isMonthInterviewsSuccess) {
                    with(binding.calendarView) {
                        dayBinder = CalendarDayBinder(this, dateAnalysisViewModel.monthInterviews, this@DateAnalysisFragment)
                        monthScrollListener = { calendarMonth ->
                            onMonthScrolled(calendarMonth.yearMonth)
                        }
                        setup(startMonth, endMonth, firstDayOfWeek.first())
                        scrollToMonth(currentMonth)
                    }

                    viewLifecycleOwner.lifecycleScope.launch {
                        dateAnalysisViewModel.getDayInterviews(
                            mainViewModel.userAuth,
                            LocalDate.now().toString()
                        )
                    }
                }
            }

        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun makeFullDateList(
        monthInterviews: List<Int>,
        visibleMonth: YearMonth
    ): List<List<Int>> {
        return monthInterviews.map { listOf(visibleMonth.year, visibleMonth.monthValue, it) }
    }

    private fun setRecyclerView() {
        binding.recyclerView.adapter = dateAnalysisListAdapter

        viewLifecycleOwner.lifecycleScope.launch {
            dateAnalysisViewModel.isDayInterviewsSuccess.collectLatest { isDayInterviewsSuccess ->
                if (isDayInterviewsSuccess) {
                    dateAnalysisListAdapter.submitList(
                        dateAnalysisViewModel.dayInterviews
                    )
                }
            }
        }
    }

    private fun setClickMonthBtn() {
        binding.btnMonthNext.setOnClickListener {
            val visibleMonth =
                binding.calendarView.findFirstVisibleMonth() ?: return@setOnClickListener
            binding.calendarView.smoothScrollToMonth(visibleMonth.yearMonth.nextMonth)
        }

        binding.btnMonthPrev.setOnClickListener {
            val visibleMonth =
                binding.calendarView.findFirstVisibleMonth() ?: return@setOnClickListener
            binding.calendarView.smoothScrollToMonth(visibleMonth.yearMonth.previousMonth)
        }
    }

    private fun getMonthInterviews(currentMonth: YearMonth) {
        viewLifecycleOwner.lifecycleScope.launch {
            dateAnalysisViewModel.getMonthInterviews(
                mainViewModel.userAuth,
                currentMonth.toString()
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

        //getMonthInterviews(currentMonth)
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onClickDate(date: Date) {
        viewLifecycleOwner.lifecycleScope.launch {
            dateAnalysisViewModel.clickedDay.emit(date)
            dateAnalysisViewModel.getDayInterviews(mainViewModel.userAuth, getDateForm(date))
        }
    }

    private fun getDateForm(date: Date): String =
        "${"%04d".format(date.year)}-${"%02d".format(date.month)}-${"%02d".format(date.day)}"

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onClickInterview(dayInterviewInfo: DayInterviewInfo) {
        interviewInfo = InterviewInfo(
            dateAnalysisViewModel.clickedDay.value.month,
            dateAnalysisViewModel.clickedDay.value.day,
            dateAnalysisViewModel.clickedDay.value.dayOfWeek,
            dayInterviewInfo.num,
            dayInterviewInfo.interviewId
        )

        viewLifecycleOwner.lifecycleScope.launch {
            dateAnalysisViewModel.getCheckAnalysisOver(
                mainViewModel.userAuth,
                dayInterviewInfo.interviewId
            )
        }
    }

    private fun checkAnalysisOver() {
        viewLifecycleOwner.lifecycleScope.launch {
            dateAnalysisViewModel.isAnalysisOver.collectLatest { isAnalysisOver ->
                if (isAnalysisOver) {
                    val action =
                        AnalysisFragmentDirections.actionAnalysisFragmentToDateDetailFragment(
                            interviewInfo
                        )
                    findNavController().navigate(action)
                } else {
                    Snackbar.make(
                        binding.root,
                        R.string.analysis_not_yet,
                        Snackbar.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

}