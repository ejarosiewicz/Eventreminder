package ejarosiewicz.com.eventreminder.data.room


import ejarosiewicz.com.eventreminder.data.room.dao.EventDAO
import ejarosiewicz.com.eventreminder.data.room.pojo.EventEntity
import ejarosiewicz.com.eventreminder.domain.converter.EventConverter
import ejarosiewicz.com.eventreminder.domain.data.EventRepository
import ejarosiewicz.com.eventreminder.domain.entity.Event

class RoomEventRepository(private val dao: EventDAO,
                          private val eventConverter: EventConverter) : EventRepository {
    override fun create(event: Event) {
        val entity = eventConverter.convert(event)
        dao.create(entity)
    }

    override fun read(): List<Event> =
            dao.read()
                    .map { entity -> eventConverter.convert(entity) }
}