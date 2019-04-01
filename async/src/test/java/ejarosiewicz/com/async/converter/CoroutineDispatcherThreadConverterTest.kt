package ejarosiewicz.com.async.converter

import assertk.assertThat
import assertk.assertions.isEqualTo
import ejarosiewicz.com.async.Thread
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.junit.Test

import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(Parameterized::class)
class CoroutineDispatcherThreadConverterTest(val threadToConvert: Thread,
                                             val expectedDispatcher: CoroutineDispatcher) {

    private val systemUnderTest = CoroutineDispatcherThreadConverter()

    @Test
    fun `Should convert thread to adequate dispatcher`() {
        val actualDispatcher = systemUnderTest.convert(threadToConvert)
        assertThat(expectedDispatcher).isEqualTo(actualDispatcher)
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun data() : Collection<Array<Any>> {
            return listOf(
                    arrayOf(Thread.IO, Dispatchers.IO),         // First test:  (paramOne = 1, paramTwo = "I")
                    arrayOf(Thread.MAIN, Dispatchers.Main) // Second test: (paramOne = 1999, paramTwo = "MCMXCIX")
            )
        }
    }
}