package ejarosiewicz.com.eventreminder.domain.read

import ejarosiewicz.com.eventreminder.domain.data.Event

interface ReadEventsUseCase {
    fun read(): List<Event>
}