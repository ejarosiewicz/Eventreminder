package ejarosiewicz.com.eventreminder.presentation.navigator

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.support.v4.app.Fragment
import ejarosiewicz.com.eventreminder.presentation.MainActivity
import ejarosiewicz.com.eventreminder.presentation.add.AddEventActivity
import ejarosiewicz.com.eventreminder.presentation.add.AddEventFragment

class ActivityNavigator(private val context: Context) : Navigator {


    override fun goToMainScreen() =
        startActivity(MainActivity::class.java)


    override fun goToAddScreen() =
        startActivity(AddEventActivity::class.java)

    override fun goBack() {

    }

    private fun startActivity(activityClass: Class<out Activity>) {
        val intent = Intent(context, activityClass)
        context.startActivity(intent)
    }
}