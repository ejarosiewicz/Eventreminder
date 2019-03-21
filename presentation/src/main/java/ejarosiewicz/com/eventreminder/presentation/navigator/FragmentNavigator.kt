package ejarosiewicz.com.eventreminder.presentation.navigator

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import ejarosiewicz.com.eventreminder.presentation.add.AddEventFragment
import ejarosiewicz.com.eventreminder.presentation.main.MainFragment

class FragmentNavigator(private val fragmentManager: FragmentManager,
                        private val containerProvider: ContainerProvider) : Navigator {


    override fun goToMainScreen() {
        goToFragment(MainFragment())
    }

    override fun goToAddScreen() {
        goToFragment(AddEventFragment())
    }

    private fun goToFragment(fragment: Fragment) =
            fragmentManager.beginTransaction()
                    .add(containerProvider.containerId, fragment)
                    .addToBackStack(null)
                    .commit()

    override fun goBack() {
        fragmentManager.popBackStackImmediate()
    }
}