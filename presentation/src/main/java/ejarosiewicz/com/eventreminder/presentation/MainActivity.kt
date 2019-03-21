package ejarosiewicz.com.eventreminder.presentation

import android.os.Bundle
import android.support.v4.app.FragmentActivity;
import ejarosiewicz.com.eventreminder.presentation.add.AddEventFragment
import ejarosiewicz.com.eventreminder.presentation.di.activityModule
import ejarosiewicz.com.eventreminder.presentation.di.asyncModule
import ejarosiewicz.com.eventreminder.presentation.di.databaseModule
import ejarosiewicz.com.eventreminder.presentation.di.domainModule
import ejarosiewicz.com.eventreminder.presentation.main.MainFragment
import ejarosiewicz.com.eventreminder.presentation.navigator.Navigator
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.generic.instance

class MainActivity : FragmentActivity(), KodeinAware {

    override val kodein = Kodein.lazy {
        import(activityModule(this@MainActivity))
        import(databaseModule(this@MainActivity.applicationContext))
        import(asyncModule())
        import(domainModule())
    }



    private val navigator: Navigator by instance("activity")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
                .add(R.id.fragmentContainer, MainFragment())
                .commit()
    }
}
