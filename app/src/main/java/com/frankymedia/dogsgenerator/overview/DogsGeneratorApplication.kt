package com.frankymedia.dogsgenerator.overview

import android.app.Application
import androidx.work.Constraints
import androidx.work.NetworkType
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
/*
    Custom Application class
 */
class DogsGeneratorApplication : Application() {

    private val applicationScope = CoroutineScope(Dispatchers.Default)
    /*
        Work Manager has been kept to do backgound task on launch

     */
    private fun setUpWork() {

        //work constraints
        val constraints = Constraints.Builder()
            .setRequiresBatteryNotLow(true)
            .setRequiresStorageNotLow(true)
            .setRequiredNetworkType(NetworkType.UNMETERED)
            .setRequiresDeviceIdle(true)
            .build()

    }

    //running the setUpWork function on a separate thread using coroutines
    fun workUnit()
    {
        applicationScope.launch {
            setUpWork()
        }
    }

}