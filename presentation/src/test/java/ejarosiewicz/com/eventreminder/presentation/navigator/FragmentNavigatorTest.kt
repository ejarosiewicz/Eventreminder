package ejarosiewicz.com.eventreminder.presentation.navigator

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import com.nhaarman.mockitokotlin2.*
import org.junit.Test

class FragmentNavigatorTest {

    private val mockContainerProvider: ContainerProvider = mock()

    private val mockFragmentTransaction: FragmentTransaction = mock {
        on { add(any<Int>(), any()) } doReturn (it)
        on { commit() } doReturn (1)
    }
    private val mockFragmentManager: FragmentManager = mock {
        on { beginTransaction() } doReturn (mockFragmentTransaction)
    }

    private val systemUnderTest = FragmentNavigator(mockFragmentManager, mockContainerProvider)

    @Test
    fun goToMainScreen() {
        systemUnderTest.goToMainScreen()

        verifyFragmentTransaction("MainFragment")
    }

    @Test
    fun goToAddScreen() {
        systemUnderTest.goToAddScreen()

        verifyFragmentTransaction("AddEventFragment")
    }

    private fun verifyFragmentTransaction(expectedFragmentName: String) {
        val fragmentCaptor = argumentCaptor<Fragment>()

        verify(mockFragmentManager).beginTransaction()
        verify(mockFragmentTransaction).add(eq(mockContainerProvider.containerId), fragmentCaptor.capture())
        verify(mockFragmentTransaction).commit()


        val actualFragmentClass = fragmentCaptor.firstValue::class
        val actualFragmentName = actualFragmentClass.java.simpleName

        assert(expectedFragmentName == actualFragmentName)
    }
}