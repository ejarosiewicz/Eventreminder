package ejarosiewicz.com.eventreminder.presentation.add

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.lifecycle.Observer
import assertk.assertThat
import assertk.assertions.isEqualTo
import com.nhaarman.mockitokotlin2.*
import ejarosiewicz.com.async.Scheduler
import ejarosiewicz.com.async.Thread
import ejarosiewicz.com.eventreminder.domain.add.AddEventUseCase
import ejarosiewicz.com.eventreminder.domain.entity.Event
import ejarosiewicz.com.eventreminder.presentation.navigator.Navigator
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class AddEventViewModelTest {

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    private val mockAddEventUseCase: AddEventUseCase = mock()
    private val mockScheduler: Scheduler = mock()

    private val mockStateObserver: Observer<AddState> = mock()

    private val systemUnderTest = AddEventViewModel(mockAddEventUseCase, mockScheduler)

    @Before
    fun `Set up`(){
        systemUnderTest.stateData
                .observeForever(mockStateObserver)
    }

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
        val addStateCaptor = argumentCaptor<AddState>()
        val onSuccessCaptor = argumentCaptor<(Unit) -> Unit>()

        systemUnderTest.addEvent(SAMPLE_EVENT)
        verify(mockScheduler).schedule(
                operation = any(),
                operationThread = any(),
                resultThread = any(),
                onSuccess = onSuccessCaptor.capture()
        )
        onSuccessCaptor.firstValue.invoke(Unit)
        verify(mockStateObserver).onChanged(addStateCaptor.capture())

        assertThat(addStateCaptor.firstValue).isEqualTo(AddState.EVENT_ADDED)
    }

    companion object {
        val SAMPLE_EVENT = Event(-1, "SAMPLE")

    }
}