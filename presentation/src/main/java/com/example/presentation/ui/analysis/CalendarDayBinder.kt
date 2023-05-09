package com.example.presentation.ui.analysis

import android.os.Build
import android.view.View
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import com.example.presentation.R
import com.example.presentation.databinding.ItemCalendarDayBinding
import com.kizitonwose.calendar.core.CalendarDay
import com.kizitonwose.calendar.core.DayPosition
import com.kizitonwose.calendar.view.CalendarView
import com.kizitonwose.calendar.view.MonthDayBinder
import com.kizitonwose.calendar.view.ViewContainer

class CalendarDayBinder(
    private val calendarView: CalendarView,
    private val interviewedDays: List<List<Int>>
) : MonthDayBinder<CalendarDayBinder.DayViewContainer> {

    class DayViewContainer(
        val binding: ItemCalendarDayBinding
    ) : ViewContainer(binding.root)

    override fun create(view: View) = DayViewContainer(ItemCalendarDayBinding.bind(view))

    @RequiresApi(Build.VERSION_CODES.O)
    override fun bind(container: DayViewContainer, data: CalendarDay) {
        container.binding.tvDay.text = data.date.dayOfMonth.toString()

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

        interviewedDays.forEach{ date ->
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
    }

}

