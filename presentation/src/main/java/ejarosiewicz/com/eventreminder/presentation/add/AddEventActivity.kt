package ejarosiewicz.com.eventreminder.presentation.add

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ejarosiewicz.com.eventreminder.presentation.BR
import ejarosiewicz.com.eventreminder.presentation.R
import ejarosiewicz.com.eventreminder.presentation.di.*
import ejarosiewicz.com.eventreminder.presentation.navigator.Navigator
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.support.closestKodein
import org.kodein.di.generic.instance


class AddEventActivity : FragmentActivity(), KodeinAware {

    companion object {
        const val NAME = "Add"
    }

    override val kodein = Kodein.lazy {
        import(activityModule(this@AddEventActivity))
        import(databaseModule(this@AddEventActivity.applicationContext))
        import(asyncModule())
        import(domainModule())
    }

    private val navigator: Navigator by instance("activity")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
                .add(R.id.fragmentContainer, AddEventFragment())
                .commit()
    }

}