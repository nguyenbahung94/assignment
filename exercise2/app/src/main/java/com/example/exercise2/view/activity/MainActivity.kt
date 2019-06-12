package com.example.exercise2.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.exercise2.R
import com.example.exercise2.model.Data
import com.example.exercise2.utils.addItemToCurrentList
import com.example.exercise2.utils.isTheLastItem
import com.example.exercise2.utils.readJSONFromFile
import com.example.exercise2.utils.writeJSONtoFile
import com.example.exercise2.view.adapter.AdapterData
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var isLoading = true
    private lateinit var fileName: String
    private val currentListDataShow = ArrayList<Data?>()
    private lateinit var listDataParent: ArrayList<Data>
    private lateinit var adapterData: AdapterData

    companion object {
        const val FILE_NAME = "/DataTest.json"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        fileName = filesDir.absolutePath + FILE_NAME
        writeJSONtoFile(fileName)
        initRecycleView()
        initEvent()
    }

    private fun initEvent() {
        fab.setOnClickListener {
            if (isLoading) {
                isLoading = false
                addTenItems()
                Toast.makeText(this@MainActivity, "load items", Toast.LENGTH_SHORT).show()
            }

        }

        mRecycleView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (isLoading && isTheLastItem(mRecycleView)) {
                    addTenItems()
                    isLoading = false
                }
            }
        })
    }

    private fun initRecycleView() {
        listDataParent = readJSONFromFile(fileName) as ArrayList<Data>
        mRecycleView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        mRecycleView.setHasFixedSize(true)
        addItemToCurrentList(listDataParent, currentListDataShow)
        adapterData = AdapterData(currentListDataShow, this@MainActivity)
        mRecycleView.adapter = adapterData
    }

    private fun addTenItems() {
        if (currentListDataShow.size < listDataParent.size) {
            currentListDataShow.add(null)
            adapterData.notifyItemInserted(currentListDataShow.size - 1)
            Handler().postDelayed({
                currentListDataShow.removeAt(currentListDataShow.size - 1)
                adapterData.notifyItemRemoved(currentListDataShow.size)
                addItemToCurrentList(listDataParent, currentListDataShow)
                adapterData.notifyDataSetChanged()
                isLoading = true
            }, 2000)

        }

    }
}
