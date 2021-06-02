package com.example.kucharka

import android.graphics.Paint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*

class NakupnyZoznamActivity : AppCompatActivity() {

    lateinit var mylist: ArrayList<String>
    lateinit var myAdapter: ArrayAdapter<String>
    lateinit var listView: ListView
    lateinit var editText: EditText
    lateinit var button: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nakupny_zoznam)

        mylist = ArrayList()
        myAdapter = ArrayAdapter(this,R.layout.list_view_layout, mylist)
        listView = findViewById(R.id.id_list_view)

        listView.adapter= myAdapter

        editText = findViewById(R.id.id_editTextListView)
        button = findViewById(R.id.id_buttonListView)

        button.setOnClickListener{
            mylist.add(editText.text.toString())
            myAdapter.notifyDataSetChanged()

            editText.setText("")
        }

        listView.setOnItemClickListener { parent, view, position, id ->
            val textView : TextView = view as TextView
            textView.paintFlags = textView.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
        }

    }


//    override fun onSaveInstanceState(outState: Bundle) {
//        super.onSaveInstanceState(outState)
//        outState.putStringArrayList("mylist", mylist)
//    }
//
//    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
//        super.onRestoreInstanceState(savedInstanceState)
//        this.mylist = savedInstanceState.getStringArrayList("myList") as ArrayList<String>
//        this.myAdapter.notifyDataSetChanged()
//        Toast.makeText(this, "Obnovila sa aktivita", Toast.LENGTH_LONG).show()
//    }


}