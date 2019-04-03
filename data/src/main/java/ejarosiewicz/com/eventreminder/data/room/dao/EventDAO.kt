package ejarosiewicz.com.eventreminder.data.room.dao

import android.arch.persistence.room.*
import ejarosiewicz.com.eventreminder.data.room.pojo.EventEntity

@Dao
interface EventDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun create(event: EventEntity)

    @Query("SELECT * FROM event WHERE event_id = :eventId")
    fun read(eventId: String): EventEntity

    @Query("SELECT * FROM event")
    fun read(): List<EventEntity>

    @Update
    fun update(event: EventEntity)

    @Query("DELETE FROM event WHERE event_id = :eventId")
    fun delete(eventId: Long)
}