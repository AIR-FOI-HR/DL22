package hr.foi.air.ws

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("hr.foi.air.ws.test", appContext.packageName)
    }

    private var server = MockWebServer()

    @Before
    fun setup() {
        //example - mock response
        val response = MockResponse().addHeader("Authorization", "token").setResponseCode(200).setBody("Hello, world!")
        //add response to server
        server.enqueue(response)
        //define server port and start server
        server.start(8081)
    }

    @Test
    fun test() {
        //set api url
        val url = server.url("/")
        //send request and get request body
        val result = OkHttpClient().newCall(Request.Builder().url(url).build())
            .execute().body().toString()
        //check result
        assertEquals("Hello, world!", result);    }


    @After
    fun shutDownServer() {
        //at the end shut down server
        server.shutdown()
    }

}