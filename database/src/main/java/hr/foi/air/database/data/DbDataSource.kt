package hr.foi.air.database.data

import android.content.Context
import hr.foi.air.core.DataSource
import hr.foi.air.core.DataSourceListener
import hr.foi.air.core.entities.Discount
import hr.foi.air.core.entities.Store
import hr.foi.air.database.DAO
import hr.foi.air.database.MainDatabase

class DbDataSource(context: Context) : DataSource {
    var dao: DAO

    init {
        dao = MainDatabase.getInstance(context).getDao()
    }

    fun getDiscountNames(): List<String> {
        return dao.getDiscountNames()
    }

    private fun getStores() : List<Store>
    {
        return dao.getAllStores()
    }

    private fun getDiscounts() : List<Discount>
    {
        return dao.getAllDiscounts()
    }

    override fun loadData(listener: DataSourceListener, contex: Context) {
        //Unos podataka u bazu, ako je potrebno
        MockData.mockData(contex)

        //Dohvat podataka iz baze
        listener.onDataLoaded(getStores(), getDiscounts())
    }
}