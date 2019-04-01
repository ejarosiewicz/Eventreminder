package ejarosiewicz.com.async

import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import ejarosiewicz.com.async.converter.ThreadConverter
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.runBlocking
import org.junit.Test

class CoroutineSchedulerTest {

    private val mockThreadConverter: ThreadConverter<CoroutineDispatcher> = mock()

    private val mockOperation: () -> EmptyResult = mock {
        on { invoke() } doReturn (EmptyResult())
    }
    private val mockOnSuccess: (EmptyResult) -> Unit = mock()

    private val systemUnderTest = CoroutineScheduler(mockThreadConverter)

    @Test
    fun `Should execute operation with success`() {
        runBlocking {
        //    systemUnderTest.execute(mockOperation, mockOnSuccess)
        }
    }

    private class EmptyResult

}