package ejarosiewicz.com.eventreminder.data.room.converter

import assertk.assertThat
import assertk.assertions.isEqualTo
import ejarosiewicz.com.eventreminder.data.DUMMY_EVENT
import ejarosiewicz.com.eventreminder.data.DUMMY_EVENT_ENTITY
import ejarosiewicz.com.eventreminder.data.room.pojo.EventEntity
import org.junit.Test

class DefaultEventConverterTest{

    private val systemUnderTest = DefaultEventConverter()

    @Test
    fun `Should convert event to entity`(){
        val expectedEntity = systemUnderTest.convert(DUMMY_EVENT)

        assertThat(expectedEntity.name).isEqualTo(DUMMY_EVENT.name)
        assertThat(expectedEntity.timestamp).isEqualTo(DUMMY_EVENT.timestamp)
    }

    @Test
    fun `Should convert entity to event`(){
        val expectedEvent = systemUnderTest.convert(DUMMY_EVENT_ENTITY)

        expectedEvent.apply {
            assertThat(id).isEqualTo(DUMMY_EVENT_ENTITY.eventId)
            assertThat(name).isEqualTo(DUMMY_EVENT_ENTITY.name)
            assertThat(timestamp).isEqualTo(DUMMY_EVENT_ENTITY.timestamp)
        }
    }

    @Test
    fun `Should convert empty entity to event with default values`(){
        val expectedEvent = systemUnderTest.convert(EMPTY_ENTITY)

        expectedEvent.apply {
            assertThat(id).isEqualTo(-1L)
            assertThat(name).isEqualTo("")
            assertThat(timestamp).isEqualTo(-1L)
        }

    }

    companion object {

        val EMPTY_ENTITY = EventEntity(
                eventId = null,
                name = null,
                timestamp = null
        )
    }
}