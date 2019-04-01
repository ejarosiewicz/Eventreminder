package ejarosiewicz.com.eventreminder.presentation.di

import android.content.Context
import ejarosiewicz.com.eventreminder.data.room.RoomEventRepository
import ejarosiewicz.com.eventreminder.data.room.converter.DefaultEventConverter
import ejarosiewicz.com.eventreminder.data.room.dao.EventDAO
import ejarosiewicz.com.eventreminder.data.room.database.DefaultEventDatabaseProvider
import ejarosiewicz.com.eventreminder.data.room.database.EventsDatabaseProvider
import ejarosiewicz.com.eventreminder.domain.converter.EventConverter
import ejarosiewicz.com.eventreminder.domain.data.EventRepository
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider


fun databaseModule(applicationContext: Context) = Kodein.Module("db", false) {

    bind<EventConverter>() with provider { DefaultEventConverter() }

    bind<EventsDatabaseProvider>() with provider {
        DefaultEventDatabaseProvider(applicationContext)
    }

    bind<EventDAO>() with provider {
        instance<EventsDatabaseProvider>().getDao()
    }

    bind<EventRepository>() with provider {
        RoomEventRepository(
                dao = instance(),
                eventConverter = instance()
        )
    }

}