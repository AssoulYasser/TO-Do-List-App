package com.example.todolist.RecyclerView

import android.view.View
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.R
import com.example.todolist.Retrofit.HttpRequest

class Holder(item: View) : RecyclerView.ViewHolder(item) {
    var titleView:TextView = item.findViewById(R.id.titleTV)
    var doneView:CheckBox = item.findViewById(R.id.doneCB)
    var editView:ImageView = item.findViewById(R.id.editIV)
    var timeView:TextView = item.findViewById(R.id.timeTV)

    init {}
}