package ejarosiewicz.com.eventreminder.presentation.add

import android.arch.lifecycle.ViewModel
import android.provider.Contacts
import ejarosiewicz.com.eventreminder.domain.add.AddEventUseCase
import ejarosiewicz.com.eventreminder.domain.data.Event
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class AddEventViewModel(private val addEventUseCase: AddEventUseCase): ViewModel() {


    fun addEvent(event: Event) {

        GlobalScope.launch(Dispatchers.IO) {
            addEventUseCase.addEvent(event)
            GlobalScope.launch(Dispatchers.Main) {  }
        }


    }

}