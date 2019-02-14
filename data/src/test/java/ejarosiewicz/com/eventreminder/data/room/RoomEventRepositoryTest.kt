package ejarosiewicz.com.eventreminder.data.room

import assertk.assertThat
import assertk.assertions.contains
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import ejarosiewicz.com.eventreminder.data.Event
import ejarosiewicz.com.eventreminder.data.room.converter.EventConverter
import ejarosiewicz.com.eventreminder.data.room.dao.EventDAO
import ejarosiewicz.com.eventreminder.data.room.pojo.EventEntity
import org.junit.Test

class RoomEventRepositoryTest {

    private val mockDao: EventDAO = mock {
        on { read() } doReturn (listOf(DUMMY_EVENT_ENTITY))
    }
    private val mockEventConverter: EventConverter = mock {
        on { convert(DUMMY_EVENT) } doReturn (DUMMY_EVENT_ENTITY)
        on { convert(DUMMY_EVENT_ENTITY) } doReturn (DUMMY_EVENT)
    }

    private val systemUnderTest = RoomEventRepository(mockDao, mockEventConverter)

    @Test
    fun `Should create new entity`() {
        systemUnderTest.create(DUMMY_EVENT)

        verify(mockEventConverter).convert(DUMMY_EVENT)
        verify(mockDao).create(DUMMY_EVENT_ENTITY)
    }

    @Test
    fun `Should read entities`() {
        val  expectedEvents = systemUnderTest.read()

        verify(mockDao).read()
        assertThat(expectedEvents).contains(DUMMY_EVENT)
    }

    companion object {
        val DUMMY_EVENT = Event("name")
        val DUMMY_EVENT_ENTITY = EventEntity(-1, "name")
    }
}