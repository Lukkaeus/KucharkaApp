package com.example.kucharka.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable
import java.util.*

@Entity()
data class Recept(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val timestamp: Long = Date().time,
    val nazov: String,
    val kategoria: String,
    val dlzkaPripravy: String,
    val objemKalorii: String,
    val ingrediencie: String,
    val postup: String,
    val poznamky: String,

    ) : Serializable