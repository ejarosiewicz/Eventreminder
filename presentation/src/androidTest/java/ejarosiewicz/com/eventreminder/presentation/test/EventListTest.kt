package ejarosiewicz.com.eventreminder.presentation.test

import android.app.Activity
import android.content.Intent
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.filters.LargeTest
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import ejarosiewicz.com.eventreminder.presentation.MainActivity
import ejarosiewicz.com.eventreminder.presentation.R
import junit.framework.Assert.assertNotNull
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
@LargeTest
class EventListTest  {

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
        onView(withId(R.id.add_event))
                .perform(click())
        assertNotNull(activity)
    }
}