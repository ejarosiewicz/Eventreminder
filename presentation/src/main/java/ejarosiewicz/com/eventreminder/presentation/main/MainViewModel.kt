package ejarosiewicz.com.eventreminder.presentation.main

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import ejarosiewicz.com.async.Scheduler
import ejarosiewicz.com.async.Thread
import ejarosiewicz.com.eventreminder.domain.entity.Event
import ejarosiewicz.com.eventreminder.domain.read.ReadEventsUseCase
import ejarosiewicz.com.eventreminder.presentation.navigator.Navigator

class MainViewModel(private val navigator: Navigator,
                    private val scheduler: Scheduler,
                    private val readEventsUseCase: ReadEventsUseCase) : ViewModel() {

    val stateData = MutableLiveData<MainStateHolder>()

    fun goToAddScreen() {
        navigator.goToAddScreen()
    }

    fun loadEvents() {
        scheduler.schedule(
                operation = { readEventsUseCase.read() },
                operationThread = Thread.IO,
                resultThread = Thread.MAIN,
                onSuccess = { events -> onEventsLoad(events) }
        )
    }

    private fun onEventsLoad(events: List<Event>) {
        val loadedState = MainStateHolder(MainState.EVENTS_LOADED, events)
        stateData.value = loadedState
    }
}