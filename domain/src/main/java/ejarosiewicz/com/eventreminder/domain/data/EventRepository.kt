package ejarosiewicz.com.eventreminder.domain.data

import ejarosiewicz.com.eventreminder.domain.entity.Event

interface EventRepository {

    fun create(event: Event)

    fun read(): List<Event>

}