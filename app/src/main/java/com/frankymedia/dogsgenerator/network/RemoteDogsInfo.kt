package com.frankymedia.dogsgenerator.network

import android.os.Parcelable
import com.frankymedia.dogsgenerator.devicedatabase.RoomDogsInfo
import kotlinx.android.parcel.Parcelize
import java.util.*


/*
    Pojo class to hold the Random dogs received from server.
 */
@Parcelize
data class RemoteDogsInfo (
    @Transient
    val id : Int?= 0,
    val date : Long?,
    val message : String,
    val status : String

) : Parcelable


fun RemoteDogsInfo.asDataBaseModel() : RoomDogsInfo {

    return RoomDogsInfo(
        id = this.id?:0,
        url = this.message,
        status = this.status,
        date = Calendar.getInstance().timeInMillis
    )

}