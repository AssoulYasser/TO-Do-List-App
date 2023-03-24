package com.example.todolist.RecyclerView

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.R
import com.example.todolist.data.TaskModel

class Adapter(initData:MutableList<TaskModel>) : RecyclerView.Adapter<Holder>() {

    private var data:MutableList<TaskModel> = initData

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(LayoutInflater.from(parent.context).inflate(R.layout.rv_item, parent, false))
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.data = data[position]
        holder.titleView.text = data[position].title
        holder.timeView.text = data[position].time
        holder.checkBoxView.isChecked = data[position].isDone
    }



    override fun getItemCount(): Int {
        return data.size
    }

    fun addData(taskModel:TaskModel){
        data.add(taskModel)
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

    fun updateData(taskModel:TaskModel){
        var index:Int = getIndex(taskModel.id)

        data[index].title = taskModel.title
        data[index].description = taskModel.description
    }

    fun refreshData(list:MutableList<TaskModel>){
        data = list
        notifyDataSetChanged()
    }


}