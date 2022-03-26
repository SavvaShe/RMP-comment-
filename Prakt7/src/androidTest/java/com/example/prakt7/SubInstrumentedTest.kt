package com.example.prakt7


import android.app.Activity
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.RootMatchers
import androidx.test.espresso.matcher.RootMatchers.withDecorView
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.CoreMatchers
import org.hamcrest.Matchers.not

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Rule
import kotlin.reflect.typeOf

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class) //юнит тесты
class SubInstrumentedTest {
    @get:Rule
    val ar = ActivityScenarioRule(MainActivity::class.java)
    @Test//тест 1
    fun division1(){
         View(withId(R.id.editTextNumberA)).perform(typeText("0"))
        onView(withId(R.id.editTextNumberB)).perform(typeText("1"), closeSoftKeyboard())
        onView(withId(R.id.buttonCalculate)).perform(click())
        onView(withId(R.id.textViewResult)).check(matches(withText("Нет решений")))
    }
    @Test
    fun division2(){
        onView(withId(R.id.editTextNumberA)).perform(typeText("2"))
        onView(withId(R.id.editTextNumberB)).perform(typeText("6"), closeSoftKeyboard())
        onView(withId(R.id.buttonCalculate)).perform(click())
        onView(withId(R.id.textViewResult)).check(matches(withText("(-∞,-3.0]")))
    }
    @Test
    fun division3(){
        onView(withId(R.id.editTextNumberA)).perform(typeText("-2"))
        onView(withId(R.id.editTextNumberB)).perform(typeText("6"), closeSoftKeyboard())
        onView(withId(R.id.buttonCalculate)).perform(click())
        onView(withId(R.id.textViewResult)).check(matches(withText("[3.0,+∞)")))
    }
    @Test
    fun division4(){
        onView(withId(R.id.editTextNumberA)).perform(typeText("-2"))
        onView(withId(R.id.editTextNumberB)).perform(typeText("-6"), closeSoftKeyboard())
        onView(withId(R.id.buttonCalculate)).perform(click())
        onView(withId(R.id.textViewResult)).check(matches(withText("[-3.0,+∞)")))
    }
    @Test
    fun division5(){
        onView(withId(R.id.editTextNumberA)).perform(typeText("2"))
        onView(withId(R.id.editTextNumberB)).perform(typeText("-6"), closeSoftKeyboard())
        onView(withId(R.id.buttonCalculate)).perform(click())
        onView(withId(R.id.textViewResult)).check(matches(withText("(-∞,3.0]")))
    }
    @Test
    fun division6(){
        onView(withId(R.id.editTextNumberA)).perform(typeText("0"))
        onView(withId(R.id.editTextNumberB)).perform(typeText("0"), closeSoftKeyboard())
        onView(withId(R.id.buttonCalculate)).perform(click())
        onView(withId(R.id.textViewResult)).check(matches(withText("(-∞,+∞)")))
    }
    @Test
    fun division7(){
        onView(withId(R.id.editTextNumberA)).perform(typeText("0"))
        onView(withId(R.id.editTextNumberB)).perform(typeText("-6"), closeSoftKeyboard())
        onView(withId(R.id.buttonCalculate)).perform(click())
        onView(withId(R.id.textViewResult)).check(matches(withText("(-∞,+∞)")))
    }
    @Test
    fun division8(){
        onView(withId(R.id.editTextNumberA)).perform(typeText("5"))
        onView(withId(R.id.editTextNumberB)).perform(typeText("0"), closeSoftKeyboard())
        onView(withId(R.id.buttonCalculate)).perform(click())
        onView(withId(R.id.textViewResult)).check(matches(withText("(-∞,0]")))
    }
    @Test
    fun division9(){
        onView(withId(R.id.editTextNumberA)).perform(typeText("-5"))
        onView(withId(R.id.editTextNumberB)).perform(typeText("0"), closeSoftKeyboard())
        onView(withId(R.id.buttonCalculate)).perform(click())
        onView(withId(R.id.textViewResult)).check(matches(withText("[0,+∞)")))
    }
    @Test
    fun toastTestA() {
        onView(withId(R.id.editTextNumberA)).perform(typeText("asd"))
        Espresso.onView(withId(R.id.buttonCalculate)).perform(click())
        var activity:Activity?=null
        ar.scenario.onActivity { activity=it }
        Espresso.onView(withText(R.string.ErrA)).inRoot(
            RootMatchers.withDecorView(
                CoreMatchers.not(
                    CoreMatchers.`is`(
                        activity!!.window.decorView
                            )
                )
            )
        ).check(
            ViewAssertions.matches(
                ViewMatchers.isDisplayed()
            )
        )
    }
    fun toastTestB() {
        onView(withId(R.id.editTextNumberB)).perform(typeText("asd"))
        Espresso.onView(withId(R.id.buttonCalculate)).perform(click())
        var activity:Activity?=null
        ar.scenario.onActivity { activity=it }
        Espresso.onView(withText(R.string.ErrB)).inRoot(
            RootMatchers.withDecorView(
                CoreMatchers.not(
                    CoreMatchers.`is`(
                        activity!!.window.decorView
                    )
                )
            )
        ).check(
            ViewAssertions.matches(
                ViewMatchers.isDisplayed()
            )
        )
    }
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.example.prakt7", appContext.packageName)
    }
}