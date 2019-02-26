package ejarosiewicz.com.async

import ejarosiewicz.com.eventreminder.domain.async.AsyncExecutor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class CoroutineAsyncExecutor: AsyncExecutor{

    override fun <T> execute(operation: () -> T, onSuccess: (T) -> Unit) {
        GlobalScope.launch(Dispatchers.IO) {
            doOperation(operation, onSuccess)
        }
    }

    private fun <T> doOperation(operation: () -> T, onSuccess: (T) -> Unit) {
        val result = operation()
        GlobalScope.launch(Dispatchers.Main) {
            onSuccess(result)
        }
    }


}