package com.frankymedia.dogsgenerator.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.frankymedia.dogsgenerator.devicedatabase.RoomDogsDatabase
import com.frankymedia.dogsgenerator.devicedatabase.RoomDogsInfo
import com.frankymedia.dogsgenerator.devicedatabase.asDomainModel
import com.frankymedia.dogsgenerator.glide.GlideApp
import com.frankymedia.dogsgenerator.network.DogsApiService
import com.frankymedia.dogsgenerator.network.RemoteDogsInfo
import com.frankymedia.dogsgenerator.network.asDataBaseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/*
    Repository class interfaces for viewmodel, network and dogsDatabase interaction.
 */

class DogsRepository (val dogsDatabase : RoomDogsDatabase){


    val recentDog: MutableLiveData<RemoteDogsInfo> by lazy {
        MutableLiveData<RemoteDogsInfo>()
    }


    suspend fun fetchAndUpdateDogs() {
        withContext(Dispatchers.IO) {
            val dogsServ = DogsApiService.createService().getDogs().await()
            recentDog.postValue(dogsServ)
            dogsDatabase.dao.insertOrUpdate(dogsServ.asDataBaseModel())
        }
    }

    suspend fun getRecentDogsList() {
        withContext(Dispatchers.IO) {
            val dogsServ = DogsApiService.createService().getDogs().await()
            recentDog.postValue(dogsServ)
            dogsDatabase.dao.insertDogs(mutableListOf(dogsServ.asDataBaseModel()))
            dogsDatabase.dao.retreiveDogs()
        }
    }
    /*
      Delete all the data from local db
     */
    suspend fun clearRecentDogsList() {
        withContext(Dispatchers.IO) {
            dogsDatabase.dao.deleteAllDogs()
        }
    }
    /*
        Clears Disk and memory cache
     */
    suspend fun clearRecentDogsCache(context :Context){
        withContext(Dispatchers.IO) {
            GlideApp.get(context).clearDiskCache()
            GlideApp.get(context).clearMemory()


        }

    }

    val dogsList : LiveData<List<RemoteDogsInfo>> = Transformations.map(dogsDatabase.dao.retreiveDogs()){
        it.asDomainModel()
    }
    val roomDogsList : LiveData<List<RoomDogsInfo>> = dogsDatabase.dao.retreiveDogs()


}
