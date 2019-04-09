package ejarosiewicz.com.eventreminder.presentation.main

import android.app.AlertDialog
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ejarosiewicz.com.eventreminder.domain.entity.Event
import ejarosiewicz.com.eventreminder.presentation.R
import ejarosiewicz.com.eventreminder.presentation.di.fragmentModule
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.fragment_main.*
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.support.closestKodein
import org.kodein.di.generic.instance


class MainFragment : Fragment(), KodeinAware {

    private val parentKodein by closestKodein()

    override val kodein = Kodein.lazy {
        extend(parentKodein)
        import(fragmentModule(this@MainFragment))
        import(mainFragmentModule())
    }

    private val viewModelFactory: ViewModelProvider.Factory by instance()

    private lateinit var viewModel: MainViewModel

    private var eventsAdapter = EventsAdapter { eventId, eventName ->
        openDeleteEventsDialog(eventId, eventName)
    }

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
        eventList.adapter = eventsAdapter
        val linearLayoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        eventList.layoutManager = linearLayoutManager
        viewModel.stateData.observe(this, Observer { state -> onStateReceived(state) })
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        addEvent.setOnClickListener { viewModel.goToAddScreen() }
        viewModel.loadEvents()
    }

    private fun openDeleteEventsDialog(eventId: Long, eventName: String) =
            AlertDialog.Builder(context)
                    .setMessage(context?.getString(R.string.want_to_remove_event, eventName))
                    .setPositiveButton(R.string.yes) { dialog, _ ->
                        viewModel.deleteEvent(eventId)
                        dialog.dismiss()
                    }
                    .setNegativeButton(R.string.no) { dialog, _ ->
                        dialog.dismiss()
                    }
                    .show()


    private fun onStateReceived(stateHolder: MainStateHolder?) {
        when (stateHolder?.state) {
            MainState.EVENTS_LOADED -> fetchEvents(stateHolder.events)
            MainState.EVENT_DELETED -> onDeleteEvent(stateHolder.events)
        }
    }

    private fun fetchEvents(events: List<Event>) {
        eventsAdapter.items = events
        eventsAdapter.notifyDataSetChanged()
    }

    private fun onDeleteEvent(events: List<Event>) {
        //todo toast
        fetchEvents(events)
    }

    override fun onResume() {
        super.onResume()
        viewModel.loadEvents()
    }

}