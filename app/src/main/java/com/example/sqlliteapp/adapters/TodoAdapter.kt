package com.example.sqlliteapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sqlliteapp.R
import com.example.sqlliteapp.models.Todo
import com.google.android.material.textview.MaterialTextView
import java.util.*
import kotlin.collections.ArrayList

class TodoAdapter(var context: Context, var todoes: ArrayList<Todo>) :
    RecyclerView.Adapter<TodoAdapter.ViewHolder>(){



    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val txtTitle  = itemView.findViewById<MaterialTextView>(R.id.text_view_todoTitle)
        val txtDescription  = itemView.findViewById<MaterialTextView>(R.id.text_view_todoDescription)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return TodoAdapter.ViewHolder(LayoutInflater.from(context).inflate(R.layout.todo_item,parent,false))
    }

    override fun getItemCount(): Int {
        return todoes.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val current = todoes[position]
        holder.txtTitle.text = current.title
        holder.txtDescription.text = current.description
    }

}