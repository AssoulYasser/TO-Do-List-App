package com.example.todolist.RecyclerView

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.R

class Holder(item: View) : RecyclerView.ViewHolder(item) {
    var titleView:TextView = item.findViewById(R.id.titleTV)
    var descriptionView:TextView = item.findViewById(R.id.descriptionTV)
}