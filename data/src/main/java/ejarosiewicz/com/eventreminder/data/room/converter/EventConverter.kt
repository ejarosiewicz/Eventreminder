package ejarosiewicz.com.eventreminder.domain.converter


import ejarosiewicz.com.eventreminder.data.room.pojo.EventEntity
import ejarosiewicz.com.eventreminder.domain.entity.Event

interface EventConverter {

    fun convert(event: Event): EventEntity

    fun convert(entity: EventEntity): Event
}