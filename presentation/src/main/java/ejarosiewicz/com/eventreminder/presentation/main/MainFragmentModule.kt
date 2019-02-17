package ejarosiewicz.com.eventreminder.presentation.main

import android.arch.lifecycle.ViewModelProvider
import android.support.v4.app.Fragment
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider

fun mainFragmentModule(fragment: Fragment) = Kodein.Module("main", false) {

    bind<ViewModelProvider.Factory>() with provider {
        MainViewModelFactory(navigator = instance())
    }
}
