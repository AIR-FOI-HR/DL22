package hr.foi.air.ws.handlers

import java.sql.Timestamp

interface MyWebserviceHandler {
    fun <T>onDataArrived(result: List<T>, ok: Boolean, timestamp: Long?)
}