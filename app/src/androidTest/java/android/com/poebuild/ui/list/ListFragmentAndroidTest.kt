package android.com.poebuild.ui.list

import android.com.poebuild.R
import android.os.Bundle
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Test
import org.junit.runner.RunWith


//TODO (R): Take a look why it's not working the usual uni test if we implement mockito android (possible issues with mockito-inline and mockito-kotlin)
@RunWith(AndroidJUnit4::class)
class ListFragmentAndroidTest {

    @Test
    fun testEventFragment() {
        val fragmentArgs = Bundle().apply {
            putInt("selectedListItem", 0)
        }
        val scenario = launchFragmentInContainer<ListFragment>(
            fragmentArgs)
        onView(withId(R.id.text)).check(matches(withText("Hello World!")))
    }
}