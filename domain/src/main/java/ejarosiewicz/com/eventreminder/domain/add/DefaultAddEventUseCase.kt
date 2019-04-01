package ejarosiewicz.com.eventreminder.domain.add

import ejarosiewicz.com.eventreminder.domain.data.EventRepository
import ejarosiewicz.com.eventreminder.domain.entity.Event
import ejarosiewicz.com.eventreminder.domain.remove.RemoveEventUseCase

class DefaultAddEventUseCase(private val eventRepository: EventRepository) : AddEventUseCase {

    override fun addEvent(event: Event) {
        eventRepository.create(event)
    }
}