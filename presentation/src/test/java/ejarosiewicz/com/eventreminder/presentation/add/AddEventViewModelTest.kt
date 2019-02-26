package ejarosiewicz.com.eventreminder.presentation.add

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import ejarosiewicz.com.eventreminder.domain.add.AddEventUseCase
import ejarosiewicz.com.eventreminder.domain.entity.Event
import org.junit.Test

class AddEventViewModelTest{

    private val mockAddEventUseCase: AddEventUseCase = mock()

    private val systemUnderTest = AddEventViewModel(mockAddEventUseCase)

    @Test
    fun `Should add valid event`(){
        systemUnderTest.addEvent(SAMPLE_EVENT)


        verify(mockAddEventUseCase).addEvent(SAMPLE_EVENT)
    }

    companion object {
        val SAMPLE_EVENT = Event(-1, "SAMPLE")

    }
}