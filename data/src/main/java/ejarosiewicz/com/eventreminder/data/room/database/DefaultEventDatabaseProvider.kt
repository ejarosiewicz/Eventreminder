package ejarosiewicz.com.eventreminder.data.room.database

import android.arch.persistence.room.Room
import android.content.Context
import ejarosiewicz.com.eventreminder.data.room.dao.EventDAO

class DefaultEventDatabaseProvider(val applicationContext: Context): EventsDatabaseProvider {

    val database: EventsDatabase = Room.databaseBuilder(
            applicationContext,
            EventsDatabase::class.java, "events-database"
    ).build()

    override fun getDao(): EventDAO = database.eventDao()
    
}