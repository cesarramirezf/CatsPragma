package com.ramces.catspragma.`interface`

import com.ramces.catspragma.entities.Breed
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers

interface CatApi {
    @Headers("x-api-key: bda53789-d59e-46cd-9bc4-2936630fde39")
    @GET("/v1/breeds")
    fun getBreeds(): Call<List<Breed>>
}