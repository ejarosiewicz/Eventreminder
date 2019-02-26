package ejarosiewicz.com.eventreminder.presentation.add

import android.arch.lifecycle.ViewModel
import ejarosiewicz.com.eventreminder.domain.add.AddEventUseCase
import ejarosiewicz.com.eventreminder.domain.entity.Event
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class AddEventViewModel(private val addEventUseCase: AddEventUseCase): ViewModel() {


    fun addEvent(event: Event) {



    }

}