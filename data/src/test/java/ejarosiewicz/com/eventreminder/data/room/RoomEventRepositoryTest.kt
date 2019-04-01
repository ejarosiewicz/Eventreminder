package ejarosiewicz.com.eventreminder.data.room

import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import ejarosiewicz.com.eventreminder.data.DUMMY_EVENT
import ejarosiewicz.com.eventreminder.data.DUMMY_EVENT_ENTITY
import ejarosiewicz.com.eventreminder.data.room.dao.EventDAO
import ejarosiewicz.com.eventreminder.domain.converter.EventConverter
import org.junit.Test

class RoomEventRepositoryTest {

    private val mockDao: EventDAO = mock {
        on { read() } doReturn (listOf(DUMMY_EVENT_ENTITY))
    }
    private val mockEventConverter: EventConverter = mock {
        on { convert(DUMMY_EVENT_ENTITY) } doReturn (DUMMY_EVENT)
        on { convert(DUMMY_EVENT) } doReturn (DUMMY_EVENT_ENTITY)
    }

    private val systemUnderTest = RoomEventRepository(mockDao, mockEventConverter)

    @Test
    fun `Should create new entity`() {
        systemUnderTest.create(DUMMY_EVENT)

        verify(mockDao).create(DUMMY_EVENT_ENTITY)
    }

    @Test
    fun `Should read entities`() {
        systemUnderTest.read()

        verify(mockDao).read()
        verify(mockEventConverter).convert(DUMMY_EVENT_ENTITY)
    }
}