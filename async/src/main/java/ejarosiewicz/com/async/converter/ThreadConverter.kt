package ejarosiewicz.com.async.converter

import ejarosiewicz.com.async.Scheduler

interface ThreadConverter<T> {

    fun convert(thread: Scheduler.Thread): T
}