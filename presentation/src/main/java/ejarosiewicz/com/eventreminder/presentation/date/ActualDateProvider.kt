package ejarosiewicz.com.eventreminder.presentation.date

interface ActualDateProvider {

    val year: Int
    val month: Int
    val day: Int
    val hour: Int
    val minute: Int

    fun provideTimestamp(year: Int, month: Int, day: Int, hour: Int, minute: Int): Long
}