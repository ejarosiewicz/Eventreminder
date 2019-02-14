package ejarosiewicz.com.eventreminder.data.room.converter

import assertk.assertThat
import assertk.assertions.isEqualTo
import ejarosiewicz.com.eventreminder.data.Event
import ejarosiewicz.com.eventreminder.data.room.pojo.EventEntity
import org.junit.Test

class DefaultEventConverterTest {

    private val systemUnderTest = DefaultEventConverter()

    @Test
    fun `Should convert event to entity`(){
        val expectedEntity = systemUnderTest.convert(exampleEvent)

        assertThat(expectedEntity.name).isEqualTo(exampleEvent.name)
    }

    @Test
    fun `Should convert entity to event`(){
        val expectedEvent = systemUnderTest.convert(exampleEntity)

        assertThat(expectedEvent.name).isEqualTo(exampleEntity.name)
    }

    @Test
    fun `Should convert empty entity to event with default values`(){
        val expectedEvent = systemUnderTest.convert(exampleEmptyEntity)

        assertThat(expectedEvent.name).isEqualTo("")
    }

    companion object {
        val exampleEvent = Event(
                name = "Some event"
        )
        val exampleEntity = EventEntity(
                name = "Some event"
        )
        val exampleEmptyEntity = EventEntity(
                name = null
        )
    }
}