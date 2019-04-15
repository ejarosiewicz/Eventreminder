package ejarosiewicz.com.eventreminder.data

import ejarosiewicz.com.eventreminder.data.room.pojo.EventEntity
import ejarosiewicz.com.eventreminder.domain.entity.Event

val DUMMY_EVENT_ENTITY = EventEntity(-1, "name",53621471)

val DUMMY_EVENT = Event(
        name = "Some name",
        timestamp = 123243902
)
