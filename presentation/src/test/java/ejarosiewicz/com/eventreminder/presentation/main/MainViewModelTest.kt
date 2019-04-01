package ejarosiewicz.com.eventreminder.presentation.main

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.lifecycle.Observer
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.argumentCaptor
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import ejarosiewicz.com.async.Scheduler
import ejarosiewicz.com.eventreminder.domain.entity.Event
import ejarosiewicz.com.eventreminder.domain.read.ReadEventsUseCase
import ejarosiewicz.com.eventreminder.presentation.navigator.Navigator
import assertk.assertThat
import assertk.assertions.isEqualTo
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

    private val mockStateObserver: Observer<MainStateHolder> = mock()

    private val systemUnderTest = MainViewModel(mockNavigator, mockScheduler, mockReadEventsUseCase)

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

    companion object {
        private val DUMMY_EVENT = Event(
                name = "Some name"
        )
        private val DUMMY_EVENT_LIST = listOf(DUMMY_EVENT)
    }

}