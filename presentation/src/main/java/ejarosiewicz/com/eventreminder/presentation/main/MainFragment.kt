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
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.fragment_main.*
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.support.closestKodein
import org.kodein.di.generic.instance
import android.support.v7.widget.LinearLayoutManager
import ejarosiewicz.com.eventreminder.presentation.di.fragmentModule


class MainFragment: Fragment(), KodeinAware {

    private val parentKodein by closestKodein()

    override val kodein = Kodein.lazy {
        extend(parentKodein)
        import(fragmentModule(this@MainFragment))
        import(mainFragmentModule())
    }

    private val viewModelFactory: ViewModelProvider.Factory by instance()

    private lateinit var viewModel: MainViewModel

    private var  eventsAdapter = EventsAdapter()

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
        eventsAdapter.onDeleteListener = {eventId, name -> }
        eventList.adapter = eventsAdapter
        val linearLayoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        eventList.layoutManager = linearLayoutManager
        viewModel.stateData.observe(this, Observer{state -> onStateReceived(state)} )
    }

    private fun onStateReceived(stateHolder: MainStateHolder?) {
        when (stateHolder?.state){
            MainState.EVENTS_LOADED -> fetchEvents(stateHolder.events)
        }
    }

    private fun fetchEvents(events: List<Event>) {
        eventsAdapter.items = events
        eventsAdapter.notifyDataSetChanged()
    }

    override fun onResume() {
        super.onResume()
        viewModel.loadEvents()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        addEvent.setOnClickListener { viewModel.goToAddScreen() }
        viewModel.loadEvents()
    }
}