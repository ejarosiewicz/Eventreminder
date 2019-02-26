package ejarosiewicz.com.async.converter

import ejarosiewicz.com.eventreminder.domain.async.Scheduler
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class CoroutineDispatcherThreadConverter : ThreadConverter<CoroutineDispatcher> {

    override fun convert(thread: Scheduler.Thread): CoroutineDispatcher =
            when (thread) {
                Scheduler.Thread.IO -> Dispatchers.IO
                Scheduler.Thread.MAIN -> Dispatchers.Main
            }
}