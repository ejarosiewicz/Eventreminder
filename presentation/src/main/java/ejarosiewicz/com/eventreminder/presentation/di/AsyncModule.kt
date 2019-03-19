package ejarosiewicz.com.eventreminder.presentation.di

import ejarosiewicz.com.async.CoroutineScheduler
import ejarosiewicz.com.async.Scheduler
import ejarosiewicz.com.async.converter.CoroutineDispatcherThreadConverter
import ejarosiewicz.com.async.converter.ThreadConverter
import kotlinx.coroutines.CoroutineDispatcher
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider

fun asyncModule() = Kodein.Module("async", false) {

    bind<Scheduler>() with provider { CoroutineScheduler(instance()) }

    bind<ThreadConverter<CoroutineDispatcher>>() with provider { CoroutineDispatcherThreadConverter() }

}