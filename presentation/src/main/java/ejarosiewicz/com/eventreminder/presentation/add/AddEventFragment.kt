package ejarosiewicz.com.eventreminder.presentation.add

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ejarosiewicz.com.eventreminder.presentation.R

class AddEventFragment: Fragment() {

    companion object {
        const val NAME = "Add"
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProviders
                .of(this, viewModelFactory)
                .get(AddEventViewModel::class.java)

        return inflater.inflate(R.layout.fragment_add, container, false)
    }

}