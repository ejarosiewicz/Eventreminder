package ejarosiewicz.com.eventreminder.presentation.main

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ejarosiewicz.com.eventreminder.domain.entity.Event
import ejarosiewicz.com.eventreminder.presentation.R
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
        import(mainFragmentModule())
        extend(parentKodein)
    }

    private val viewModelFactory: ViewModelProvider.Factory by instance()

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {

        viewModel = ViewModelProviders
                .of(this, viewModelFactory)
                .get(MainViewModel::class.java)

        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.stateData.observe(this, Observer{state -> onStateReceived(state)} )
    }

    private fun onStateReceived(stateHolder: MainStateHolder?) {
        when (stateHolder?.state){
            MainState.EVENTS_LOADED -> fetchEvents(stateHolder.events)
        }
    }

    private fun fetchEvents(events: List<Event>) {

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        addEvent.setOnClickListener { viewModel.goToAddScreen()}
    }
}