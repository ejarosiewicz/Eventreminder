package ejarosiewicz.com.eventreminder.domain.add

import ejarosiewicz.com.eventreminder.data.EventRepository
import ejarosiewicz.com.eventreminder.domain.converter.EventConverter
import ejarosiewicz.com.eventreminder.domain.data.Event

class DefaultAddEventUseCase(private val eventRepository: EventRepository,
                             private val eventConverter: EventConverter): AddEventUseCase {

    override fun addEvent(event: Event) {
        val entity = eventConverter.convert(event)
        eventRepository.create(entity)
    }

}