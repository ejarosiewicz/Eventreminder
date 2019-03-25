package ejarosiewicz.com.eventreminder.presentation.add

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.support.v4.app.FragmentManager
import ejarosiewicz.com.async.Scheduler
import ejarosiewicz.com.async.Thread
import ejarosiewicz.com.eventreminder.domain.add.AddEventUseCase
import ejarosiewicz.com.eventreminder.domain.entity.Event
import ejarosiewicz.com.eventreminder.presentation.navigator.Navigator

class AddEventViewModel(private val addEventUseCase: AddEventUseCase,
                        private val scheduler: Scheduler) : ViewModel() {

    var eventName: String = ""

    val stateData = MutableLiveData<AddState>()

    override fun onCleared() {
        scheduler.cancel()
    }

    fun addEvent() {
        val event = Event(name = eventName)
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
        stateData.value = AddState.EVENT_ADDED
    }

}