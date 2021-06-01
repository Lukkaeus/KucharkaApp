package com.example.kucharka

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData

class ReceptViewModel(application: Application) : AndroidViewModel(application) {
    val appDatabase = AppDatabase(application)
    val receptDao = appDatabase.receptDao()

    //...


    fun getRecepty(): LiveData<List<Recept>> {
        return receptDao.list()
    }

    fun add(recept: Recept){
        //zabezpecuje aby pridavanie do databazy nebežalo na hlavnom vlákne
        appDatabase.transactionExecutor.execute{
            receptDao.insert(recept)
        }
    }

    fun update(recept: Recept){
        appDatabase.transactionExecutor.execute{
            receptDao.updateRecept(recept)
        }
    }

    fun delete(recept: Recept){
        appDatabase.transactionExecutor.execute{
            receptDao.deleteRecept(recept)
        }
    }

}