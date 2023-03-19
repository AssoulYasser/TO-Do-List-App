package com.example.todolist.RecyclerView

import android.content.Intent
import android.util.Log
import android.view.View
import android.widget.CheckBox
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.R
import com.example.todolist.Retrofit.HttpRequest
import com.example.todolist.activities.TaskDetailsActivity
import kotlinx.android.synthetic.main.rv_item.view.*

class Holder(item: View) : RecyclerView.ViewHolder(item) {
    var titleView:TextView = item.findViewById(R.id.titleTV)
    var timeView:TextView = item.findViewById(R.id.timeTV)
    var checkBoxView:CheckBox = item.findViewById(R.id.doneCB)

    lateinit var data:com.example.todolist.data.Model

    init {
        item.setOnClickListener {
            val intent = Intent(it.context, TaskDetailsActivity::class.java)
            intent.putExtra("id", data.id)
            intent.putExtra("title", data.title)
            intent.putExtra("description", data.description)
            intent.putExtra("time", data.time)
            intent.putExtra("isDone", data.isDone)
            it.context.startActivity(intent)
        }

        checkBoxView.setOnClickListener{
            val completed = data
            completed.isDone = checkBoxView.isChecked
            HttpRequest.updateData(completed)
        }
    }
}
