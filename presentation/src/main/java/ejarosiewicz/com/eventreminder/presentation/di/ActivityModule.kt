package ejarosiewicz.com.eventreminder.presentation.di

import android.support.v7.app.AppCompatActivity
import ejarosiewicz.com.eventreminder.presentation.navigator.ContainerProvider
import ejarosiewicz.com.eventreminder.presentation.navigator.DefaultContainerProvider
import ejarosiewicz.com.eventreminder.presentation.navigator.FragmentNavigator
import ejarosiewicz.com.eventreminder.presentation.navigator.Navigator
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider

fun activityModule(activity: AppCompatActivity) = Kodein.Module("cipa", false) {
    bind<ContainerProvider>() with provider { DefaultContainerProvider()}

    bind<Navigator>() with provider { FragmentNavigator(
            fragmentManager = activity.supportFragmentManager,
            containerProvider = instance())}
    //bind<FragmentManagerFacade>() with provider { DefaultFragmentManagerFacade(activity.supportFragmentManager)}
}
