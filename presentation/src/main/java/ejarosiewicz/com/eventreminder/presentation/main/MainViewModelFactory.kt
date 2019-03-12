package ejarosiewicz.com.eventreminder.presentation.main

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import ejarosiewicz.com.async.Scheduler
import ejarosiewicz.com.eventreminder.domain.read.ReadEventsUseCase
import ejarosiewicz.com.eventreminder.presentation.navigator.Navigator

class MainViewModelFactory(val navigator: Navigator,
                           val scheduler: Scheduler,
                           val readEventsUseCase: ReadEventsUseCase): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
            MainViewModel(navigator, scheduler, readEventsUseCase) as T
}