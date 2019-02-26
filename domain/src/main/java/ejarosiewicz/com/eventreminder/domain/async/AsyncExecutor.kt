package ejarosiewicz.com.eventreminder.domain.async

interface AsyncExecutor {

    fun<T> execute(operation: () -> T, onSuccess: (T) -> Unit)
}