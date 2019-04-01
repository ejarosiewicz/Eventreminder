package ejarosiewicz.com.eventreminder.presentation.main

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView

class EventHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    var name: TextView = itemView.findViewById(android.R.id.text1)

}
