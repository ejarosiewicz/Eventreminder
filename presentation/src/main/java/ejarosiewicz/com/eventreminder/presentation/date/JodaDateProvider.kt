package ejarosiewicz.com.eventreminder.presentation.date

import org.joda.time.DateTime

class JodaDateProvider: ActualDateProvider {

    private val dateTime = DateTime()

    override val year: Int
        get() = dateTime.monthOfYear

    override val month: Int
        get() = dateTime.monthOfYear

    override val day: Int
        get() = dateTime.dayOfMonth

    override val hour: Int
        get() = dateTime.hourOfDay

    override val minute: Int
        get() = dateTime.minuteOfHour
}