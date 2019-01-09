package ejarosiewicz.com.eventreminder.presentation.main

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import ejarosiewicz.com.eventreminder.presentation.R
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment: Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        addEvent.setOnClickListener{findNavController().navigate(R.id.add_event)}
    }

}