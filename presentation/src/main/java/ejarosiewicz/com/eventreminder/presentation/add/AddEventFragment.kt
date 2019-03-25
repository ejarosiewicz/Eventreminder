package ejarosiewicz.com.eventreminder.presentation.add

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ejarosiewicz.com.eventreminder.presentation.BR
import ejarosiewicz.com.eventreminder.presentation.R
import ejarosiewicz.com.eventreminder.presentation.di.fragmentModule
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.support.closestKodein
import org.kodein.di.generic.instance


class AddEventFragment : Fragment(), KodeinAware {

    companion object {
        const val NAME = "Add"
    }

    private val parentKodein by closestKodein()

    override val kodein = Kodein.lazy {
        extend(parentKodein)
        import(fragmentModule(this@AddEventFragment))
        import(addEventFragmentModule())
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

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        activity?.let {
            val viewDataBinding = DataBindingUtil.setContentView<ViewDataBinding>(it, R.layout.fragment_add)
            viewDataBinding.setVariable(BR.viewmodel, viewModel)
        }
        viewModel.stateData.observe(this, Observer{state -> onStateReceived(state)} )
    }

    private fun onStateReceived(state: AddState?) {
        when (state){
            AddState.EVENT_ADDED -> onEventAdded()
        }
    }

    private fun onEventAdded() {
        activity?.finish()
    }

}