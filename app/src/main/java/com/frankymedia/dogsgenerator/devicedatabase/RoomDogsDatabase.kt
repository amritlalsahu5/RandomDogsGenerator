package com.frankymedia.dogsgenerator.devicedatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/* dogsDatabase instance
*/
@Database(entities = [RoomDogsInfo::class],version = 1, exportSchema = false)
abstract class RoomDogsDatabase : RoomDatabase(){
    abstract val dao : RoomDogsDao

    companion object {
        @Volatile
        private var INSTANCE: RoomDogsDatabase? = null

        fun getInstance(context: Context) : RoomDogsDatabase {
            synchronized(this){
                var instance =
                    INSTANCE
                if(instance == null) {
                    instance = Room.databaseBuilder(context.applicationContext,
                        RoomDogsDatabase::class.java,
                        "recent_dogs").build()

                    INSTANCE = instance
                }
                return instance
            }
        }
    }

}