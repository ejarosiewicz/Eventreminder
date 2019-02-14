package ejarosiewicz.com.eventreminder.presentation.main

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
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
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        addEvent.setOnClickListener { viewModel.goToAddScreen()}
    }
}