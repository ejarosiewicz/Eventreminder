package ejarosiewicz.com.eventreminder.presentation.main

import android.arch.lifecycle.ViewModel
import ejarosiewicz.com.eventreminder.presentation.navigator.FragmentNavigator
import ejarosiewicz.com.eventreminder.presentation.navigator.Navigator
import java.util.*

class MainViewModel(private val navigator: Navigator) : ViewModel() {

    fun goToAddScreen() {
        navigator.goToAddScreen()
    }


}