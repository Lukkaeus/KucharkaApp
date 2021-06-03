package com.example.kucharka.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.kucharka.Poznamka
import java.util.*

//state dokáže prežiť reštart aktivity, aj odchod na pozadie
class NakupnyZoznamViewModel(val state : SavedStateHandle) : ViewModel() {

    val poznamky: MutableLiveData<List<Poznamka>>
        get() {
            return state.getLiveData("poznamky")
        }


    fun addPoznamka(text : String){
        //ak je hodnota null tak vratime prazdny zoznam
        val oldPoznamka : List<Poznamka> = state.get("poznamky") ?: emptyList()
        val newPoznamka = oldPoznamka.plus(Poznamka(UUID.randomUUID(),text))
        state.set("poznamky", newPoznamka)
    }

    fun removePoznamka(position: Int){
        val oldPoznamky = poznamky.value ?: emptyList()
        state["poznamky"] = oldPoznamky - oldPoznamky.elementAt(position)
    }



}