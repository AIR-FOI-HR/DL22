package hr.foi.air.database.converters

import org.junit.Assert.*

import org.junit.Test
import java.text.SimpleDateFormat
import java.util.*

class DateConverterTest {

    private val dateConverter = DateConverter()

    /*
    test toDate function
     */
    @Test
    fun toDateTest() {
        val sdf = SimpleDateFormat("dd.MM.yyyy")
        //convert System.currentTimeMillis to Date
        val dateConverted = dateConverter.toDate(System.currentTimeMillis())
        //convert it to String in format dd.MM.yyyy
        val formattedDateConverted = sdf.format(dateConverted)
        //format Date() from java util class to String in format dd.MM.yyyy
        val date = sdf.format(Date())
        //compare two days
        assertEquals(date, formattedDateConverted)
    }

}
