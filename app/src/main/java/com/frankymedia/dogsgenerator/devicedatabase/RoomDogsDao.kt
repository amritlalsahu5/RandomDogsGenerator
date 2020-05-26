package com.frankymedia.dogsgenerator.devicedatabase

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction

/*
    Room Dao to handle database operations
 */
@Dao
interface RoomDogsDao{
    @Insert
    fun insertDogs(dogsInfo : List<RoomDogsInfo>)

    @Query("select * from roomdogsinfo order by date DESC")
    fun retreiveDogs() : LiveData<List<RoomDogsInfo>>

    @Query("select * from roomdogsinfo where date =:time")
    fun getDogByTime(time : Long) : List<RoomDogsInfo>

    @Query("DELETE FROM roomdogsinfo")
    fun deleteAllDogs()

    @Query("UPDATE roomdogsinfo SET date = :date  WHERE url = :url")
    fun updateQuantity(date : Long, url : String)

    @Query("DELETE FROM  roomdogsinfo where  date not in (SELECT date from roomdogsinfo order by date DESC LIMIT 20)")
    fun deleteRowOnExceedLimit()
    /*
        Insert or udpated Entry based on cache limit .
     */
    @Transaction
    fun insertOrUpdate( dogsInfo : RoomDogsInfo) {
        val itemsFromDB : List<RoomDogsInfo> = getDogByTime(dogsInfo.date)
        if (itemsFromDB.isEmpty()) {
            insertDogs(mutableListOf(dogsInfo))
            deleteRowOnExceedLimit()
        }
        else
            updateQuantity(dogsInfo.date, dogsInfo.url)
    }

}