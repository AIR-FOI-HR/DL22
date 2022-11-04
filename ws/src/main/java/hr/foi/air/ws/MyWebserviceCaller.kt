package hr.foi.air.ws

import com.google.gson.Gson
import com.squareup.okhttp.OkHttpClient
import hr.foi.air.ws.responses.MyWebserviceResponse
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

    fun getAllStores()
    {
        val serviceAPI = retrofit.create(MyWebservice::class.java)
        val call : Call<MyWebserviceResponse> = serviceAPI.getAllStores(method)
        call.enqueue (
            object : Callback<MyWebserviceResponse>{
                override fun onResponse(
                    response: Response<MyWebserviceResponse>?,
                    retrofit: Retrofit?
                ) {
                    TODO("Not yet implemented")
                }

                override fun onFailure(t: Throwable?) {
                    TODO("Not yet implemented")
                }
            }
        )
    }
}