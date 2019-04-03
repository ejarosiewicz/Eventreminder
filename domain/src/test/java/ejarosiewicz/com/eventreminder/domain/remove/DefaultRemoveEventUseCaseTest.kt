package ejarosiewicz.com.eventreminder.domain.remove

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import ejarosiewicz.com.eventreminder.domain.data.EventRepository
import org.junit.Test

class DefaultRemoveEventUseCaseTest {

    private val mockEventRepository: EventRepository = mock()

    private val systemUnderTest = DefaultRemoveEventUseCase(mockEventRepository)

    @Test
    fun `Should remove event to repository`() {
        systemUnderTest.removeEvent(DUMMY_EVENT_ID)

        verify(mockEventRepository).delete(DUMMY_EVENT_ID)
    }

    companion object {
        val DUMMY_EVENT_ID = 123L
    }
}