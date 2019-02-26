package ejarosiewicz.com.eventreminder.domain.read

//import ejarosiewicz.com.eventreminder.data.EventRepository
//import ejarosiewicz.com.eventreminder.domain.converter.EventConverter
//import ejarosiewicz.com.eventreminder.domain.entity.Event

//class DefaultReadEventsUseCase(private val eventRepository: EventRepository,
//                               private val eventConverter: EventConverter) : ReadEventsUseCase {
//
//    override fun read(): List<Event> = eventRepository
//            .read()
//            .map { entity -> eventConverter.convert(entity) }
//
//}