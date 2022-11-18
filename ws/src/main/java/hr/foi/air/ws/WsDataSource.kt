package hr.foi.air.ws

import android.content.Context
import hr.foi.air.core.DataSource
import hr.foi.air.core.DataSourceListener
import hr.foi.air.core.entities.Discount
import hr.foi.air.core.entities.Store
import hr.foi.air.ws.handlers.MyWebserviceHandler

class WsDataSource : DataSource {
    var stores: List<Store> = ArrayList()
    var discounts: List<Discount> = ArrayList()
    var storesArrived = false
    var discountsArrived = false
    lateinit var listener: DataSourceListener

    override fun loadData(listener: DataSourceListener, contex: Context) {
        this.listener = listener

        val storesCaller = MyWebserviceCaller()
        val discountsCaller = MyWebserviceCaller()

        storesCaller.getAllStores("getAll", storesHandler)
        discountsCaller.getAllDiscounts("getAll", discountsHandler)
    }
    @Suppress("UNCHECKED_CAST")
    private val storesHandler : MyWebserviceHandler =
        object : MyWebserviceHandler{
            override fun <T> onDataArrived(result: List<T>, ok: Boolean, timestamp: Long?) {
                if(ok) stores = result as List<Store>
                storesArrived = true
                checkIfAllDataArrived()
            }
        }
    @Suppress("UNCHECKED_CAST")
    private val discountsHandler : MyWebserviceHandler =
        object : MyWebserviceHandler{
            override fun <T> onDataArrived(result: List<T>, ok: Boolean, timestamp: Long?) {
                if(ok) discounts = result as List<Discount>
                discountsArrived = true
                checkIfAllDataArrived()
            }
        }

    private fun checkIfAllDataArrived()
    {
        if (storesArrived && discountsArrived)
            listener.onDataLoaded(stores, discounts)
    }
}