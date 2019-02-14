package ejarosiewicz.com.eventreminder.data.room.database

import ejarosiewicz.com.eventreminder.data.room.dao.EventDAO

interface EventsDatabaseProvider {

    fun getDao(): EventDAO
}