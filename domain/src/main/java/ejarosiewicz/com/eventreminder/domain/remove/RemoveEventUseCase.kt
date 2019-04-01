package ejarosiewicz.com.eventreminder.domain.remove

import ejarosiewicz.com.eventreminder.domain.entity.Event

interface RemoveEventUseCase {

    fun removeEvent(event: Event)
}