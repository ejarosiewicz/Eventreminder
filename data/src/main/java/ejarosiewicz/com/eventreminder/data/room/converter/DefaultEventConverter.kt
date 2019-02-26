package ejarosiewicz.com.eventreminder.data.room.converter

import ejarosiewicz.com.eventreminder.data.room.pojo.EventEntity
import ejarosiewicz.com.eventreminder.domain.converter.EventConverter
import ejarosiewicz.com.eventreminder.domain.entity.Event

class DefaultEventConverter: EventConverter {

    override fun convert(event: Event): EventEntity = EventEntity(
            name = event.name
    )

    override fun convert(entity: EventEntity): Event = Event(
            name = entity.name ?: ""
    )
}