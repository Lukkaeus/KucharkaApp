package com.example.kucharka.interfaces

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.kucharka.entities.Recept

@Dao
interface ReceptDao {


    @Query("SELECT * FROM recept")
    fun list(): LiveData<List<Recept>>

    //@databaza dopíše za nás inserty
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(recept: Recept)

    @Update
    fun updateRecept(recept: Recept)

    @Delete
    fun deleteRecept(recept: Recept)



    

}