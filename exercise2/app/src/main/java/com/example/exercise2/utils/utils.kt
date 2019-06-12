package com.example.exercise2.utils

import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.exercise2.model.Data
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.BufferedReader
import java.io.File

fun writeJSONtoFile(fileName: String) {
    val listData = ArrayList<Data>()

    for (i in 0 until 10000) {
        listData.add(Data("Element $i", "content $i"))
    }
    val jsonString: String = Gson().toJson(listData)
    val file = File(fileName)
    file.writeText(jsonString)
}

fun readJSONFromFile(f: String): List<Data> {

    val bufferedReader: BufferedReader = File(f).bufferedReader()
    val inputString = bufferedReader.use { it.readText() }

    val listData: List<Data> = Gson().fromJson(inputString, object : TypeToken<List<Data>>() {}.type)
    return listData
}

/*
* each time add 10 items from parent list to current list
* */
fun addItemToCurrentList(bigList: ArrayList<Data>, currentList: ArrayList<Data?>, numberOfItem: Int = 10) {
    if (currentList.size < bigList.size) {
        if (currentList.size + numberOfItem <= bigList.size) {
            for (i in currentList.size until currentList.size + numberOfItem) {
                currentList.add(bigList[i])
            }
        } else {
            for (i in currentList.size until bigList.size) {
                currentList.add(bigList[i])
            }
        }
    }
}

/*
* check when scrolling at bottom of recyclerview
* */
fun isTheLastItem(mRecycleView: RecyclerView): Boolean {
    val layoutManager = mRecycleView.layoutManager as LinearLayoutManager
    val pos = layoutManager.findLastCompletelyVisibleItemPosition()
    val numItems = mRecycleView.adapter!!.itemCount

    if (pos >= numItems - 1) {
        return true
    }
    return false
}