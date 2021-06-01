package com.example.kucharka

import android.content.Intent
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider


class ReceptActivity : AppCompatActivity() {
    lateinit var receptNazovTextView: TextView
   // lateinit var kategoriaTextView: TextView
    lateinit var dlzkaPripravyTextView: TextView
    lateinit var objemKaloriiTextView: TextView
    lateinit var ingrediencieTextView: TextView
    lateinit var postupTextView: TextView

    lateinit var poznamkyEditText: EditText

    lateinit var upravitButton: Button
    lateinit var poznamkyButton: Button

    lateinit var receptViewModel: ReceptViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recept)



        receptNazovTextView = findViewById(R.id.receptNazovTextView)
       // kategoriaTextView = findViewById(R.id.receptNazovTextView)
        dlzkaPripravyTextView = findViewById(R.id.dlzkaPripravyTextView)
        objemKaloriiTextView = findViewById(R.id.objemKaloriiTextView)
        ingrediencieTextView = findViewById(R.id.ingrediencieTextView)
        postupTextView = findViewById(R.id.postupTextView)

        poznamkyButton = findViewById(R.id.pridajPoznamkuButton)
        poznamkyEditText = findViewById(R.id.poznamkyEditText)



        //na zaklade intentu ktorým sme spustili tuto aktivitu, si vytiahneme informaciu z intentu čo je recept
        val recept = intent.extras!!.get("recept") as Recept
        receptNazovTextView.text = recept.nazov
        dlzkaPripravyTextView.text = "Dľžka prípravy: " + recept.dlzkaPripravy
        objemKaloriiTextView.text = "Objem kalórii: " + recept.objemKalorii
        ingrediencieTextView.text = "Zoznam ingrediencii: " + recept.ingrediencie
        postupTextView.text = "Postup: " + recept.postup

        poznamkyEditText.setText(recept.poznamky)



        upravitButton = findViewById(R.id.updateReceptuButton)
        upravitButton.setOnClickListener{
            val intent = Intent(this,UpdateReceptActivity::class.java)
            intent.putExtra("recept", recept)
            this.startActivity(intent)

        }




        poznamkyButton.setOnClickListener{
            receptViewModel = ViewModelProvider(this).get(ReceptViewModel::class.java)
            val updatnutyRecept = Recept(recept.id,recept.timestamp,
                recept.nazov, "", recept.dlzkaPripravy,
                recept.objemKalorii, recept.ingrediencie, recept.postup, poznamkyEditText.text.toString())

            receptViewModel.update(updatnutyRecept)
            Toast.makeText(this, "Úspešne si uložil poznámku", Toast.LENGTH_SHORT).show()
            val intent = Intent(this,MainActivity::class.java)
            this.startActivity(intent)
        }

    }
}