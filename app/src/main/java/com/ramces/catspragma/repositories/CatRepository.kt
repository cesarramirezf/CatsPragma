package com.ramces.catspragma.repositories

import com.ramces.catspragma.entities.Breed
import com.ramces.catspragma.`interface`.CatApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object CatRepository {
    private val apiService = Retrofit.Builder()
        .baseUrl("https://api.thecatapi.com")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(CatApi::class.java)

    fun getBreeds(): List<Breed>? {
        val response = apiService.getBreeds().execute()
        return response.body()
    }
}
