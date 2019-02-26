package ejarosiewicz.com.eventreminder.domain.read

import ejarosiewicz.com.eventreminder.domain.entity.Event

interface ReadEventsUseCase {
    fun read(): List<Event>
}