package ejarosiewicz.com.eventreminder.presentation.main

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider

class MainViewModelFactory: ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
            MainViewModel() as T
}