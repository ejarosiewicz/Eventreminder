package ejarosiewicz.com.eventreminder.presentation.navigator

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import com.nhaarman.mockitokotlin2.*
import ejarosiewicz.com.eventreminder.presentation.main.MainFragment
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

class FragmentNavigatorTest {

    private val mockFragmentTransaction: FragmentTransaction = mock {
        on { add(any<Int>(), any()) } doReturn (it)
        on { commit() } doReturn (1)
    }
    private val mockContainerProvider: ContainerProvider = mock()
    private val mockFragmentManager: FragmentManager = mock {
        on { beginTransaction() } doReturn (mockFragmentTransaction)
    }

    private val systemUnderTest = FragmentNavigator(mockFragmentManager, mockContainerProvider)

    @Test
    fun goToMainScreen() {
        systemUnderTest.goToMainScreen()

        verifyFragmentTransaction(MainFragment())
    }

    @Test
    fun goToAddScreen() {
    }

    private fun verifyFragmentTransaction(fragmentName: String) {
        val fragmentCaptor = argumentCaptor<Fragment>()

        verify(mockFragmentManager).beginTransaction()
        verify(mockFragmentTransaction).add(eq(mockContainerProvider.containerId), fragmentCaptor.capture())
        verify(mockFragmentTransaction).commit()


        val actualClass = fragmentCaptor.firstValue::class
        val actualname = actualClass.java.simpleName

        assert(expectedName == actualName)
    }
}