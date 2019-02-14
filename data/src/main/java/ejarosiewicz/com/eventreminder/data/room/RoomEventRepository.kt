package ejarosiewicz.com.eventreminder.data.room

import ejarosiewicz.com.eventreminder.data.Event
import ejarosiewicz.com.eventreminder.data.EventRepository
import ejarosiewicz.com.eventreminder.data.room.converter.EventConverter
import ejarosiewicz.com.eventreminder.data.room.dao.EventDAO

class RoomEventRepository(private val dao: EventDAO,
                          private val eventConverter: EventConverter) : EventRepository {

    override fun create(event: Event) {
        val entity = eventConverter.convert(event)
        dao.create(entity)
    }

    override fun read(): List<Event> = dao
            .read()
            .map { entity -> eventConverter.convert(entity) }


}