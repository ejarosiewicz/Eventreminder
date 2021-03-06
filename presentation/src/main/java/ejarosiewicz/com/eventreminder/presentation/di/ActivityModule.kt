package ejarosiewicz.com.eventreminder.presentation.di

import android.support.v4.app.FragmentActivity
import ejarosiewicz.com.eventreminder.presentation.navigator.ActivityNavigator
import ejarosiewicz.com.eventreminder.presentation.navigator.ContainerProvider
import ejarosiewicz.com.eventreminder.presentation.navigator.DefaultContainerProvider
import ejarosiewicz.com.eventreminder.presentation.navigator.Navigator
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.provider

fun activityModule(activity: FragmentActivity) = Kodein.Module("cipa", true) {

    bind<ContainerProvider>() with provider { DefaultContainerProvider()}

    bind<Navigator>() with provider { ActivityNavigator(context = activity)}
}
