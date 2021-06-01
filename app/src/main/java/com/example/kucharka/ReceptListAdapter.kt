package com.example.kucharka

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

object ReceptDiff : DiffUtil.ItemCallback<Recept>(){
    override fun areItemsTheSame(oldItem: Recept, newItem: Recept): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Recept, newItem: Recept): Boolean {
        return oldItem.nazov == newItem.nazov && oldItem.timestamp == newItem.timestamp
    }
}

class ReceptViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val text1: TextView = itemView.findViewById(R.id.textView)

    fun bind(recept: Recept, context: Context){
        text1.setOnClickListener{
            val intent = Intent(context,ReceptActivity::class.java)
            intent.putExtra("recept", recept)
            context.startActivity(intent)
        }

        text1.text = recept.nazov
    }
}

class ReceptListAdapter(private val context: Context) : androidx.recyclerview.widget.ListAdapter<Recept, ReceptViewHolder>(ReceptDiff) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReceptViewHolder {
//        val layout = LayoutInflater.from(parent.context).inflate(android.R.layout.simple_list_item_1, parent, false)
        val layout = LayoutInflater.from(parent.context).inflate(R.layout.recepty_list_item, parent, false)

        return ReceptViewHolder(layout)
    }

    override fun onBindViewHolder(holder: ReceptViewHolder, position: Int) {
        holder.bind(getItem(position),context)
    }


}