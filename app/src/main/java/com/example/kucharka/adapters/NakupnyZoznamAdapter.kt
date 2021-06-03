package com.example.kucharka

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import java.io.Serializable
import java.util.*

data class Poznamka(
    val id:UUID = UUID.randomUUID(),
    val poznamka: String
) : Serializable

object NakupnyZoznamDiff: DiffUtil.ItemCallback<Poznamka>(){
    //Kedy sa 2 objekty rovnaju
    override fun areItemsTheSame(oldItem: Poznamka, newItem: Poznamka): Boolean {
        return oldItem == newItem
    }

    //kedy su 2 objekty equivaletne, porovnava na zaklade vlastnosti
    override fun areContentsTheSame(oldItem: Poznamka, newItem: Poznamka): Boolean {
        return oldItem == newItem
    }
}

class NakupnyZoznamViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
    val nakupnyZoznamTextView : TextView = itemView.findViewById(R.id.nakupnyZoznamTextView)
    fun bind(poznamka: Poznamka){
        nakupnyZoznamTextView.text = poznamka.poznamka
    }
}

class NakupnyZoznamAdapter : ListAdapter<Poznamka, NakupnyZoznamViewHolder>(NakupnyZoznamDiff){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NakupnyZoznamViewHolder {
        val layout = LayoutInflater.from(parent.context).inflate(R.layout.nakupny_zoznam_list_item, parent, false)

        return NakupnyZoznamViewHolder(layout)
    }

    override fun onBindViewHolder(holder: NakupnyZoznamViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}