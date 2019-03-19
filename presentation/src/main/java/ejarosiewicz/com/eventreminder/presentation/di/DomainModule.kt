package ejarosiewicz.com.eventreminder.presentation.di

import ejarosiewicz.com.eventreminder.domain.add.AddEventUseCase
import ejarosiewicz.com.eventreminder.domain.add.DefaultAddEventUseCase
import ejarosiewicz.com.eventreminder.domain.read.DefaultReadEventsUseCase
import ejarosiewicz.com.eventreminder.domain.read.ReadEventsUseCase
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider

fun domainModule() = Kodein.Module("domain", false) {

    bind<ReadEventsUseCase>() with provider { DefaultReadEventsUseCase(instance()) }

    bind<AddEventUseCase>() with provider { DefaultAddEventUseCase(instance()) }

}