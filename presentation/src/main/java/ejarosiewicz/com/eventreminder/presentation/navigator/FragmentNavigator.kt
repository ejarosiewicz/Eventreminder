package ejarosiewicz.com.eventreminder.presentation.navigator

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import ejarosiewicz.com.eventreminder.presentation.R
import ejarosiewicz.com.eventreminder.presentation.add.AddEventFragment
import ejarosiewicz.com.eventreminder.presentation.main.MainFragment

class FragmentNavigator(private val fragmentManager: FragmentManager): Navigator {

    override fun goToMainScreen() {
        goToFragment(MainFragment())
    }

    override fun goToAddScreen() {
        goToFragment(AddEventFragment())
    }

    private fun goToFragment(fragment: Fragment){
        val transaction = fragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentContainer, fragment)
        transaction.commit()
    }
}