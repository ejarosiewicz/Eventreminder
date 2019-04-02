package ejarosiewicz.com.eventreminder.presentation.main

import android.arch.lifecycle.ViewModelProvider
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider

fun mainFragmentModule() = Kodein.Module("main", false) {

    bind<ViewModelProvider.Factory>() with provider {
        MainViewModelFactory(navigator = instance(),
                scheduler = instance(),
                readEventsUseCase = instance())
    }
}
