package ejarosiewicz.com.eventreminder.presentation.add

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import ejarosiewicz.com.async.Scheduler
import ejarosiewicz.com.eventreminder.domain.add.AddEventUseCase
import ejarosiewicz.com.eventreminder.presentation.navigator.Navigator

class AddEventViewModelFactory(private val addEventUseCase: AddEventUseCase,
                               private val scheduler: Scheduler) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
            AddEventViewModel(addEventUseCase, scheduler) as T
}