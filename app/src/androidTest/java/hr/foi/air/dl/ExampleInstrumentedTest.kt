package hr.foi.air.dl

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.microsoft.appcenter.espresso.Factory
import com.microsoft.appcenter.espresso.ReportHelper
import org.junit.After

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Rule

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    //for App center report
    @get:Rule
    public var reportHelper: ReportHelper = Factory.getReportHelper()


    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("hr.foi.air.dl22", appContext.packageName)
    }

    @Test
    fun scenario() {
        //perform click action on element with content description: Open drawer - open menu
        Espresso.onView(ViewMatchers.withContentDescription("Open drawer")).perform(
            ViewActions.click());

        //check if element with text About app is displayed
        Espresso.onView(ViewMatchers.withText("About app")).check(
            ViewAssertions.matches(ViewMatchers.isDisplayed()));

        //perform click action on menu about element
        Espresso.onView(ViewMatchers.withId(R.id.menu_about)).perform(
            ViewActions.click());

        //perform click action on element with content description: Open drawer - close menu
        Espresso.onView(ViewMatchers.withContentDescription("Open drawer")).perform(
            ViewActions.click());
    }

    @Test
    fun scenario1() {
        //perform click on Super Nova element
        Espresso.onView(ViewMatchers.withText("Super Nova")).perform(ViewActions.click())
        //Espresso.onView(ViewMatchers.withId(R.id.textInput)).perform(ViewActions.typeText("Nesto"))
    }

    //App center label for stopping app
    @After
    fun tearDown() {
        reportHelper.label("Stopping App")
    }



}