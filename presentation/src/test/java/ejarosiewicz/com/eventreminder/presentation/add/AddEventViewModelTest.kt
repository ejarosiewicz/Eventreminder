package ejarosiewicz.com.eventreminder.presentation.add

import com.nhaarman.mockitokotlin2.*
import ejarosiewicz.com.async.Scheduler
import ejarosiewicz.com.async.Thread
import ejarosiewicz.com.eventreminder.domain.add.AddEventUseCase
import ejarosiewicz.com.eventreminder.domain.entity.Event
import ejarosiewicz.com.eventreminder.presentation.navigator.Navigator
import org.junit.Test

class AddEventViewModelTest {

    private val mockAddEventUseCase: AddEventUseCase = mock()
    private val mockScheduler: Scheduler = mock()
    private val mockNavigator: Navigator = mock()

    private val systemUnderTest = AddEventViewModel(mockAddEventUseCase, mockScheduler, mockNavigator)

    @Test
    fun `Should add event asynchronously`() {
        val operationArgumentCaptor = argumentCaptor<() -> Unit>()

        systemUnderTest.addEvent(SAMPLE_EVENT)
        verify(mockScheduler).schedule(
                operation = operationArgumentCaptor.capture(),
                operationThread = any(),
                resultThread = any(),
                onSuccess = any()
        )
        operationArgumentCaptor.firstValue()


        verify(mockAddEventUseCase).addEvent(SAMPLE_EVENT)
    }

    @Test
    fun `Should do operation on proper threads`() {
        systemUnderTest.addEvent(SAMPLE_EVENT)

        verify(mockScheduler).schedule(
                operation = any<() -> Event>(),
                operationThread = eq(Thread.IO),
                resultThread = eq(Thread.MAIN),
                onSuccess = any()
        )
    }

    @Test
    fun `Should go to the main screen after adding event`() {
        val onSuccessCaptor = argumentCaptor<(Any) -> Unit>()

        systemUnderTest.addEvent(SAMPLE_EVENT)
        verify(mockScheduler).schedule(
                operation = any(),
                operationThread = any(),
                resultThread = any(),
                onSuccess = onSuccessCaptor.capture()
        )
        onSuccessCaptor.firstValue.invoke(Unit)

        verify(mockNavigator).goToMainScreen()
    }

    companion object {
        val SAMPLE_EVENT = Event(-1, "SAMPLE")

    }
}