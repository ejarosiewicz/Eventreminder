package ejarosiewicz.com.eventreminder.domain.add

import ejarosiewicz.com.eventreminder.domain.data.Event

interface AddEventUseCase {

    fun addEvent(event: Event)
}