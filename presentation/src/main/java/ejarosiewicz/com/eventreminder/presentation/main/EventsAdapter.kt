package ejarosiewicz.com.eventreminder.presentation.main

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import ejarosiewicz.com.eventreminder.domain.entity.Event

class EventsAdapter(var onDeleteListener: (Long, String) -> Unit) : RecyclerView.Adapter<EventHolder>() {

    var items: List<Event> = emptyList()

    override fun getItemCount(): Int = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventHolder {
        val inflater = LayoutInflater.from(parent.context)
        val textView = inflater.inflate(android.R.layout.simple_spinner_item, parent, false)

        return EventHolder(textView)
    }

    override fun onBindViewHolder(holder: EventHolder, position: Int) {
        holder.name.text = items[position].name
        holder.name.setOnClickListener { onDeleteListener(items[position].id, items[position].name) }
    }
}