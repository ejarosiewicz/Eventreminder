package ejarosiewicz.com.eventreminder.data.room

import assertk.assertThat
import assertk.assertions.contains
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import ejarosiewicz.com.eventreminder.data.room.dao.EventDAO
import ejarosiewicz.com.eventreminder.data.room.pojo.EventEntity
import org.junit.Test

class RoomEventRepositoryTest {

    private val mockDao: EventDAO = mock {
        on { read() } doReturn (listOf(DUMMY_EVENT_ENTITY))
    }

    private val systemUnderTest = RoomEventRepository(mockDao)

    @Test
    fun `Should create new entity`() {
        systemUnderTest.create(DUMMY_EVENT_ENTITY)

        verify(mockDao).create(DUMMY_EVENT_ENTITY)
    }

    @Test
    fun `Should read entities`() {
       systemUnderTest.read()

        verify(mockDao).read()
    }

    companion object {

        val DUMMY_EVENT_ENTITY = EventEntity(-1, "name")
    }
}