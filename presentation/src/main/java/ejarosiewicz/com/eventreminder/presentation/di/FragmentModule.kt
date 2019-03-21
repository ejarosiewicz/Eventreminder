package ejarosiewicz.com.eventreminder.presentation.di

import android.support.v4.app.Fragment
import ejarosiewicz.com.eventreminder.presentation.navigator.FragmentNavigator
import ejarosiewicz.com.eventreminder.presentation.navigator.Navigator
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider

fun fragmentModule(fragment: Fragment) = Kodein.Module("chuj", false) {

//    bind<Navigator>("fragment") with provider { FragmentNavigator(
//            fragmentManager = fragment.childFragmentManager,
//            containerProvider = instance())}
}
