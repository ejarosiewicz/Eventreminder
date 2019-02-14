package ejarosiewicz.com.eventreminder.domain.read

import com.nhaarman.mockitokotlin2.*
import ejarosiewicz.com.eventreminder.data.EventRepository
import ejarosiewicz.com.eventreminder.data.room.pojo.EventEntity
import ejarosiewicz.com.eventreminder.domain.converter.EventConverter
import org.junit.Test

class DefaultReadEventsUseCaseTest {

    private val mockEventConverter: EventConverter = mock()
    private val mockEventRepository: EventRepository = mock{
        on {read()} doReturn (DUMMY_EVENT_ENTITY_LIST)
    }

    private val systemUnderTest = DefaultReadEventsUseCase(mockEventRepository, mockEventConverter)

    @Test
    fun `Should read all events`() {
      systemUnderTest.read()

        verify(mockEventRepository).read()
        verify(mockEventConverter, times(DUMMY_EVENT_ENTITY_LIST.size)).convert(any<EventEntity>())

    }

    companion object {
        val DUMMY_EVENT_ENTITY = EventEntity(
                name = "name"
        )
        val DUMMY_EVENT_ENTITY_LIST = listOf(DUMMY_EVENT_ENTITY)
    }
}