package ejarosiewicz.com.eventreminder.domain.read

import ejarosiewicz.com.eventreminder.domain.data.EventRepository
import ejarosiewicz.com.eventreminder.domain.entity.Event

class DefaultReadEventsUseCase(private val eventRepository: EventRepository) : ReadEventsUseCase {

    override fun read(): List<Event> = eventRepository.read()

}