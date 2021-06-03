package com.example.kucharka

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kucharka.activity.AddingReceptActivity
import com.example.kucharka.activity.AlergenyActivity
import com.example.kucharka.activity.NakupnyZoznamActivity
import com.example.kucharka.viewModels.ReceptViewModel
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {
   //premenne pre nav drawer
    lateinit var toggle: ActionBarDrawerToggle
    lateinit var drawerLayout: DrawerLayout
    lateinit var navigationView: NavigationView

    val receptViewModel: ReceptViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        //kod pre nav drawer
        drawerLayout = findViewById(R.id.drawerLayout)
        navigationView = findViewById(R.id.nav_view)

        toggle = ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        navigationView.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.item1 -> startActivity(Intent(this, AddingReceptActivity::class.java))
                R.id.item2 -> startActivity(Intent(this, NakupnyZoznamActivity::class.java))
                R.id.item3 -> startActivity(Intent(this, AlergenyActivity::class.java))
            }
            true
        }
        //koniec kodu pre nav drawer


        //vytiahneme a deklarujeme recyclerView
        //stanovýme si linear layout pre recyclerView
        val receptyRecyclerView: RecyclerView =  findViewById(R.id.receptyRecyclerView)
        receptyRecyclerView.layoutManager = LinearLayoutManager(this)

        ReceptListAdapter(this).also {
            receptyRecyclerView.adapter = it
            receptViewModel.getRecepty().observe(this, it::submitList)
        }

    }

//nav drawer
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(toggle.onOptionsItemSelected(item)){
            true
        }
        return super.onOptionsItemSelected(item)
    }


}