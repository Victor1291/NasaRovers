package com.shu.nasarovers.api

import com.shu.nasarovers.models.NasaList
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface NasaListApi {

//curiosity
    @GET("mars-photos/api/v1/rovers/{rover}/photos")
    suspend fun loadPhotos(
    @Path("rover") rover: String = "perseverance",
        @Query("sol") sol: Int,
        @Query("api_key") api_key: String = API_KEY
    ): NasaList

    @GET("mars-photos/api/v1/rovers/{rover}/photos")
    suspend fun loadPhotosTwo(
        @Path("rover") rover: String = "curiosity",
        @Query("sol") sol: Int,
        @Query("api_key") api_key: String = API_KEY
    ): NasaList


    @GET("mars-photos/api/v1/rovers/{rover}/photos")
    suspend fun loadPhotosPages(
        @Path("rover") rover: String = "curiosity",
        @Query("sol") sol: Int,
        @Query("page") page: Int,
        @Query("api_key") api_key: String = API_KEY
    ): NasaList



    private companion object {
        private const val API_KEY = "Zhis9OkDaYDNvP0zij9woPpN8Db4MtrkxoTpcaff"
    }
}

val retrofit = Retrofit
    .Builder()
    .client(
        OkHttpClient.Builder().addInterceptor(HttpLoggingInterceptor().also {
            it.level = HttpLoggingInterceptor.Level.BODY
        }).build()
    )
    .baseUrl("https://api.nasa.gov")
    .addConverterFactory(GsonConverterFactory.create())
    .build()
    .create(NasaListApi::class.java)


//Rover Opportunity, Spirit, curiosity
//Посадка марсохода PERSEVERANCE п

/*
@Query("camera") camera: String = "PANCAM"
 */