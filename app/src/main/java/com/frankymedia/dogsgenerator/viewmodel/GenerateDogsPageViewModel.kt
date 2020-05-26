package com.frankymedia.dogsgenerator.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.frankymedia.dogsgenerator.devicedatabase.RoomDogsDatabase
import com.frankymedia.dogsgenerator.repository.DogsRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

enum class GenerateDogsApiStatus {
    LOADING, DONE, ERROR
}
/*
    View model to manage all the Dogs related operation.
 */
class GenerateDogsPageViewModel(private val app : Application) : AndroidViewModel(app) {
    var appRepository = DogsRepository(RoomDogsDatabase .getInstance(app))

    private val _networkError = MutableLiveData<Boolean>()
    val networkError : LiveData<Boolean>
    get() = _networkError

    private val _apiResponse = MutableLiveData<String>()

    //live data that will be observed in order to display error icon or loading icon
    private val localdogsApiStatus = MutableLiveData<GenerateDogsApiStatus>()

    val apiStatus : LiveData<GenerateDogsApiStatus>
        get() = localdogsApiStatus

    //liveData to hold the list of users returned from the Api
    val dogs = appRepository.roomDogsList
    val recentDog = appRepository.recentDog

    //coroutine job for asychronous processing
   private var viewModelJob = Job()
   private val coroutineScope = CoroutineScope(Dispatchers.Main + viewModelJob)



    fun fetchDogsApiService() {

        coroutineScope.launch {
            try {
                localdogsApiStatus.value =
                    GenerateDogsApiStatus.LOADING
                appRepository.fetchAndUpdateDogs()
                localdogsApiStatus.value =
                    GenerateDogsApiStatus.DONE
            }catch (e : Exception) {
                e.printStackTrace()
                _apiResponse.value = "Error ${e.message}"
                localdogsApiStatus.value =
                    GenerateDogsApiStatus.ERROR
                _networkError.value = true
            }
        }
    }

    fun clearRecentDogsCache() {

        coroutineScope.launch {
            try {
                localdogsApiStatus.value =
                    GenerateDogsApiStatus.LOADING
                appRepository.clearRecentDogsList()
                appRepository.clearRecentDogsCache(app)
                localdogsApiStatus.value =
                    GenerateDogsApiStatus.DONE
            }catch (e : Exception) {
                e.printStackTrace()
                _apiResponse.value = "Error ${e.message}"
                localdogsApiStatus.value =
                    GenerateDogsApiStatus.ERROR
                _networkError.value = true
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}
