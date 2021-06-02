package com.example.kucharka

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.*

class NakupnyZoznamActivity : AppCompatActivity() {
    lateinit var zoznamRecyclerView: RecyclerView
    lateinit var pridajPolozkuButton: Button
    lateinit var polozkaEditText: EditText

    val viewModel: NakupnyZoznamViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nakupny_zoznam)


        zoznamRecyclerView = findViewById(R.id.nakupnyZoznamRecyclerView)
        pridajPolozkuButton = findViewById(R.id.pridajPolozkuButton)
        polozkaEditText = findViewById(R.id.polozkaEditText)

        class SwipeHelper : ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT){
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                viewModel.removePoznamka(viewHolder.adapterPosition)
            }

            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ) = false
        }

        ItemTouchHelper(SwipeHelper()).attachToRecyclerView(zoznamRecyclerView)

        //zoznamRecyclerView.layoutManager = LinearLayoutManager(this)
        zoznamRecyclerView.layoutManager = GridLayoutManager(this, 2)
        val adapter = NakupnyZoznamAdapter(this)

        zoznamRecyclerView.adapter = adapter

        viewModel.poznamky.observe(this) {
            adapter.submitList(it)
        }

    }

    fun onButtonClick(view: View) {
        val text = polozkaEditText.text.toString()
        viewModel.addPoznamka(text)
        polozkaEditText.setText("")
    }



}