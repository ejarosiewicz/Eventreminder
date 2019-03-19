package ejarosiewicz.com.eventreminder.presentation.add

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ejarosiewicz.com.eventreminder.presentation.R
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.support.closestKodein
import org.kodein.di.generic.instance
import android.databinding.DataBindingUtil



class AddEventFragment : Fragment(), KodeinAware {

    companion object {
        const val NAME = "Add"
    }

    private val parentKodein by closestKodein()

    override val kodein = Kodein.lazy {
        import(addEventFragmentModule())
        extend(parentKodein)
    }

    private val viewModelFactory: ViewModelProvider.Factory by instance()

    private lateinit var viewModel: AddEventViewModel

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

}