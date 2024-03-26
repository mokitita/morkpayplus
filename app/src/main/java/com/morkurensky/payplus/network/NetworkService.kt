package com.morkurensky.payplus

import com.google.gson.GsonBuilder
import com.morkurensky.payplus.data.Item
import com.morkurensky.payplus.network.ItemsDeserializer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.POST

private const val BASE_URL = "http://192.168.1.127:8030/"

private val gson = GsonBuilder().registerTypeAdapter(List::class.java, ItemsDeserializer())
    .setLenient()
    .create()

class NetworkService {
        private val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        private val productService = retrofit.create(PayPlusService::class.java)

        suspend fun allItems(): Call<List<Item>> = withContext(Dispatchers.Default) {
            productService.getItems()
        }
    }


interface PayPlusService {

    @POST("payment/billing/entry/headers")
    fun getItems(): Call<List<Item>>
}



