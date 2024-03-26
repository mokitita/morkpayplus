package com.morkurensky.payplus.network

import com.google.gson.Gson
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.morkurensky.payplus.data.Item
import java.lang.reflect.Type

class ItemsDeserializer : JsonDeserializer<List<Item>> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): List<Item> {
        val productsArray = json?.let {
            it.asJsonObject.get("headers").asJsonArray
        }
        productsArray?.let {
            val products = mutableListOf<Item>()
            it.forEach { jsonElement ->
                val product = Gson().fromJson(jsonElement, Item::class.java)
                products.add(product)
            }
            return products
        }
        return emptyList()
    }
}