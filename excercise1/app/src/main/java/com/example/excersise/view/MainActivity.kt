package com.example.excersise.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.excersise.R
import com.example.excersise.utils.convertToMap
import com.example.excersise.utils.getJson
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    private lateinit var listArrivals: ArrayList<String>
    private lateinit var hashMapDepartures: HashMap<String, List<String>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getData()
        initSpinner()

    }

    private fun initSpinner() {
        val adapter = ArrayAdapter(this@MainActivity, android.R.layout.simple_spinner_item, listArrivals)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerArrivals.adapter = adapter
        spinnerArrivals.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                drawUIDepartures(adapter.getItem(position).toString())
            }
        }
    }

    private fun drawUIDepartures(item: String) {
        llLayoutContainDepartures.removeAllViews()
        val listDepartures = hashMapDepartures[item] as ArrayList<String>
        val inf = LayoutInflater.from(this@MainActivity)
        var child: View
        if (listDepartures.isNotEmpty()) {
            for (i in 0 until listDepartures.size) {
                child = with(inf) { inflate(R.layout.item, null) }
                val button = child.findViewById(R.id.btn) as Button
                button.text = listDepartures[i]
                button.setOnClickListener {
                    Toast.makeText(this@MainActivity, "Pick at ${listDepartures[i]}", Toast.LENGTH_SHORT).show()
                }
                llLayoutContainDepartures.addView(child)
            }

        } else {
            Toast.makeText(this@MainActivity, "No Departures Available", Toast.LENGTH_SHORT).show()
        }
    }

    private fun getData() {
        val dataCustomer = getJson(this@MainActivity)
        listArrivals = dataCustomer.data[0].arrivals as ArrayList<String>
        hashMapDepartures = convertToMap(dataCustomer)
    }

}
