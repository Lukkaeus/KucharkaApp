package com.example.kucharka.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.kucharka.MainActivity
import com.example.kucharka.R
import com.example.kucharka.entities.Recept
import com.example.kucharka.viewModels.ReceptViewModel


class ReceptActivity : AppCompatActivity() {
    lateinit var receptNazovTextView: TextView
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
        dlzkaPripravyTextView = findViewById(R.id.dlzkaPripravyTextView)
        objemKaloriiTextView = findViewById(R.id.objemKaloriiTextView)
        ingrediencieTextView = findViewById(R.id.ingrediencieTextView)
        postupTextView = findViewById(R.id.postupTextView)

        poznamkyButton = findViewById(R.id.pridajPoznamkuButton)
        poznamkyEditText = findViewById(R.id.poznamkyEditText)


        //na zaklade intentu ktorým sme spustili tuto aktivitu, si vytiahneme informaciu o recepte pomocou .extras
        val recept = intent.extras!!.get("recept") as Recept
        receptNazovTextView.text = recept.nazov
        if (recept.dlzkaPripravy == "") {
            dlzkaPripravyTextView.text = "Dĺžka prípravy: neuvedené"
        } else {
            dlzkaPripravyTextView.text = "Dľžka prípravy: " + recept.dlzkaPripravy
        }
        if (recept.objemKalorii == "") {
            objemKaloriiTextView.text = "Objem kalórii: neuvedené"
        } else {
            objemKaloriiTextView.text = "Objem kalórii: " + recept.objemKalorii
        }
        ingrediencieTextView.text = "Zoznam ingrediencii: " + recept.ingrediencie
        postupTextView.text = "Postup: " + recept.postup

        poznamkyEditText.setText(recept.poznamky)


        upravitButton = findViewById(R.id.updateReceptuButton)
        upravitButton.setOnClickListener {
            val intent = Intent(this, UpdateReceptActivity::class.java)
            intent.putExtra("recept", recept)
            this.startActivity(intent)
        }


        poznamkyButton.setOnClickListener {
            receptViewModel = ViewModelProvider(this).get(ReceptViewModel::class.java)
            val updatnutyRecept = Recept(
                recept.id,
                recept.timestamp,
                recept.nazov,
                "",
                recept.dlzkaPripravy,
                recept.objemKalorii,
                recept.ingrediencie,
                recept.postup,
                poznamkyEditText.text.toString()
            )

            receptViewModel.update(updatnutyRecept)
            Toast.makeText(this, "Úspešne si uložil poznámku receptu: ${recept.nazov}", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, MainActivity::class.java)
            this.startActivity(intent)
        }

    }
}