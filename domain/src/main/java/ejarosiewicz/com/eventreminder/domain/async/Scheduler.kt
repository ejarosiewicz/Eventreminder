package ejarosiewicz.com.eventreminder.domain.async

interface Scheduler {

    fun<T> schedule(
            operation: () -> T,
            operationThread : Thread,
            resultThread : Thread,
            onSuccess: (T) -> Unit)

    fun cancel()
}