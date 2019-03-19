package ejarosiewicz.com.eventreminder.presentation.main

import android.support.v7.widget.RecyclerView
import android.view.View.inflate
import android.view.ViewGroup
import android.widget.TextView
import ejarosiewicz.com.eventreminder.domain.entity.Event

class EventsAdapter : RecyclerView.Adapter<EventHolder>() {

    var items: List<Event> = emptyList()
        set(events) {
            field = events
            notifyDataSetChanged()
        }

    override fun getItemCount(): Int = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventHolder {
        val textView = inflate(parent.context, android.R.layout.test_list_item, parent) as TextView

        return EventHolder(textView)
    }

    override fun onBindViewHolder(holder: EventHolder, position: Int) {
        holder.name.text = items[position].name
    }
}