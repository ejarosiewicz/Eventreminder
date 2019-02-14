package ejarosiewicz.com.eventreminder.data.room.converter

import ejarosiewicz.com.eventreminder.data.Event
import ejarosiewicz.com.eventreminder.data.room.pojo.EventEntity

interface EventConverter {

    fun convert(event: Event): EventEntity

    fun convert(entity: EventEntity): Event
}