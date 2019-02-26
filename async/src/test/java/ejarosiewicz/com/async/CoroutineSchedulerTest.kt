package ejarosiewicz.com.async

import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import kotlinx.coroutines.runBlocking
import org.junit.Test

class CoroutineSchedulerTest {

    private val mockOperation: () -> EmptyResult = mock {
        on { invoke() } doReturn (EmptyResult())
    }
    private val mockOnSuccess: (EmptyResult) -> Unit = mock()

    private val systemUnderTest = CoroutineScheduler()

    @Test
    fun `Should execute operation with success`() {
        runBlocking {
        //    systemUnderTest.execute(mockOperation, mockOnSuccess)
        }
    }

    private class EmptyResult

}