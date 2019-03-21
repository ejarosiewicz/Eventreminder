package ejarosiewicz.com.eventreminder.presentation.add

import android.arch.lifecycle.ViewModel
import android.support.v4.app.FragmentManager
import ejarosiewicz.com.async.Scheduler
import ejarosiewicz.com.async.Thread
import ejarosiewicz.com.eventreminder.domain.add.AddEventUseCase
import ejarosiewicz.com.eventreminder.domain.entity.Event
import ejarosiewicz.com.eventreminder.presentation.navigator.Navigator

class AddEventViewModel(private val addEventUseCase: AddEventUseCase,
                        private val scheduler: Scheduler,
                        private val navigator: Navigator) : ViewModel() {

    var eventName: String = ""
    lateinit var fragmentManager: FragmentManager

    override fun onCleared() {
        scheduler.cancel()
    }

    fun addEvent(){
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
        fragmentManager.popBackStack()
    }

}