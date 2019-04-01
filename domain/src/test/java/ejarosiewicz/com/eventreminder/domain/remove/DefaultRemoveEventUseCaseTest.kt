package ejarosiewicz.com.eventreminder.domain.remove

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import ejarosiewicz.com.eventreminder.domain.data.EventRepository
import ejarosiewicz.com.eventreminder.domain.entity.Event
import org.junit.Test

class DefaultRemoveEventUseCaseTest{
    private val mockEventRepository: EventRepository = mock()

    private val systemUnderTest = DefaultRemoveEventUseCase(mockEventRepository)

    @Test
    fun `Should add event to repository`(){
        systemUnderTest.removeEvent(DUMMY_EVENT)

        verify(mockEventRepository).delete(DUMMY_EVENT)
    }

    companion object {
        val DUMMY_EVENT = Event(
                name = "Some name"
        )
    }
}