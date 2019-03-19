package ejarosiewicz.com.eventreminder.data.room.dao

import android.arch.persistence.room.*
import ejarosiewicz.com.eventreminder.data.room.pojo.EventEntity

@Dao
interface EventDAO {

    @Insert
    fun create(event: EventEntity)

    @Query("SELECT * FROM event WHERE event_id = :id")
    fun read(id: String): EventEntity

    @Query("SELECT * FROM event")
    fun read(): List<EventEntity>

    @Update
    fun update(event: EventEntity)

    @Delete
    fun delete(event: EventEntity)
}