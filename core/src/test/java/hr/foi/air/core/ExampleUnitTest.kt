package hr.foi.air.core

import org.junit.Test

import org.junit.Assert.*
import java.text.SimpleDateFormat
import java.util.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun addition_isNotCorrect() {
        assertNotEquals(4, 2 + 5)
    }

    @Test
    fun checkDate() {
        val sdf = SimpleDateFormat("dd/MM/yyyy")
        //format date from java Util class
        val currentDate = sdf.format(Date())
        val date = "08/12/2021"
        //compare two dates
        assertEquals(currentDate, date);
    }

    @Test
    fun checkList() {
        val list = listOf("a1", "a2")
        //check if list contains element
        assertTrue(list.contains("a1"))
    }

    @Test
    fun checkListSize() {
        val list = listOf("a1", "a2")
        //check array size
        assertEquals(2, list.size)
    }

    @Test
    fun checkArray() {
        val arr : IntArray = intArrayOf(10, 20, 30, 40, 50)
        //check if two arrays are the same
        assertArrayEquals(intArrayOf(10, 20, 30, 40, 50), arr)
    }

}