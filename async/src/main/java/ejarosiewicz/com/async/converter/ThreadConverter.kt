package ejarosiewicz.com.async.converter

import ejarosiewicz.com.async.Thread

interface ThreadConverter<T> {

    fun convert(thread: Thread): T
}