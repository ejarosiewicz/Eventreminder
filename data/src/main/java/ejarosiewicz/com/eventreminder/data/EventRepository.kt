package ejarosiewicz.com.eventreminder.data

import ejarosiewicz.com.eventreminder.data.room.pojo.EventEntity

interface EventRepository {

    fun create(entity: EventEntity)

    fun read(): List<EventEntity>

}