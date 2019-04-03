package ejarosiewicz.com.eventreminder.presentation.main

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.lifecycle.Observer
import ejarosiewicz.com.async.Scheduler
import ejarosiewicz.com.eventreminder.domain.entity.Event
import ejarosiewicz.com.eventreminder.domain.read.ReadEventsUseCase
import ejarosiewicz.com.eventreminder.presentation.navigator.Navigator
import assertk.assertThat
import assertk.assertions.isEqualTo
import com.nhaarman.mockitokotlin2.*
import ejarosiewicz.com.async.Thread
import ejarosiewicz.com.eventreminder.domain.remove.RemoveEventUseCase
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MainViewModelTest{

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    private val mockNavigator: Navigator = mock()
    private val mockScheduler: Scheduler = mock()
    private val mockReadEventsUseCase: ReadEventsUseCase = mock()
    private val mockRemoveEventUseCase: RemoveEventUseCase = mock()

    private val mockStateObserver: Observer<MainStateHolder> = mock()

    private val systemUnderTest = MainViewModel(mockNavigator, mockScheduler, mockReadEventsUseCase, mockRemoveEventUseCase)

    @Before
    fun `Set up`(){
        systemUnderTest.stateData
                .observeForever(mockStateObserver)
    }

    @Test
    fun `Should go to the Add event screen after invoking action`(){
        systemUnderTest.goToAddScreen()

        verify(mockNavigator).goToAddScreen()
    }

    @Test
    fun `Should load events after request`(){
        val onSuccessCaptor = argumentCaptor<(List<Event>) -> Unit>()
        val mainStateCaptor = argumentCaptor<MainStateHolder>()

        systemUnderTest.loadEvents()
        verify(mockScheduler).schedule(
                operation = any(),
                operationThread = any(),
                resultThread = any(),
                onSuccess = onSuccessCaptor.capture()
        )
        onSuccessCaptor.firstValue.invoke(DUMMY_EVENT_LIST)


        verify(mockStateObserver).onChanged(mainStateCaptor.capture())
        mainStateCaptor.firstValue.apply {
            assertThat(state).isEqualTo(MainState.EVENTS_LOADED)
            assertThat(events).isEqualTo(DUMMY_EVENT_LIST)
        }
    }

    @Test
    fun `Should delete events after request`(){
        val operationCaptor = argumentCaptor<() -> Unit>()
        val onSuccessCaptor = argumentCaptor<(Any) -> Unit>()
        val mainStateCaptor = argumentCaptor<MainStateHolder>()

        systemUnderTest.deleteEvent(DUMMY_EVENT_ID)
        verify(mockScheduler).schedule(
                operation = operationCaptor.capture(),
                operationThread = eq(Thread.IO),
                resultThread = eq(Thread.MAIN),
                onSuccess = onSuccessCaptor.capture()
        )
        operationCaptor.firstValue.invoke()
        onSuccessCaptor.firstValue.invoke(DUMMY_EVENT_LIST)

        verify(mockRemoveEventUseCase).removeEvent(DUMMY_EVENT_ID)
        verify(mockStateObserver).onChanged(mainStateCaptor.capture())
        mainStateCaptor.firstValue.apply {
            assertThat(state).isEqualTo(MainState.EVENT_DELETED)
        }
    }

    @Test
    fun `Should reload events after deletion`(){
        val operationCaptor = argumentCaptor<() -> Unit>()
        val onSuccessCaptor = argumentCaptor<(Any) -> Unit>()
        val mainStateCaptor = argumentCaptor<MainStateHolder>()

        systemUnderTest.deleteEvent(DUMMY_EVENT_ID)
        verify(mockScheduler).schedule(
                operation = operationCaptor.capture(),
                operationThread = any(),
                resultThread = any(),
                onSuccess = onSuccessCaptor.capture()
        )
        operationCaptor.firstValue.invoke()
        onSuccessCaptor.firstValue.invoke(DUMMY_EVENT_LIST)

        verify(mockReadEventsUseCase).read()
        verify(mockStateObserver).onChanged(mainStateCaptor.capture())
        mainStateCaptor.firstValue.apply {
            assertThat(events).isEqualTo(DUMMY_EVENT_LIST)
        }
    }

    companion object {
        private val DUMMY_EVENT_ID = 1920L

        private val DUMMY_EVENT = Event(
                name = "Some name"
        )
        private val DUMMY_EVENT_LIST = listOf(DUMMY_EVENT)
    }

}