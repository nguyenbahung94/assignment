package com.example.excersise.model


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("arrivals")
    val arrivals: List<String>,
    @SerializedName("currentTime")
    val currentTime: String,
    @SerializedName("departures")
    val departures: Map<String,List<String>?>
)