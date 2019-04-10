package ejarosiewicz.com.eventreminder.presentation.add

import android.arch.lifecycle.ViewModelProvider
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider

fun addEventFragmentModule() = Kodein.Module("add", false) {

    bind<ViewModelProvider.Factory>() with provider {
        AddEventViewModelFactory(
                addEventUseCase = instance(),
                scheduler = instance(),
                actualDateProvider = instance())
    }
}
