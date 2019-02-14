package ejarosiewicz.com.eventreminder.data.room.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import ejarosiewicz.com.eventreminder.data.room.dao.EventDAO
import ejarosiewicz.com.eventreminder.data.room.pojo.EventEntity

@Database(
        entities = [EventEntity::class],
        version = 1
)
abstract class EventsDatabase : RoomDatabase() {

    abstract fun eventDao(): EventDAO
}