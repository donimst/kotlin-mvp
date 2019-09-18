package com.hokagelab.doni.kadefootball


import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.Espresso.pressBack
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.action.ViewActions.swipeDown
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.filters.LargeTest
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import org.hamcrest.Matchers.not
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class MatchEventTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    private fun delay() {
        try {
            Thread.sleep(2000)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
    }

    @Test
    fun matchEventTest() {
        delay()
        onView(withId(R.id.recView_last)).check(matches(isDisplayed()))

        delay()
        onView(withId(R.id.recView_last)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(14))

        delay()
        onView(withId(R.id.recView_last))
            .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(14, click()))

        delay()
        onView(withId(R.id.homeBadge)).check(matches(isDisplayed()))
        onView(withId(R.id.awayBadge)).check(matches(isDisplayed()))

        delay()
        onView(withId(R.id.add_to_favorite)).perform(click())

        delay()
        pressBack()

        delay()
        onView(withId(R.id.favMenu)).perform(click())

        delay()
        onView(withId(R.id.recView_favMatch)).check(matches(isDisplayed()))

        delay()
        onView(withId(R.id.recView_favMatch)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(0))

        delay()
        onView(withId(R.id.recView_favMatch))
            .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))

        delay()
        onView(withId(R.id.homeBadge)).check(matches(isDisplayed()))
        onView(withId(R.id.awayBadge)).check(matches(isDisplayed()))

        delay()
        onView(withId(R.id.add_to_favorite)).perform(click())

        delay()
        pressBack()

        delay()
        onView(withId(R.id.refresh_favMatch)).perform(swipeDown())

        delay()
        onView(withId(R.id.empty_text)).check(matches(isDisplayed()))
        onView(withId(R.id.recView_favMatch)).check(matches(not(isDisplayed())))

        delay()
    }
}
