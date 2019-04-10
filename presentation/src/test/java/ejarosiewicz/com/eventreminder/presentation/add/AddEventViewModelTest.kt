package ejarosiewicz.com.eventreminder.presentation.add

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.lifecycle.Observer
import assertk.assertThat
import assertk.assertions.hasClass
import assertk.assertions.isEqualTo
import com.nhaarman.mockitokotlin2.*
import ejarosiewicz.com.async.Scheduler
import ejarosiewicz.com.async.Thread
import ejarosiewicz.com.eventreminder.domain.add.AddEventUseCase
import ejarosiewicz.com.eventreminder.domain.entity.Event
import ejarosiewicz.com.eventreminder.presentation.date.ActualDateProvider
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class AddEventViewModelTest {

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    private val mockAddEventUseCase: AddEventUseCase = mock()
    private val mockScheduler: Scheduler = mock()
    private val mockActualDateProvider: ActualDateProvider = mock {
        on { year } doReturn (SAMPLE_YEAR)
        on { month } doReturn (SAMPLE_MONTH)
        on { day } doReturn (SAMPLE_DAY)
        on { hour } doReturn (SAMPLE_HOUR)
        on { minute } doReturn (SAMPLE_MINUTE)
    }

    private val mockStateObserver: Observer<AddState> = mock()

    private val systemUnderTest = AddEventViewModel(mockAddEventUseCase, mockScheduler, mockActualDateProvider)

    @Before
    fun `Set up`() {
        systemUnderTest.stateData
                .observeForever(mockStateObserver)
    }

    @Test
    fun `Add event asynchronously`() {
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
    fun `Do operation on proper threads`() {
        systemUnderTest.addEvent(SAMPLE_EVENT)

        verify(mockScheduler).schedule(
                operation = any<() -> Event>(),
                operationThread = eq(Thread.IO),
                resultThread = eq(Thread.MAIN),
                onSuccess = any()
        )
    }

    @Test
    fun `Go to the main screen after adding event`() {
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

        assertThat(addStateCaptor.firstValue).isEqualTo(StateEventAdded)
    }

    @Test
    fun `Send open date picker event with date after request`() {
        val addStateCaptor = argumentCaptor<AddState>()

        systemUnderTest.onDatePickerOpened()
        verify(mockStateObserver).onChanged(addStateCaptor.capture())

        assertThat(addStateCaptor.firstValue).hasClass(StateOpenDatePicker::class)
        val openDateState = addStateCaptor.firstValue as StateOpenDatePicker
        assertThat(openDateState.day).isEqualTo(SAMPLE_DAY)
        assertThat(openDateState.month).isEqualTo(SAMPLE_MONTH)
        assertThat(openDateState.year).isEqualTo(SAMPLE_YEAR)
    }

    @Test
    fun `Send open time picker event with time after request`() {
        val addStateCaptor = argumentCaptor<AddState>()

        systemUnderTest.onTimePickerOpened()
        verify(mockStateObserver).onChanged(addStateCaptor.capture())

        assertThat(addStateCaptor.firstValue).hasClass(StateOpenTimePicker::class)
        val openTimeState = addStateCaptor.firstValue as StateOpenTimePicker
        assertThat(openTimeState.hour).isEqualTo(SAMPLE_HOUR)
        assertThat(openTimeState.minute).isEqualTo(SAMPLE_MINUTE)
    }

    @Test
    fun `Set proper date`() {
        val expectedDay = 1
        val expectedMonth = 7
        val expectedYear = 1990


        systemUnderTest.setDate(expectedYear, expectedMonth, expectedDay)

        assertThat(systemUnderTest.eventDate).isEqualTo("$expectedYear-$expectedMonth-$expectedDay")
    }

    @Test
    fun `Set proper time`() {
        val expectedHour = 12
        val expectedMinute = 29

        systemUnderTest.setTime(expectedHour, expectedMinute)

        assertThat(systemUnderTest.eventTime).isEqualTo("$expectedHour:$expectedMinute")
    }

    companion object {
        val SAMPLE_EVENT = Event(-1, "SAMPLE")

        val SAMPLE_DAY = 13
        val SAMPLE_MONTH = 10
        val SAMPLE_YEAR = 2173
        val SAMPLE_HOUR = 12
        val SAMPLE_MINUTE = 23

    }
}