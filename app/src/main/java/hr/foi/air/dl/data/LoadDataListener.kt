package hr.foi.air.dl.data

import hr.foi.air.core.entities.Discount
import hr.foi.air.core.entities.Store

interface LoadDataListener {
    fun onDataLoaded(stores: List<Store>, discounts: List<Discount>)
}