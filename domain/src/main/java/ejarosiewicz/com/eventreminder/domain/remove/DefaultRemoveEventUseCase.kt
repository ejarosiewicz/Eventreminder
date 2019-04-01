package ejarosiewicz.com.eventreminder.domain.remove

import ejarosiewicz.com.eventreminder.domain.data.EventRepository
import ejarosiewicz.com.eventreminder.domain.entity.Event

class DefaultRemoveEventUseCase(private val eventRepository: EventRepository) : RemoveEventUseCase {

    override fun removeEvent(event: Event) {
        eventRepository.delete(event)
    }
}