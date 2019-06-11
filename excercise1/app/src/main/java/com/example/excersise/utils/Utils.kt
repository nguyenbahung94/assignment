package com.example.excersise.utils

import android.content.Context
import com.example.excersise.model.DataCustomer
import com.google.gson.Gson
import java.io.IOException
import java.io.InputStream

fun getJson(context: Context): DataCustomer {
    val mJson = inputStreamToString(context.resources.assets.open("data.json"))
    val dataCustomer = Gson().fromJson(mJson, DataCustomer::class.java)
    return dataCustomer
}

fun convertToMap(dataCustomer: DataCustomer): HashMap<String, List<String>> {
    val listArrivals = dataCustomer.data[0].arrivals
    val listDepartures = dataCustomer.data[0].departures
    val finalData = HashMap<String, List<String>>()
    for (i in 0 until listArrivals.size) {
        val arrivals = listArrivals[i]
        finalData[arrivals] = listDepartures[arrivals] ?: arrayListOf()
    }
    return finalData
}


fun inputStreamToString(inputStream: InputStream): String {
    return try {
        val bytes = ByteArray(inputStream.available())
        inputStream.read(bytes, 0, bytes.size)
        String(bytes)
    } catch (e: IOException) {
        "exception"
    }

}