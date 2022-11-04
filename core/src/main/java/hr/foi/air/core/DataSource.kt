package hr.foi.air.core

import android.content.Context

interface DataSource {
    fun loadData(listener: DataSourceListener, contex: Context)
}