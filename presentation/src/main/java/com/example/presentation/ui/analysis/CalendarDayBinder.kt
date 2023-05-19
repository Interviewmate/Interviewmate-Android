package com.example.presentation.ui.analysis

import android.os.Build
import android.view.View
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import com.example.presentation.R
import com.example.presentation.databinding.ItemCalendarDayBinding
import com.example.presentation.model.analysis.Date
import com.kizitonwose.calendar.core.CalendarDay
import com.kizitonwose.calendar.core.DayPosition
import com.kizitonwose.calendar.view.CalendarView
import com.kizitonwose.calendar.view.MonthDayBinder
import com.kizitonwose.calendar.view.ViewContainer
import java.time.LocalDate
import java.time.format.TextStyle
import java.util.*

class CalendarDayBinder(
    private val calendarView: CalendarView,
    private val interviewedDays: List<List<Int>>,
    private val clickDateListener: ClickDateListener
) : MonthDayBinder<CalendarDayBinder.DayViewContainer> {

    private lateinit var selectedDate: ItemCalendarDayBinding

    @RequiresApi(Build.VERSION_CODES.O)
    inner class DayViewContainer(
        val binding: ItemCalendarDayBinding
    ) : ViewContainer(binding.root) {

        lateinit var day: CalendarDay

        init {
            binding.tvDay.setOnClickListener {
                if (day.position == DayPosition.MonthDate) {
                    if (selectedDate == binding) {
                        return@setOnClickListener
                    }
                    selectedDate.root.background = null
                    selectedDate.tvDay.setTextColor(
                        ContextCompat.getColor(
                            calendarView.context,
                            R.color.black
                        )
                    )

                    setDeepBlue(binding)

                    selectedDate = binding

                    clickDateListener.onCallback(
                        Date(
                            day.date.year,
                            day.date.monthValue,
                            day.date.dayOfMonth,
                            day.date.dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.KOREAN)
                        )
                    )
                }
            }
        }
    }

    private fun setDeepBlue(binding: ItemCalendarDayBinding) {
        binding.root.background =
            ContextCompat.getDrawable(
                calendarView.context,
                R.drawable.shape_click_day
            )
        binding.tvDay.setTextColor(
            ContextCompat.getColor(
                calendarView.context,
                R.color.white
            )
        )
    }


    @RequiresApi(Build.VERSION_CODES.O)
    override fun create(view: View) = DayViewContainer(ItemCalendarDayBinding.bind(view))

    @RequiresApi(Build.VERSION_CODES.O)
    override fun bind(container: DayViewContainer, data: CalendarDay) {
        container.binding.tvDay.text = data.date.dayOfMonth.toString()
        container.day = data

        if (data.position == DayPosition.MonthDate) {
            container.binding.tvDay.setTextColor(
                ContextCompat.getColor(
                    calendarView.context,
                    R.color.black
                )
            )
        } else {
            container.binding.tvDay.setTextColor(
                ContextCompat.getColor(
                    calendarView.context,
                    R.color.medium_gray
                )
            )
        }

        interviewedDays.forEach { date ->
            val (year, month, day) = date

            if (year == data.date.year && month == data.date.monthValue && day == data.date.dayOfMonth) {
                container.binding.root.background =
                    ContextCompat.getDrawable(
                        calendarView.context,
                        R.drawable.shape_timer
                    )
                container.binding.tvDay.setTextColor(
                    ContextCompat.getColor(
                        calendarView.context,
                        R.color.white
                    )
                )
            }
        }

        checkToday(data, container)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun checkToday(data: CalendarDay, container: DayViewContainer) {
        val today = LocalDate.now()

        if (today.year == data.date.year && today.monthValue == data.date.monthValue && today.dayOfMonth == data.date.dayOfMonth) {
            setDeepBlue(container.binding)
            selectedDate = container.binding
        }
    }
}

