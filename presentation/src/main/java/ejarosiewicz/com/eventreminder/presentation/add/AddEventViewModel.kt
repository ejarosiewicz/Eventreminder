package ejarosiewicz.com.eventreminder.presentation.add

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import ejarosiewicz.com.async.Scheduler
import ejarosiewicz.com.async.Thread
import ejarosiewicz.com.eventreminder.domain.add.AddEventUseCase
import ejarosiewicz.com.eventreminder.domain.entity.Event
import ejarosiewicz.com.eventreminder.presentation.date.ActualDateProvider

class AddEventViewModel(private val addEventUseCase: AddEventUseCase,
                        private val scheduler: Scheduler,
                        private val actualDateProvider: ActualDateProvider) : ViewModel() {

    var eventName: String = ""
    var eventDate: String = ""
    var eventTime: String = ""

    private var year: Int = actualDateProvider.year
    private var month: Int = actualDateProvider.month
    private var day: Int = actualDateProvider.day
    private var hour: Int = actualDateProvider.hour
    private var minute: Int = actualDateProvider.minute

    val stateData = MutableLiveData<AddState>()

    override fun onCleared() {
        scheduler.cancel()
    }

    fun addEvent() {
        val event = Event(name = eventName,
                timestamp = actualDateProvider.provideTimestamp(
                        year = year,
                        month = month,
                        day = day,
                        hour = hour,
                        minute = minute
                ))
        addEvent(event)
    }

    fun addEvent(event: Event) {
        scheduler.schedule(
                operation = { addEventUseCase.addEvent(event) },
                operationThread = Thread.IO,
                resultThread = Thread.MAIN,
                onSuccess = { onEventAdd() }
        )
    }

    private fun onEventAdd() {
        stateData.value = StateEventAdded
    }

    fun onDatePickerOpened() {
        stateData.value = StateOpenDatePicker(year, month, day)
    }

    fun onTimePickerOpened() {
        stateData.value = StateOpenTimePicker(hour, minute)
    }

    fun setDate(year: Int, month: Int, day: Int) {
        this.year = year
        this.month = month
        this.day = day
        eventDate = "$year-$month-$day"
    }

    fun setTime(hour: Int, minute: Int) {
        this.hour = hour
        this.minute = minute
        eventTime = "$hour:$minute"
    }
}