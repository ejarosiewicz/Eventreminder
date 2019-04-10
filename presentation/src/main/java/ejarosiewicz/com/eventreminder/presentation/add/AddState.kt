package ejarosiewicz.com.eventreminder.presentation.add

sealed class AddState
object StateEventAdded : AddState()
data class StateOpenDatePicker(val year: Int,
                               val month: Int,
                               val day: Int) : AddState()

data class StateOpenTimePicker(val hour: Int,
                               val minute: Int) : AddState()