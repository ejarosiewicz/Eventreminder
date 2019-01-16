package ejarosiewicz.com.eventreminder.presentation.di

import android.app.Activity
import android.support.v7.app.AppCompatActivity
import ejarosiewicz.com.eventreminder.presentation.navigator.FragmentNavigator
import ejarosiewicz.com.eventreminder.presentation.navigator.Navigator
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider

fun activityModule(activity: AppCompatActivity) = Kodein.Module("cipa", false) {
    bind<Navigator>() with provider { FragmentNavigator(activity.supportFragmentManager)}
}
