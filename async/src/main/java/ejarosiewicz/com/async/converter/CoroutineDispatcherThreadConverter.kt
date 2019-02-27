package ejarosiewicz.com.async.converter

import ejarosiewicz.com.async.Thread
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class CoroutineDispatcherThreadConverter : ThreadConverter<CoroutineDispatcher> {

    override fun convert(thread: Thread): CoroutineDispatcher =
            when (thread) {
                Thread.IO -> Dispatchers.IO
                Thread.MAIN -> Dispatchers.Main
            }
}