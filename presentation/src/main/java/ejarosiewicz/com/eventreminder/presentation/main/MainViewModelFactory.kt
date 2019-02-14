package ejarosiewicz.com.eventreminder.presentation.main

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import ejarosiewicz.com.eventreminder.presentation.navigator.Navigator

class MainViewModelFactory(val navigator: Navigator): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
            MainViewModel(navigator) as T
}