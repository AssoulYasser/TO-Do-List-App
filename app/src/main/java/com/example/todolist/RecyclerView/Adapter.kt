package com.example.todolist.RecyclerView

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.R
import com.example.todolist.data.Model

class Adapter(var initData:MutableList<Model>) : RecyclerView.Adapter<Holder>() {

    private var data:MutableList<Model> = initData

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(LayoutInflater.from(parent.context).inflate(R.layout.rv_item, parent, false))
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.titleView.text = data[position].title
        holder.timeView.text = data[position].time.toString()
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun addData(model:Model){
        data.add(model)
        notifyDataSetChanged()
    }

    fun deleteData(model: Long){
        val index = getIndex(model)
        data.removeAt(index)
        notifyDataSetChanged()
    }

    fun getIndex(id: Long):Int{
        data.forEach {
            if (it.id == id)
                return data.indexOf(it)
        }
        return 0
    }

    fun updateData(model:Model){
        var index:Int = getIndex(model.id)

        data[index].title = model.title
        data[index].description = model.description
    }

    fun refreshData(list:MutableList<Model>){
        data = list
        notifyDataSetChanged()
    }


}