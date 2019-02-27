package ejarosiewicz.com.async

import ejarosiewicz.com.async.converter.ThreadConverter
import kotlinx.coroutines.*

class CoroutineScheduler(private val threadConverter: ThreadConverter<CoroutineDispatcher>)
    : Scheduler {

    private var job: Job? = null

    override fun <T> schedule(operation: () -> T,
                              operationThread: Scheduler.Thread,
                              resultThread: Scheduler.Thread,
                              onSuccess: (T) -> Unit) {
        val operationDispatcher = threadConverter.convert(operationThread)
        val resultDispatcher = threadConverter.convert(resultThread)

        job?.cancel()
        job = GlobalScope.launch(operationDispatcher) {
            doOperation(operation, resultDispatcher, onSuccess)
        }
    }

    override fun cancel() {
        job?.cancel()
    }

    private fun <T> doOperation(operation: () -> T,
                                        resultDispatcher: CoroutineDispatcher,
                                        onSuccess: (T) -> Unit) {
        val result = operation()
        GlobalScope.launch(resultDispatcher) {
            onSuccess(result)
        }
    }


}