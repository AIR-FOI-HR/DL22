package hr.foi.air.ws

import com.google.gson.FieldNamingStrategy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.squareup.okhttp.OkHttpClient
import hr.foi.air.ws.responses.MyWebserviceResponse
import hr.foi.air.core.entities.Discount
import hr.foi.air.core.entities.Store
import hr.foi.air.ws.handlers.MyWebserviceHandler
import retrofit.*

class MyWebserviceCaller {
    lateinit var retrofit : Retrofit
    val baseUrl : String = "http://cortex.foi.hr/mtl/courses/air/"

    init {
        retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(OkHttpClient())
            .build()
    }

    fun getAllStores(method: String, dataArrivedHandler : MyWebserviceHandler)
    {
        val serviceAPI = retrofit.create(MyWebservice::class.java)
        val call : Call<MyWebserviceResponse> = serviceAPI.getAllStores(method)
        call.enqueue (
            object : Callback<MyWebserviceResponse>{
                override fun onResponse(
                    response: Response<MyWebserviceResponse>?,
                    retrofit: Retrofit?
                ) {
                    try {
                        if(response != null && response.isSuccess)
                        {
                            val gson = Gson()
                            val storeItems : Array<Store> = gson.fromJson(
                                response.body().items, Array<Store>::class.java)
                            dataArrivedHandler.onDataArrived<Store>(
                                storeItems.toList(), true, response.body().timeStamp)

                        }
                        else {
                            //TODO("Nešto nije u redu sa response")
                        }
                    }
                    catch (ex: Exception) {
                        //TODO("Iznimka u obradi odgovora")
                    }
                }

                override fun onFailure(t: Throwable?) {
                    TODO("Not yet implemented")
                }
            }
        )
    }

    fun getAllDiscounts(method: String, dataArrivedHandler : MyWebserviceHandler)
    {
        val serviceAPI = retrofit.create(MyWebservice::class.java)
        val call : Call<MyWebserviceResponse> = serviceAPI.getAllDiscounts(method)
        call.enqueue (
            object : Callback<MyWebserviceResponse>{
                override fun onResponse(
                    response: Response<MyWebserviceResponse>?,
                    retrofit: Retrofit?
                ) {
                    try {
                        if(response != null && response.isSuccess)
                        {
                            val customPolicy = FieldNamingStrategy {
                                f -> f.name.replace("discountValue", "discount")
                            }
                            val gson = GsonBuilder()
                                .setDateFormat("yyyy-MM-dd")
                                .setFieldNamingStrategy(customPolicy)
                                .create()
                            val discountItems : Array<Discount> = gson.fromJson(
                                response.body().items, Array<Discount>::class.java)
                            dataArrivedHandler.onDataArrived<Discount>(
                                discountItems.toList(), true, response.body().timeStamp)

                        }
                        else {
                            //TODO("Nešto nije u redu sa response")
                        }
                    }
                    catch (ex: Exception) {
                        //TODO("Iznimka u obradi odgovora")
                    }
                }

                override fun onFailure(t: Throwable?) {
                    TODO("Not yet implemented")
                }
            }
        )
    }
}