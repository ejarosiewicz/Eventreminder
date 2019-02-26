package ejarosiewicz.com.eventreminder.presentation.main

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import ejarosiewicz.com.eventreminder.presentation.navigator.Navigator
import org.junit.Test

class MainViewModelTest{

    private val mockNavigator: Navigator = mock()

    private val systemUnderTest = MainViewModel(mockNavigator)

    @Test
    fun `Should go to the Add event screen after invoking action`(){
        systemUnderTest.goToAddScreen()

        verify(mockNavigator).goToAddScreen()
    }
}