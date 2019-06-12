package com.example.exercise2.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.exercise2.R
import com.example.exercise2.model.Data
import kotlinx.android.synthetic.main.item.view.*
import kotlinx.android.synthetic.main.item_progressbar.view.*

class AdapterData(private val listData: List<Data?>, private val context: Context) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        const val VIEW_TYPE_ITEM = 0
        const val VIEW_TYPE_LOADING = 1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_ITEM -> DataHolder(LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false))
            else -> ViewHolderLoading(
                LayoutInflater.from(parent.context).inflate(R.layout.item_progressbar, parent, false)
            )
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (listData[position] == null) VIEW_TYPE_LOADING else VIEW_TYPE_ITEM
    }

    override fun getItemCount(): Int = listData.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is DataHolder) {
            val currentData = listData[position]
            holder.tvName.text = currentData!!.name
            holder.tvContent.text = currentData.content
        } else if (holder is ViewHolderLoading) {
            holder.progressbar.isIndeterminate = true
        }
    }


    private class DataHolder(v: View) : RecyclerView.ViewHolder(v) {
        val tvName = v.tvName!!
        val tvContent = v.tvContent!!

    }

    private class ViewHolderLoading(v: View) : RecyclerView.ViewHolder(v) {
        val progressbar = v.itemProgressbar
    }
}