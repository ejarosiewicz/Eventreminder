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
    }

    @Test
    fun `Should convert entity to event`(){
        val expectedEvent = systemUnderTest.convert(DUMMY_EVENT_ENTITY)

        assertThat(expectedEvent.name).isEqualTo(DUMMY_EVENT_ENTITY.name)
    }

    @Test
    fun `Should convert empty entity to event with default values`(){
        val expectedEvent = systemUnderTest.convert(EMPTY_ENTITY)

        assertThat(expectedEvent.name).isEqualTo("")
    }

    companion object {

        val EMPTY_ENTITY = EventEntity(
                name = null
        )
    }
}