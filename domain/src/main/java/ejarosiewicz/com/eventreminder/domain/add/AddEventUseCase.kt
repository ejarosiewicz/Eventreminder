package ejarosiewicz.com.eventreminder.domain.add

import ejarosiewicz.com.eventreminder.domain.entity.Event

interface AddEventUseCase {

    fun addEvent(event: Event)
}