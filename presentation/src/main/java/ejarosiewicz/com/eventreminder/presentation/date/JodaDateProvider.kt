package ejarosiewicz.com.eventreminder.presentation.date

import org.joda.time.DateTime

class JodaDateProvider : ActualDateProvider {

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


    override fun provideTimestamp(year: Int, month: Int, day: Int, hour: Int, minute: Int): Long =
            dateTime
                    .withDate(year, month, day)
                    .withTime(hour, minute, NO_SECONDS, NO_MILIS)
                    .millis

    companion object {
        const val NO_SECONDS = 0
        const val NO_MILIS = 0
    }
}