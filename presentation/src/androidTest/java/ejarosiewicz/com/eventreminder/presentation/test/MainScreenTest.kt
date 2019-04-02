package ejarosiewicz.com.eventreminder.presentation.test

import android.app.Activity
import android.content.Intent
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.*
import android.support.test.espresso.assertion.ViewAssertions.doesNotExist
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.filters.LargeTest
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import ejarosiewicz.com.eventreminder.presentation.MainActivity
import ejarosiewicz.com.eventreminder.presentation.R
import junit.framework.Assert.assertNotNull
import junit.framework.Assert.assertNull
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
@LargeTest
class MainScreenTest  {

    @get:Rule
    val activityTestRule = ActivityTestRule(MainActivity::class.java, true, true)

    var activity: Activity? = null

    @Before
    fun setup() {
        activityTestRule.launchActivity(Intent())
        activity = activityTestRule.activity
    }

    @After
    fun tearDown() {
        activityTestRule.finishActivity()
    }

    @Test
    fun userIsInTheMainScreen() {
        assertNotNull(activity)
    }

    @Test
    fun userAddsNewEvent() {
        val eventName = "Transmission"

        onView(withId(R.id.addEvent))
                .perform(click())
        closeSoftKeyboard()
        onView(withId(R.id.name))
                .perform(clearText(),typeText(eventName), closeSoftKeyboard())
        onView(withId(R.id.addButton))
                .perform(click())

        onView(withId(R.id.eventList))
                .check(matches(withText(eventName)))
    }

    @Test
    fun userDeletesEvent() {
        onView(withId(android.R.id.text1))
                .perform(click())
        onView(withText("Yes"))
                .perform(click())

        onView(withId(android.R.id.text1))
                .check(doesNotExist())
    }
}