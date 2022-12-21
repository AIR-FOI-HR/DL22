package hr.foi.air.database

import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import hr.foi.air.database.entities.Discount
import hr.foi.air.database.entities.Store
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

    private lateinit var mainDatabase: MainDatabase

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("hr.foi.air.database.test", appContext.packageName)
    }

    @Before
    fun initDb() {
        //initialize main database - Room inMemoryDatabase
        mainDatabase = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getInstrumentation().targetContext,
            MainDatabase::class.java).build()

        //insert new store in database
        val acmeStore: Store = Store()
        acmeStore.name = "ACME store"
        mainDatabase.getDao().insertStores(acmeStore)
    }

    @Test
    fun insertDiscounts() {
        //insert new discounts in In-MemoryDatabase
        val discount: Discount = Discount()
        discount.name = "Najveci popust"
        discount.discountValue = 50
        mainDatabase.getDao().insertDiscounts(discount)
        val discounts = mainDatabase.getDao().getAllDiscounts()
        //check if list is not empty
        assert(discounts.isNotEmpty())
    }

    @Test
    fun getStoreByName() {
        //check if list of all stores by name from In-memory database is not empty
        assert(mainDatabase.getDao().getAllStoresByName("ACME store").isNotEmpty())
    }

    @Test
    fun getDiscountsByStoreId() {
        //insert discount to In-memory database
        val discount: Discount = Discount()
        discount.name = "Najveci popust"
        discount.discountValue = 50
        discount.storeId = 1
        mainDatabase.getDao().insertDiscounts(discount)
        val discountsFinal = mainDatabase.getDao().getAllDiscountsByStore(1)

        //check if list od discounts from In-memory database is not empty
        assert(discountsFinal.isNotEmpty())
    }

    @After
    fun closeDb() {
        mainDatabase.close()
    }

}