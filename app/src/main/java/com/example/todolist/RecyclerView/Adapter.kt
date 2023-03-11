package com.example.todolist.RecyclerView

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.R

class Adapter(private val data:MutableList<Model>) : RecyclerView.Adapter<Holder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(LayoutInflater.from(parent.context).inflate(R.layout.rv_item, parent, false))
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.titleView.text = data[position].title
        holder.descriptionView.text = data[position].description
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun addData(id:Long,title:String, description:String){
        data.add(Model(id,title,description))
        notifyDataSetChanged()
    }


}