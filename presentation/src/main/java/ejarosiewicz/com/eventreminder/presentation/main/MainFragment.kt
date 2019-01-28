package ejarosiewicz.com.eventreminder.presentation.main

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ejarosiewicz.com.eventreminder.presentation.R
import ejarosiewicz.com.eventreminder.presentation.di.fragmentModule
import ejarosiewicz.com.eventreminder.presentation.navigator.Navigator
import kotlinx.android.synthetic.main.fragment_main.*
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.support.closestKodein
import org.kodein.di.generic.instance

class MainFragment: Fragment(), KodeinAware {

    companion object {
        const val NAME = "Main"
    }

    private val parentKodein by closestKodein()

    override val kodein = Kodein.lazy {
        import(fragmentModule(this@MainFragment))
        extend(parentKodein)
    }

    private val navigator: Navigator by instance()

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        addEvent.setOnClickListener { navigator.goToAddScreen()}
    }
}