package ejarosiewicz.com.eventreminder.data.room

import ejarosiewicz.com.eventreminder.data.EventRepository
import ejarosiewicz.com.eventreminder.data.room.dao.EventDAO
import ejarosiewicz.com.eventreminder.data.room.pojo.EventEntity

class RoomEventRepository(private val dao: EventDAO) : EventRepository {

    override fun create(entity: EventEntity) {
        dao.create(entity)
    }

    override fun read(): List<EventEntity> = dao.read()
}