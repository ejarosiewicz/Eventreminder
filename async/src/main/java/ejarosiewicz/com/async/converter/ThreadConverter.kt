package ejarosiewicz.com.async.converter

import ejarosiewicz.com.eventreminder.domain.async.Scheduler

interface ThreadConverter<T> {

    fun convert(thread: Scheduler.Thread): T
}