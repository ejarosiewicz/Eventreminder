package ejarosiewicz.com.eventreminder.data

interface EventRepository {

    fun create(event: Event)

    fun read(): List<Event>

}