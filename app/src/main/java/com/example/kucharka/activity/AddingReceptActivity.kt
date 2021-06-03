package com.example.kucharka.activity

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.lifecycle.ViewModelProvider
import com.example.kucharka.MainActivity
import com.example.kucharka.R
import com.example.kucharka.entities.Recept
import com.example.kucharka.viewModels.ReceptViewModel
import java.util.*

class AddingReceptActivity : AppCompatActivity() {
    lateinit var addButton : Button
    lateinit var receptViewModel: ReceptViewModel

    //premenne pre notifikáciu
    val CHANNEL_ID = "channel_id"
    val notificationId = 101

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_adding_recept)


        supportActionBar?.setTitle("Pridávanie nového receptu")

        receptViewModel = ViewModelProvider(this).get(ReceptViewModel::class.java)
        addButton = findViewById(R.id.pridajReceptButton)

        addButton.setOnClickListener{
            vlozenieDoDatabazy()
        }

        createNotificationChannel()


    }

    fun vlozenieDoDatabazy(){
        val editTextNazov: EditText = findViewById(R.id.pridajNazovEditText)
        val editTextDlzkaPripravy: EditText = findViewById(R.id.pridajDlzkuPripravyEditText)
        val editTextObjemKalorii: EditText = findViewById(R.id.pridajObjemKaloriiEditText)
        val editTextIngrediencie: EditText = findViewById(R.id.pridajIngrediencieEditText)
        val editTextPostup: EditText = findViewById(R.id.pridajPostupEditText)

        val nazov = editTextNazov.text.toString()
        val dlzkaPripravy = editTextDlzkaPripravy.text.toString()
        val objemKalorii = editTextObjemKalorii.text.toString()
        val ingrediencie = editTextIngrediencie.text.toString()
        val postup = editTextPostup.text.toString()

        if(suPoliaPrazdne(nazov,postup,ingrediencie)){
            val recept = Recept(0,Date().time,  nazov,"", dlzkaPripravy, objemKalorii, ingrediencie ,postup, "")
            receptViewModel.add(recept)
//            Toast.makeText(this, "Nový recept bol úspešne pridaný", Toast.LENGTH_LONG).show()
            sendNotification()

            startActivity(Intent(this, MainActivity::class.java))
        } else {
            Toast.makeText(this, "Prosím vyplňte všetky povinné textové polia", Toast.LENGTH_LONG).show()
        }

    }

    private fun suPoliaPrazdne(nazov: String, postup: String, ingrediencie: String): Boolean {
        return !(TextUtils.isEmpty(nazov)  || TextUtils.isEmpty(postup) || TextUtils.isEmpty(ingrediencie))
    }


    private fun createNotificationChannel(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val name = "Notifikácia"
            val descriptionText = ""
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID,name,importance).apply {
                description = descriptionText
            }
            val notificationManager: NotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    private fun sendNotification(){
        val editTextNazov: EditText = findViewById(R.id.pridajNazovEditText)
        val nazov = editTextNazov.text.toString()

        val builder = NotificationCompat.Builder(this,CHANNEL_ID)
                        .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("Úspešne ste vytvorili nový recept")
            .setContentText(nazov)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        with(NotificationManagerCompat.from(this)){
            notify(notificationId, builder.build())
        }
    }


}