package ejarosiewicz.com.eventreminder.presentation

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import ejarosiewicz.com.eventreminder.presentation.di.activityModule
import ejarosiewicz.com.eventreminder.presentation.navigator.Navigator
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.generic.instance

class MainActivity : AppCompatActivity(), KodeinAware {

    override val kodein = Kodein.lazy {
        import(activityModule(this@MainActivity))
    }

    private val navigator: Navigator by instance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navigator.goToMainScreen()
    }
}
