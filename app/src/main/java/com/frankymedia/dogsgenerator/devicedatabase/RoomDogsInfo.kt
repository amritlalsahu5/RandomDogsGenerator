package com.frankymedia.dogsgenerator.devicedatabase

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.frankymedia.dogsgenerator.network.RemoteDogsInfo

/*
dogsDatabase entity class
  RoomDogsInfo
 */
@Entity()
data class RoomDogsInfo(
    @PrimaryKey(autoGenerate = true)
    val id : Int,
    val url : String,
    val date : Long,
    val status : String
)

/*
extension function to convert the RoomDogsInfo to Remote Dogs object
 */

fun List<RoomDogsInfo>.asDomainModel() : List<RemoteDogsInfo>{
    return map {

        RemoteDogsInfo(
            id = it.id,
            message = it.url,
            status = it.status,
            date = it.date
        )
    }
}

