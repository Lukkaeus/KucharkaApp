package com.example.kucharka.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import com.example.kucharka.MainActivity
import com.example.kucharka.R
import com.example.kucharka.entities.Recept
import com.example.kucharka.viewModels.ReceptViewModel


class UpdateReceptActivity : AppCompatActivity() {
    lateinit var addButton : Button
    lateinit var receptViewModel: ReceptViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_recept)


        receptViewModel = ViewModelProvider(this).get(ReceptViewModel::class.java)

        nacitajUdaje()

        addButton = findViewById(R.id.upravReceptButton)
        addButton.setOnClickListener{
            upravaReceptu()

        }

    }

    private fun nacitajUdaje() {
        val editTextNazov: EditText = findViewById(R.id.upravNazovEditText)
        val editTextDlzkaPripravy: EditText = findViewById(R.id.upravDlzkuPripravyEditText)
        val editTextObjemKalorii: EditText = findViewById(R.id.upravObjemKaloriiEditText)
        val editTextIngrediencie: EditText = findViewById(R.id.upravIngrediencieEditText)
        val editTextPostup: EditText = findViewById(R.id.upravPostupEditText)

        val recept = intent.extras!!.get("recept") as Recept

        editTextNazov.setText(recept.nazov)
        editTextDlzkaPripravy.setText(recept.dlzkaPripravy)
        editTextObjemKalorii.setText(recept.objemKalorii)
        editTextIngrediencie.setText(recept.ingrediencie)
        editTextPostup.setText(recept.postup)
    }

    private fun upravaReceptu() {
        val editTextNazov: EditText = findViewById(R.id.upravNazovEditText)
        val editTextDlzkaPripravy: EditText = findViewById(R.id.upravDlzkuPripravyEditText)
        val editTextObjemKalorii: EditText = findViewById(R.id.upravObjemKaloriiEditText)
        val editTextIngrediencie: EditText = findViewById(R.id.upravIngrediencieEditText)
        val editTextPostup: EditText = findViewById(R.id.upravPostupEditText)


        val recept = intent.extras!!.get("recept") as Recept

        val nazov = editTextNazov.text.toString()
        val dlzkaPripravy = editTextDlzkaPripravy.text.toString()
        val objemKalorii = editTextObjemKalorii.text.toString()
        val ingrediencie = editTextIngrediencie.text.toString()
        val postup = editTextPostup.text.toString()

        //ak su vyplnene všetky povinne polia robime update
        if(suPoliaPrazdne(nazov,postup,ingrediencie)){
            val updatnutyRecept = Recept(recept.id,recept.timestamp,
                                        nazov, "", dlzkaPripravy,
                                        objemKalorii, ingrediencie, postup, recept.poznamky)

            receptViewModel.update(updatnutyRecept)
            Toast.makeText(this, "Recept ${recept.nazov} bol úspešne upravený ", Toast.LENGTH_LONG).show()

            val intent = Intent(this, MainActivity::class.java)
            this.startActivity(intent)
        } else {
            Toast.makeText(this, "Prosím vyplňte všetky povinné textové polia", Toast.LENGTH_LONG).show()
        }

    }


    private fun suPoliaPrazdne(nazov: String, postup: String, ingrediencie: String): Boolean {
        return !(TextUtils.isEmpty(nazov)  || TextUtils.isEmpty(postup) || TextUtils.isEmpty(ingrediencie))
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.delete_menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.odstranenieReceptuMenu){
            deleteRecept()
        }
        return super.onOptionsItemSelected(item)
    }


    private fun deleteRecept(){
        val recept = intent.extras!!.get("recept") as Recept

        val builder = AlertDialog.Builder(this, R.style.AlertDialogCustom)
        builder.setPositiveButton("Áno"){_,_ ->
            receptViewModel.delete(recept)
            Toast.makeText(this, "Úspešne odstránený: ${recept.nazov}", Toast.LENGTH_LONG).show()
            startActivity(Intent(this, MainActivity::class.java))
        }
        builder.setNegativeButton("Nie"){_,_ ->}
        builder.setTitle("Vymazať ${recept.nazov}?")
        builder.setMessage("Určite chceš vymazať ${recept.nazov}?")
        builder.create().show()
    }


}