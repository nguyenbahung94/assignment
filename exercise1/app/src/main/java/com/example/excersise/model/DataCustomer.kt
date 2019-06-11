package com.example.excersise.model


import com.google.gson.annotations.SerializedName

data class DataCustomer(
    @SerializedName("data")
    val data: List<Data>,
    @SerializedName("status")
    val status: Boolean
)