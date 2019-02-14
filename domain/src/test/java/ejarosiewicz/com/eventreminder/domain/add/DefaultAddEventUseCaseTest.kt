package ejarosiewicz.com.eventreminder.domain.add

import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import ejarosiewicz.com.eventreminder.data.EventRepository
import ejarosiewicz.com.eventreminder.data.room.pojo.EventEntity
import ejarosiewicz.com.eventreminder.domain.converter.EventConverter
import ejarosiewicz.com.eventreminder.domain.data.Event
import org.junit.Test

class DefaultAddEventUseCaseTest {

    private val mockEventRepository: EventRepository = mock()
    private val mockEventConverter: EventConverter = mock{
        on {convert(DUMMY_EVENT)} doReturn (DUMMY_EVENT_ENTITY)
    }

    private val systemUnderTest = DefaultAddEventUseCase(mockEventRepository, mockEventConverter)

    @Test
    fun `Should add event to thew repository`(){
        systemUnderTest.addEvent(DUMMY_EVENT)

        verify(mockEventConverter).convert(DUMMY_EVENT)
        verify(mockEventRepository).create(DUMMY_EVENT_ENTITY)
    }

    companion object {
        val DUMMY_EVENT = Event(
                name = "Some name"
        )

        val DUMMY_EVENT_ENTITY = EventEntity(
                name = "Some name"
        )
    }
}