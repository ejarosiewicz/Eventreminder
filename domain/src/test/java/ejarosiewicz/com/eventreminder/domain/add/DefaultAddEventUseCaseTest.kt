package ejarosiewicz.com.eventreminder.domain.add

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import ejarosiewicz.com.eventreminder.domain.data.EventRepository
import ejarosiewicz.com.eventreminder.domain.entity.Event
import org.junit.Test

class DefaultAddEventUseCaseTest {

    private val mockEventRepository: EventRepository = mock()

    private val systemUnderTest = DefaultAddEventUseCase(mockEventRepository)

    @Test
    fun `Should add event to repository`(){
        systemUnderTest.addEvent(DUMMY_EVENT)

        verify(mockEventRepository).create(DUMMY_EVENT)
    }

    companion object {
        val DUMMY_EVENT = Event(
                name = "Some name"
        )
    }
}