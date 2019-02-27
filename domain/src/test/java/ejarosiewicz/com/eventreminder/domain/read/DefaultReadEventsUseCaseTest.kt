package ejarosiewicz.com.eventreminder.domain.read

import com.nhaarman.mockitokotlin2.*
import ejarosiewicz.com.eventreminder.domain.data.EventRepository
import org.junit.Test

class DefaultReadEventsUseCaseTest {

    private val mockEventRepository: EventRepository = mock()

    private val systemUnderTest = DefaultReadEventsUseCase(mockEventRepository)

    @Test
    fun `Should use event repository to read`() {
        systemUnderTest.read()

        verify(mockEventRepository).read()
    }
}