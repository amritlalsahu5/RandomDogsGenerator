package com.frankymedia.dogsgenerator.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

/*
    Handling network with Retrofit
 */
private val BASE_URL = "https://dog.ceo"

//creating moshi object
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

//creating retrofit object
private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi)).addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(BASE_URL)
    .build()

interface DogsApiService {
    //http request to the base URL
    @GET("/api/breeds/image/random")
    //returning a deferred RemoteDogsInfo
    fun getDogs() : Deferred<RemoteDogsInfo>


    companion object {
        fun createService(): DogsApiService {

            return retrofit.create(
                DogsApiService::class.java)
        }
    }}



